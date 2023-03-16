package com.jihoon.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jihoon.board.common.constant.ApiPattern;
import com.jihoon.board.dto.request.board.PostBoardDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.board.GetBoardResponseDto;
import com.jihoon.board.dto.response.board.GetListResponseDto;
import com.jihoon.board.dto.response.board.PostBoardResponseDto;
import com.jihoon.board.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

  @Autowired private BoardService boardService;

  private final String POST_BOARD = "";
  private final String GET_BOARD = "/{boardNumber}";
  private final String GET_LIST = "/list";

  @PostMapping(POST_BOARD)
  public ResponseDto<PostBoardResponseDto> postBoard(
    @AuthenticationPrincipal String email, 
    @Valid @RequestBody PostBoardDto requestBody) {
    ResponseDto<PostBoardResponseDto> response = boardService.postBoard(email, requestBody);
    return response;
  }

  @GetMapping(GET_BOARD)
  public ResponseDto<GetBoardResponseDto> getBoard(@PathVariable("boardNumber") int boardNumber) {
    ResponseDto<GetBoardResponseDto> response = boardService.getBoard(boardNumber);
    return response;
  }
  
  @GetMapping(GET_LIST)
  public ResponseDto<List<GetListResponseDto>> getList() {
    ResponseDto<List<GetListResponseDto>> response = boardService.getList();
    return response;
  }


}
