package com.ramonvicente.mp4analyzer.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ramonvicente.mp4analyzer.dto.BoxDto;

@Service
public class Mp4AnalyzerService implements MediaAnalyzer {

  @Override
  public List<BoxDto> analyze(String mp4Url) {
    if(mp4Url.isBlank()) {
      throw new IllegalArgumentException("mp4Url must not be blank.");
    }

    HttpURLConnection connection = null;
    try {

      URL url = new URL(mp4Url).toURI().toURL();

      connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      InputStream urlResponse = connection.getInputStream();

      int firstBoxSize = getBoxSize(urlResponse.readNBytes(4), 0);
      String firstBoxType = getBoxType(urlResponse);
      BoxDto firstBox = BoxDto.builder()
              .boxSize(firstBoxSize)
              .boxType(firstBoxType)
              .subBoxes(new ArrayList<>())
              .build();

      byte[] content = urlResponse.readAllBytes();

      List<BoxDto> boxes = new ArrayList<>();
      boxes.add(firstBox);

      /*
      The first idea was take all parent boxes and then the subBoxes, 
      however I am a bit struggle with that and keep just taking the sub boxes
      */ 
      parseSubBoxes(content, 0, content.length, firstBox);

      // the idea was pass boxes in the parseSubBoxes and then it add the parent boxes
      return boxes;

    } catch (IOException e){
      e.printStackTrace();
      return List.of();
    }
     catch(URISyntaxException e) {
      e.printStackTrace();
      return List.of();
    }
    finally {
      if (connection != null) {
          connection.disconnect();
      }
    }
  }

  private int parseSubBoxes(byte[] data, int startIndex, int endIndex, BoxDto parentBox) {
    int index = startIndex;
    while (index < endIndex) {
      int boxSize = getBoxSize(data, index);
      String boxType = getBoxType(data, index + 4);

     BoxDto currentBox = BoxDto.builder()
              .boxSize(boxSize)
              .boxType(boxType)
              .subBoxes(new ArrayList<>())
              .build();

      parentBox.getSubBoxes().add(currentBox);

      if (isContainerBox(boxType)) {
        index = parseSubBoxes(data, index + 8, index + boxSize, currentBox);
      } else {
        index += boxSize;
      }
    }
    return index;
  }

  private int getBoxSize(byte[] data, int index) {
    return ((data[index] & 0xFF) << 24) |
          ((data[index + 1] & 0xFF) << 16) |
          ((data[index + 2] & 0xFF) << 8) |
          (data[index + 3] & 0xFF);
  }

  private String getBoxType(InputStream inputStream) throws IOException {
    byte[] boxType = new byte[4];
    inputStream.read(boxType);

    StringBuilder stringBuilder = new StringBuilder();

    for (byte b : boxType) {
        char character = (char) b;
        stringBuilder.append(character);
    }
    return stringBuilder.toString();
  }

  private String getBoxType(byte[] data, int index) {
    return new String(data, index, 4);
  }

  private boolean isContainerBox(String boxType) {
    return boxType.equals("moof") || boxType.equals("traf");
  }
}
