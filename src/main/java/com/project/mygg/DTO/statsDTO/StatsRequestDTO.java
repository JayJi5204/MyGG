package com.project.mygg.DTO.statsDTO;

import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatsRequestDTO {

    @NotNull(message = "챔피언 이름은 필수입니다.")
    private ChampionName championName;

    @NotNull(message = "라인은 필수입니다.")
    private Line line;

    @NotNull(message = "킬수는 필수입니다.")
    private Long kill;

    @NotNull(message = "데스수는 필수입니다.")
    private Long death;

    @NotNull(message = "어시스트수는 필수입니다.")
    private Long assist;


}
