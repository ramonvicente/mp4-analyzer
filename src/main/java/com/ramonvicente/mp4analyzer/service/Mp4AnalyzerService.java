package com.ramonvicente.mp4analyzer.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ramonvicente.mp4analyzer.dto.BoxDto;

@Service
public class Mp4AnalyzerService implements MediaAnalyzer {

  @Override
  public List<BoxDto> analyze(String mp4Url) {
    return List.of();
  }
}
