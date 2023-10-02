package com.ramonvicente.mp4analyzer.service;

import java.util.List;

import com.ramonvicente.mp4analyzer.dto.BoxDto;

public interface MediaAnalyzer {
  
  List<BoxDto> analyze(String mediaUrl);
}
