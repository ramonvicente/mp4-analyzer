package com.ramonvicente.mp4analyzer.service;

import java.util.List;

import com.ramonvicente.mp4analyzer.dto.BoxDto;

/*
I first thought in make this interface MediaAnalyzer<T> where the generic class would be the type of box 
but for now I assume that all media analyzer has the same Box structure 
*/
public interface MediaAnalyzer {
  
  List<BoxDto> analyze(String mediaUrl);
}
