package com.project.mygg.DTO.BoardDTO;

import com.project.mygg.entity.MemberEntity;
import com.project.mygg.enums.Tier;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
