package com.project.mygg.DTO;

import com.project.mygg.enums.Role;
import com.project.mygg.enums.Tier;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberRequestDTO {

    @NotEmpty(message = "회원 아이디는 필수입니다.")
    private String name;

    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "롤 닉네임는 필수입니다.")
    private String nickName;

    private int penalty;

    private Tier tier;

    private Role role;


    public MemberRequestDTO(String name, String password, String nickName) {
        this.name = name;
        this.password = password;
        this.nickName = nickName;
        this.penalty = 0;
        this.tier = Tier.UNRANKED;
        this.role = Role.ADMIN;
    }
}
