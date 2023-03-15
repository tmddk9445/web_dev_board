package com.jihoon.board.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jihoon.board.common.constant.ApiPattern;
import com.jihoon.board.dto.request.user.PatchProfileDto;
import com.jihoon.board.dto.response.ResponseDto;
import com.jihoon.board.dto.response.user.PatchProfileResponseDto;
import com.jihoon.board.service.UserService;

@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
  
  @Autowired UserService userService;

  private final String PATCH_PROFILE = "/profile";

  @PatchMapping(PATCH_PROFILE)
  public ResponseDto<PatchProfileResponseDto> patchProfile(
    @AuthenticationPrincipal String email, 
    @Valid @RequestBody PatchProfileDto requestBody
    ) {
    ResponseDto<PatchProfileResponseDto> response = userService.patchProfile(email, requestBody);
    return response;
  }

}
