package com.example.assignment.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuotesDto {

  private Long id;

  @NotBlank
  private String author;

  @NotBlank
  private String quote;

}
