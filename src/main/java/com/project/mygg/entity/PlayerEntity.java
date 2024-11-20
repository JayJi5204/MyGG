package com.project.mygg.entity;

import com.project.mygg.DTO.PlayerDTO.PlayerRequestDTO;
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
    private String nickname;

    @Enumerated(EnumType.STRING)
    private Tier tier;

    private int penalty;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private ResultEntity resultEntity;

    public PlayerEntity(PlayerRequestDTO playerRequestDTO) {
        this.nickname = playerRequestDTO.getNickname();
        this.tier = playerRequestDTO.getTier();
        this.penalty = playerRequestDTO.getPenalty();
    }

    public void update(PlayerRequestDTO playerRequestDTO) {
        this.nickname=playerRequestDTO.getNickname();
        this.tier=playerRequestDTO.getTier();
    }

    // enum값 정수로 변환
    public int getTierValue() {
        return this.tier.getValue();
    }
}
