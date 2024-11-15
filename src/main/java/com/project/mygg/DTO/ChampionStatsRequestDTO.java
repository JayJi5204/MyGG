package com.project.mygg.DTO;

import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChampionStatsRequestDTO {

    @NotNull(message = "챔피언 이름은 필수입니다.")
    @Enumerated(EnumType.STRING)
    private ChampionName championName;

    @NotNull(message = "라인은 필수입니다.")
    @Enumerated(EnumType.STRING)
    private Line line;

    private int win = 0;

    private int lose = 0;

    private int kill = 0;

    private int death = 0;

    private int assist = 0;
}
