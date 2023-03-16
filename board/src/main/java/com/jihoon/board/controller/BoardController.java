package com.jihoon.board.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jihoon.board.common.constant.ApiPattern;
import com.jihoon.board.dto.request.board.PatchBoardDto;
import com.jihoon.board.dto.request.board.PostBoardDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.board.DeleteBoardResponseDto;
import com.jihoon.board.dto.response.board.GetBoardResponseDto;
import com.jihoon.board.dto.response.board.GetListResponseDto;
import com.jihoon.board.dto.response.board.GetMyListResponseDto;
import com.jihoon.board.dto.response.board.PatchBoardResponseDto;
import com.jihoon.board.dto.response.board.PostBoardResponseDto;
import com.jihoon.board.service.BoardService;

@RestController
@RequestMapping(ApiPattern.BOARD)
public class BoardController {

  @Autowired
  private BoardService boardService;

  private final String POST_BOARD = "";
  private final String GET_BOARD = "/{boardNumber}";
  private final String GET_LIST = "/list";
  private final String GET_MY_LIST = "/my-list";
  private final String PATCH_BOARD = "";
  private final String DELETE_BOARD = "/{boardNumber}";

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

  @GetMapping(GET_MY_LIST)
  public ResponseDto<List<GetMyListResponseDto>> getMyList(@AuthenticationPrincipal String email) {
    ResponseDto<List<GetMyListResponseDto>> response = boardService.getMyList(email);
    return response;
  }

  @PatchMapping(PATCH_BOARD)
  public ResponseDto<PatchBoardResponseDto> patchBoard(
      @AuthenticationPrincipal String email,
      @Valid @RequestBody PatchBoardDto requestBody) {
    ResponseDto<PatchBoardResponseDto> response = boardService.patchBoard(email, requestBody);
    return response;
  }

  @DeleteMapping(DELETE_BOARD)
  public ResponseDto<DeleteBoardResponseDto> deleteBoard(
      @AuthenticationPrincipal String email,
      @PathVariable("boardNumber") int boardNumber) {
    ResponseDto<DeleteBoardResponseDto> response = boardService.deleteBoard(email, boardNumber);
    return response;
  }
}
