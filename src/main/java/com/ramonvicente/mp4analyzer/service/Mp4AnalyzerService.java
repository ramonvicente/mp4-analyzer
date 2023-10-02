package com.ramonvicente.mp4analyzer.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;
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

      int firstBoxSize = getBoxSize(urlResponse.readNBytes(4));
      String firstBoxType = getBoxType(urlResponse);

      return List.of(BoxDto.builder().boxSize(firstBoxSize).boxType(firstBoxType).test(urlResponse.readAllBytes()).build());
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

  private int getBoxSize(byte[] data) {
    return ((data[0] & 0xFF) << 24) |
           ((data[1] & 0xFF) << 16) |
           ((data[2] & 0xFF) << 8) |
           (data[3] & 0xFF);
  }

  public String getBoxType(InputStream inputStream) throws IOException {
    byte[] boxType = new byte[4];
    inputStream.read(boxType);
    StringBuilder stringBuilder = new StringBuilder();
    for (byte b : boxType) {
        char character = (char) b;
        stringBuilder.append(character);
    }

    return stringBuilder.toString();
  }
}
