package com.project.mygg.entity;

import com.project.mygg.DTO.PlayerRequestDTO;
import com.project.mygg.DTO.PlayerResponseDTO;
import com.project.mygg.enums.Tier;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "player_entity")
@NoArgsConstructor
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long id;

    @Column(unique = true)
    private String nickName;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    private int penalty;



    @OneToMany(mappedBy = "playerEntity", cascade = CascadeType.ALL)
    private List<ChampionStatsEntity> championStatsEntity = new ArrayList<>();


    public PlayerEntity(PlayerRequestDTO playerRequestDTO) {
        this.nickName = playerRequestDTO.getNickName();
        this.tier = playerRequestDTO.getTier();
        this.penalty = playerRequestDTO.getPenalty();
    }
}
