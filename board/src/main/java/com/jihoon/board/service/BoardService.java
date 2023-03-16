package com.jihoon.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jihoon.board.common.constant.ResponseMessage;
import com.jihoon.board.dto.request.board.PostBoardDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.board.GetBoardResponseDto;
import com.jihoon.board.dto.response.board.GetListResponseDto;
import com.jihoon.board.dto.response.board.PostBoardResponseDto;
import com.jihoon.board.entity.BoardEntity;
import com.jihoon.board.entity.CommentEntity;
import com.jihoon.board.entity.LikyEntity;
import com.jihoon.board.entity.UserEntity;
import com.jihoon.board.repository.BoardRepository;
import com.jihoon.board.repository.CommentRepository;
import com.jihoon.board.repository.LikyRepository;
import com.jihoon.board.repository.UserRepository;

@Service
public class BoardService {
  
  @Autowired private BoardRepository boardRepository;
  @Autowired private LikyRepository likyRepository;
  @Autowired private CommentRepository commentRepository;
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

  public ResponseDto<GetBoardResponseDto> getBoard(int boardNumber) {

    GetBoardResponseDto data = null;

    try {

      BoardEntity boardEntity = boardRepository.findByBoardNumber(boardNumber);
      if (boardEntity == null) return ResponseDto.setFailed(ResponseMessage.NOT_EXIST_BOARD);

      List<LikyEntity> likyList = likyRepository.findByBoardNumber(boardNumber);
      List<CommentEntity> commentList = commentRepository.findByBoardNumberOrderByWriteDatetimeDesc(boardNumber);

      data = new GetBoardResponseDto(boardEntity, commentList, likyList);

    } catch (Exception exception) {
      exception.printStackTrace();
      return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

  }

  public ResponseDto<List<GetListResponseDto>> getList() {

    List<GetListResponseDto> data = null;

    try {

        List<BoardEntity> boardEntityList = boardRepository.findByOrderByBoardWriteDatetimeDesc();
        data = GetListResponseDto.copyList(boardEntityList);

    } catch(Exception exception) {
        exception.printStackTrace();
        return ResponseDto.setFailed(ResponseMessage.DATABASE_ERROR);
    }

    return ResponseDto.setSuccess(ResponseMessage.SUCCESS, data);

}
}
