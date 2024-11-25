package com.project.mygg.DTO.boardDTO;

import com.project.mygg.entity.BoardEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    public BoardResponseDTO(BoardEntity boardEntity) {
        this.id = boardEntity.getId();
        this.title = boardEntity.getTitle();
        this.content = boardEntity.getContent();
        this.writer = boardEntity.getWriter();
        this.regDay = boardEntity.getRegDay();
        this.modDay = boardEntity.getModDay();
    }
}
