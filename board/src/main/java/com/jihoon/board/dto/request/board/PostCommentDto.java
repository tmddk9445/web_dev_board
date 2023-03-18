package com.jihoon.board.dto.request.board;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostCommentDto {
  
  private int boardNumber;
  private String commentContent;

}
