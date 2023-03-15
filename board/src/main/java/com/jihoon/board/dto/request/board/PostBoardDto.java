package com.jihoon.board.dto.request.board;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostBoardDto {

  @NotBlank
  private String boardTitle;
  @NotBlank
  private String boardContent;
  private String boardImgUrl;
  
}
