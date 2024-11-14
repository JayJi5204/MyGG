package com.project.mygg.DTO;

import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.Tier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerResponseDTO {
    private Long id;
    private String nickName;
    private Tier tier;
    private int penalty;

    public PlayerResponseDTO(PlayerEntity playerEntity) {
        this.id = playerEntity.getId();
        this.nickName = playerEntity.getNickName();
        this.tier = playerEntity.getTier();
        this.penalty = 0;
    }
}
