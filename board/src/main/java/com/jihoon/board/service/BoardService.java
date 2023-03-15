package com.jihoon.board.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jihoon.board.common.constant.ResponseMessage;
import com.jihoon.board.dto.request.board.PostBoardDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.board.PostBoardResponseDto;
import com.jihoon.board.entity.BoardEntity;
import com.jihoon.board.entity.UserEntity;
import com.jihoon.board.repository.BoardRepository;
import com.jihoon.board.repository.UserRepository;

@Service
public class BoardService {
  
  @Autowired private BoardRepository boardRepository;
  @Autowired private UserRepository userRepository;

  public ResponseDto<PostBoardResponseDto> postBoard(String email, PostBoardDto dto) {

    PostBoardResponseDto data = null;

    try {

      UserEntity userEntity = userRepository.findByEmail(email);
      if(userEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_USER);

      BoardEntity boardEntity = new BoardEntity(userEntity, dto);
      boardRepository.save(boardEntity);
      
      data = new PostBoardResponseDto(boardEntity);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }


    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

  }
}
