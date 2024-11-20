package com.project.mygg.DTO.ResultDTO;

import com.project.mygg.entity.ResultEntity;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultResponseDTO {

    private Long id;

    private String nickname;

    private ChampionName championName;

    private Line line;

    private Long win;

    private Long lose;

    private Long kill;

    private Long death;

    private Long assist;

    public String resultDate;

    public ResultResponseDTO(ResultEntity resultEntity) {
        this.id = resultEntity.getId();
        this.nickname = resultEntity.getNickname();
        this.championName = resultEntity.getChampionName();
        this.line = resultEntity.getLine();
        this.win = resultEntity.getWin();
        this.lose = resultEntity.getLose();
        this.kill = resultEntity.getKill();
        this.death = resultEntity.getDeath();
        this.assist = resultEntity.getAssist();
        this.resultDate = resultEntity.getResultDate();
    }
}
