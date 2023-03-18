package com.jihoon.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.jihoon.board.entity.BoardEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetSearchListResponseDto {
  private int boardNumber;
  private String boardTitle;
  private String boardContent;
  private String boardImgUrl;
  private String boardWriteDatetime;
  private int viewCount;
  private String writerNickname;
  private String writerProfileUrl;
  private int commentCount;
  private int likeCount;

  public GetSearchListResponseDto(BoardEntity boardEntity) {
    this.boardNumber = boardEntity.getBoardNumber();
    this.boardTitle = boardEntity.getBoardTitle();
    this.boardContent = boardEntity.getBoardContent();
    this.boardImgUrl = boardEntity.getBoardImgUrl();
    this.boardWriteDatetime = boardEntity.getBoardWriteDatetime();
    this.viewCount = boardEntity.getViewCount();
    this.writerNickname = boardEntity.getWriterNickname();
    this.writerProfileUrl = boardEntity.getWriterProfileUrl();
    this.commentCount = boardEntity.getCommentCount();
    this.likeCount = boardEntity.getLikeCount();
  }

  public static List<GetSearchListResponseDto> copyList(List<BoardEntity> boardEntityList) {

    List<GetSearchListResponseDto> list = new ArrayList<>();
  
    for (BoardEntity boardEntity : boardEntityList) {
      GetSearchListResponseDto dto = new GetSearchListResponseDto(boardEntity);
      list.add(dto);
    }

    return list;

  }
}
