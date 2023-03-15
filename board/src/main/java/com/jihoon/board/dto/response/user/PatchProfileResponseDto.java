package com.jihoon.board.dto.response.user;

import com.jihoon.board.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchProfileResponseDto {
  
  private String email;
  private String nickname;
  private String telNumber;
  private String address;
  private String profile;

  public PatchProfileResponseDto(UserEntity userEntity) {
    this.email = userEntity.getEmail();
    this.nickname = userEntity.getNickname();
    this.telNumber = userEntity.getTelNumber();
    this.address = userEntity.getAddress();
    this.profile = userEntity.getProfile();
  }

}
