package com.ramonvicente.mp4analyzer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ramonvicente.mp4analyzer.dto.BoxDto;
import com.ramonvicente.mp4analyzer.service.Mp4AnalyzerService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class AnalyzerController {

  @Autowired
  private final Mp4AnalyzerService analyzerService;
  
  @GetMapping(value = "/analyzes", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseStatus(HttpStatus.OK)
  public List<BoxDto> getMp4Boxes(@RequestParam String url) {
    return analyzerService.analyze(url);
  }
}