package com.project.mygg.DTO.ResultDTO;

import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import com.project.mygg.enums.WinOrLose;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultRequestDTO {

    @NotEmpty(message = "언제 경기인지는 필수입니다.")
    public String resultDate;

    @NotEmpty(message = "닉네임은 필수입니다.")
    private String nickname;

    @NotNull(message = "챔피언 이름은 필수입니다.")
    @Enumerated(EnumType.STRING)
    private ChampionName championName;

    @NotNull(message = "라인은 필수입니다.")
    @Enumerated(EnumType.STRING)
    private Line line;

    @NotNull(message = "승패는 필수입니다.")
    @Enumerated(EnumType.STRING)
    private WinOrLose winOrLose;

    @NotNull(message = "킬수는 필수입니다.")
    private Long kill;

    @NotNull(message = "데스수는 필수입니다.")
    private Long death;

    @NotNull(message = "어시스트수는 필수입니다.")
    private Long assist;



}
