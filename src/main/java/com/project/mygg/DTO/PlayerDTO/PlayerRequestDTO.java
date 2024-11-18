package com.project.mygg.DTO.PlayerDTO;

import com.project.mygg.enums.Tier;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerRequestDTO {

    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotNull(message = "티어 선택은 필수입니다.")
    @Enumerated(EnumType.STRING)
    private Tier tier;
    private int penalty = 0;
}
