package com.project.mygg.entity;


import com.project.mygg.DTO.ResultDTO.ResultRequestDTO;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import com.project.mygg.enums.WinOrLose;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@Table(name = "result_entity")
public class ResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long id;

    private String resultDate; // 몇월 몇일에 경기했는지(그래서 일부러 LocalDateTime으로 설정안함)

    private String nickname;

    @Enumerated(EnumType.STRING)
    private ChampionName championName;

    @Enumerated(EnumType.STRING)
    private Line line;

    private Long win;

    private Long lose;

    private Long kill;

    private Long death;

    private Long assist;

    @OneToMany(mappedBy = "resultEntity", cascade = CascadeType.ALL)
    private List<PlayerEntity> playerEntity;

    public ResultEntity(ResultRequestDTO resultRequestDTO, PlayerEntity playerEntity) {
        this.resultDate = resultRequestDTO.getResultDate();
        this.nickname = playerEntity.getNickname();
        this.championName = resultRequestDTO.getChampionName();
        this.line = resultRequestDTO.getLine();
        if (resultRequestDTO.getWinOrLose() == WinOrLose.WIN) {
            this.win = 1L;
            this.lose = 0L;
        } else if (resultRequestDTO.getWinOrLose() == WinOrLose.LOSE) {
            this.win = 0L;
            this.lose = 1L;
        }
        this.kill = resultRequestDTO.getKill();
        this.death = resultRequestDTO.getDeath();
        this.assist = resultRequestDTO.getAssist();
    }

}
