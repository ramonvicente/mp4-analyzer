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
  private String boxType;
  private long boxSize;
  private List<BoxDto> subBoxes;
}