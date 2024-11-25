package com.project.mygg.DTO.boardDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardRequestDTO {

    @NotEmpty(message = "게시글 제목은 필수입니다.")
    private String title;

    @NotEmpty(message = "게시글 내용은 필수입니다.")
    private String content;

    private String writer;
}
