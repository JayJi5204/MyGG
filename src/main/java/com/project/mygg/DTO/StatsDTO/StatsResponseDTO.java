package com.project.mygg.DTO.statsDTO;

import com.project.mygg.entity.StatsEntity;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsResponseDTO {


    private String nickname;

    private ChampionName championName;

    private Line line;

    private Long win;

    private Long lose;

    private Long kill;

    private Long death;

    private Long assist;


    public StatsResponseDTO(StatsEntity statsEntity) {
        this.nickname = statsEntity.getNickname();
        this.championName = statsEntity.getChampionName();
        this.line = statsEntity.getLine();
        this.win = statsEntity.getWin();
        this.lose = statsEntity.getLose();
        this.kill = statsEntity.getKill();
        this.death = statsEntity.getDeath();
        this.assist = statsEntity.getAssist();
    }
}
