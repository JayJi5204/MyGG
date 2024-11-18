package com.project.mygg.DTO.ReplyDTO;

import com.project.mygg.entity.ReplyEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ReplyResponseDTO {
    private Long id;

    private String replyContent;

    private String replyWriter;

    private LocalDateTime replyRegDay;

    private LocalDateTime replyModDay;


    public ReplyResponseDTO(ReplyEntity replyEntity) {
        this.id = replyEntity.getId();
        this.replyContent = replyEntity.getReplyContent();
        this.replyWriter= replyEntity.getReplyWriter();
        this.replyRegDay = replyEntity.getReplyRegDay();
        this.replyModDay = replyEntity.getReplyModDay();
    }
}
