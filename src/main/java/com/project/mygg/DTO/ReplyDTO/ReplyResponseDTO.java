package com.project.mygg.DTO.ReplyDTO;

import com.project.mygg.entity.BoardEntity;
import com.project.mygg.entity.ReplyEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyResponseDTO {
    private Long id;

    private String reply;

    private String replyWriter;

    private LocalDateTime regDay;

    private LocalDateTime modDay;


    public ReplyResponseDTO(ReplyEntity replyEntity) {
        this.id = replyEntity.getId();
        this.reply = replyEntity.getReply();
        this.replyWriter= replyEntity.getReplyWriter();
        this.regDay = replyEntity.getRegDay();
        this.modDay = replyEntity.getModDay();
    }
}
