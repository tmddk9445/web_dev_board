package com.jihoon.board.dto.request.board;

import javax.validation.constraints.Min;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LikeDto {
  @Min(1)
  private int boardNumber;
}
