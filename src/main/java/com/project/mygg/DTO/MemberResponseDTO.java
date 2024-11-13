package com.project.mygg.DTO;

import com.project.mygg.entity.MemberEntity;
import com.project.mygg.enums.Role;
import com.project.mygg.enums.Tier;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String nickName;
    private int penalty;
    private Tier tier;
    private Role role;

    public MemberResponseDTO(MemberEntity memberEntity) {
        this.id = memberEntity.getId();
        this.username = memberEntity.getUsername();
        this.name=memberEntity.getName();
        this.nickName = memberEntity.getNickName();
        this.penalty = memberEntity.getPenalty();
        this.tier = memberEntity.getTier();
        this.role = memberEntity.getRole();
    }
}
