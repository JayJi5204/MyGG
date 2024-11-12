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
    private String username;

    @NotEmpty(message = "회원 비밀번호는 필수입니다.")
    private String password;

    @NotEmpty(message = "롤 닉네임는 필수입니다.")
    private String nickName;

    private int penalty = 0;

    private Tier tier = Tier.UNRANKED;

    private Role role = Role.MANAGER;


}
