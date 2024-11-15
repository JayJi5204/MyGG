package com.project.mygg.DTO.BoardDTO;

import com.project.mygg.entity.BoardEntity;
import com.project.mygg.entity.MemberEntity;
import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.Tier;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardResponseDTO {
    private Long id;

    private String title;

    private String content;

    private String writer;

    private LocalDateTime regDay;

    private LocalDateTime modDay;

    private MemberEntity memberEntity;

    public BoardResponseDTO(BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.writer= boardEntity.getWriter();
        this.regDay = boardEntity.getRegDay();
        this.modDay = boardEntity.getModDay();
        this.memberEntity = boardEntity.getMemberEntity();
    }
}
