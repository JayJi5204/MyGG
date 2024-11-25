package com.project.mygg.DTO.replyDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyRequestDTO {

    @NotEmpty(message = "댓글 내용은 필수입니다.")
    private String replyContent;

    private String replyWriter;

}
