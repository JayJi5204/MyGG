package com.project.mygg.DTO.PlayerDTO;

import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.Tier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerResponseDTO {
    private Long id;
    private String nickname;
    private Tier tier;
    private int penalty;

    public PlayerResponseDTO(PlayerEntity playerEntity) {
        this.id = playerEntity.getId();
        this.nickname = playerEntity.getNickname();
        this.tier = playerEntity.getTier();
        this.penalty = playerEntity.getPenalty();
    }
}
