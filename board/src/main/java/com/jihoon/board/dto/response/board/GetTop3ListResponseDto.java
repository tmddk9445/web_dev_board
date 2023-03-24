package com.jihoon.board.dto.response.board;

import java.util.ArrayList;
import java.util.List;

import com.jihoon.board.entity.BoardEntity;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "좋아요 기준 상위 3개 게시물 리스트 가져오기 Response Body - data")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTop3ListResponseDto {
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

  public GetTop3ListResponseDto(BoardEntity boardEntity) {
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

  public static List<GetTop3ListResponseDto> copyList(List<BoardEntity> boardEntityList) {

    List<GetTop3ListResponseDto> list = new ArrayList<>();
  
    for (BoardEntity boardEntity : boardEntityList) {
      GetTop3ListResponseDto dto = new GetTop3ListResponseDto(boardEntity);
      list.add(dto);
    }

    return list;

  }
}
