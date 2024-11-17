package com.project.mygg.DTO.ReplyDTO;

import com.project.mygg.entity.MemberEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReplyRequestDTO {

    @NotEmpty(message = "댓글 내용은 필수입니다.")
    private String reply;

    private String replyWriter;

    public ReplyRequestDTO(String reply,  MemberEntity memberEntity) {
        this.reply = reply;
        this.replyWriter = memberEntity.getUsername();
    }
}
