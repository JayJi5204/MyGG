package com.project.mygg.DTO.memberDTO;

import com.project.mygg.entity.MemberEntity;
import com.project.mygg.enums.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberResponseDTO {
    private Long id;
    private String username;
    private String name;
    private String phoneNumber;
    private Role role;

    public MemberResponseDTO(MemberEntity memberEntity) {
        this.id = memberEntity.getId();
        this.username = memberEntity.getUsername();
        this.name = memberEntity.getName();
        this.phoneNumber = memberEntity.getPhoneNumber();
        this.role = memberEntity.getRole();
    }
}
