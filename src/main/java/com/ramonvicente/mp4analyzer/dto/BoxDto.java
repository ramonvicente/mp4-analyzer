package com.ramonvicente.mp4analyzer.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoxDto {
  // First I though in BoxType be a enum however I believe there are many other types of box in a MP4 file.
  private String boxType;
  private int boxSize;
  private List<BoxDto> subBoxes;
}