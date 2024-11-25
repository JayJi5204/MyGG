package com.project.mygg.entity;

import com.project.mygg.DTO.replyDTO.ReplyRequestDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reply_entity")
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reply_id")
    private Long id;

    private Long brdId;

    private String replyContent;

    private String replyWriter;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime replyRegDay;

    @LastModifiedDate
    private LocalDateTime replyModDay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private MemberEntity memberEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    public ReplyEntity(ReplyRequestDTO replyRequestDTO, BoardEntity boardEntity, MemberEntity memberEntity) {
        this.replyContent = replyRequestDTO.getReplyContent();
        this.brdId = boardEntity.getId();
        this.replyWriter = memberEntity.getUsername();
    }


    public void updateReply(ReplyRequestDTO replyRequestDTO) {
        this.replyContent = replyRequestDTO.getReplyContent();
    }


}
