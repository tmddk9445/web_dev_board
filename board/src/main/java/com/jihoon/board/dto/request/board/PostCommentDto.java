package com.jihoon.board.dto.request.board;

import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value="댓글 작성 Request Body")
@Data
@NoArgsConstructor
public class PostCommentDto {
    @ApiModelProperty(value="게시물 번호", example="1", required=true)
    @Min(1)
    private int boardNumber;

    @ApiModelProperty(value="댓글 내용", example="This is Comment!", required=true)
    @NotBlank
    private String commentContent;
}