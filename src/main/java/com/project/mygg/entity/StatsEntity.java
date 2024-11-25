package com.project.mygg.entity;


import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "stats_entity")
@NoArgsConstructor
public class StatsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stats_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private ChampionName championName;

    @Enumerated(EnumType.STRING)
    private Line line;

    private Long win;

    private Long lose;

    private Long kill;

    private Long death;

    private Long assist;

    @OneToMany(mappedBy = "statsEntity", cascade = CascadeType.ALL)
    private List<MatchEntity> matchEntityArrayList = new ArrayList<>();


    @Builder
    public StatsEntity(ChampionName championName, Line line, Long win, Long lose, Long kill, Long death, Long assist) {
        this.championName = championName;
        this.line = line;
        this.win = win;
        this.lose = lose;
        this.kill = kill;
        this.death = death;
        this.assist = assist;
    }
}
