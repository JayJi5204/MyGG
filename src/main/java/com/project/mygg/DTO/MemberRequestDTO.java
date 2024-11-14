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

    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;

    @NotEmpty(message = "휴대폰 번호는 필수입니다.")
    private String phoneNumber;

    private Role role = Role.ADMIN;

}
