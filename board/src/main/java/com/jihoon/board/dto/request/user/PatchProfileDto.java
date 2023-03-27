package com.jihoon.board.dto.request.user;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.URL;

import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="유저 프로필 URL 수정 Request Body")
@Data
@NoArgsConstructor
public class PatchProfileDto {
    @ApiModelProperty(value="프로필 사진 URL", example="http://~", required=true)
    @NotBlank
    @URL
    private String profile;
}