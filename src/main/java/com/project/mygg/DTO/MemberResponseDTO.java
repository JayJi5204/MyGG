package com.project.mygg.DTO;

import com.project.mygg.entity.MemberEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
class MemberResponseDTO {
    private Long id;
    private String name;
    private String password;
    private String nickName;
    private int Elo;
    private int penalty;

    public MemberResponseDTO(MemberEntity memberEntity) {
        this.id = memberEntity.getId();
        this.name = memberEntity.getName();
        this.password = memberEntity.getPassword();
        this.nickName = memberEntity.getNickName();
        this.Elo = memberEntity.getElo();
        this.penalty = memberEntity.getPenalty();
    }
}
