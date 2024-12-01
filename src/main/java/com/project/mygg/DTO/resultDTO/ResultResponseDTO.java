package com.project.mygg.DTO.resultDTO;

import com.project.mygg.entity.BoardEntity;
import com.project.mygg.entity.ResultEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ResultResponseDTO {
    private Long id;

    private String resultDate;

    private String content;

    private String pog;

    private String writer;

    private LocalDateTime modDay;


    public ResultResponseDTO(ResultEntity resultEntity) {
        this.id = resultEntity.getId();
        this.resultDate = resultEntity.getResultDate();
        this.content=resultEntity.getContent();
        this.pog = resultEntity.getPog();
        this.writer = resultEntity.getWriter();
        this.modDay = resultEntity.getModDay();
    }
}
