package com.project.mygg.entity;


import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "match_entity")
@NoArgsConstructor
public class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long id;

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

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private PlayerEntity playerEntity;


    public StatsEntity(PlayerEntity playerEntity,String nickname, ChampionName championName, Line line, Long kill, Long death, Long assist) {
        this.playerEntity=playerEntity;
        this.nickname = nickname;
        this.championName = championName;
        this.line = line;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
    }

    public StatsEntity(PlayerEntity playerEntity, ChampionName championName, Long kill, Long death, Long assist) {
        this.playerEntity = playerEntity;
        this.championName = championName;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
    }

}
