package com.project.mygg.DTO.matchDTO;

import com.project.mygg.enums.ChampionName;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MatchRequestDTO {

    private String matchDate;
    private String nickname;
    private ChampionName championName;
    private Long win;
    private Long lose;
    private Long kill;
    private Long death;
    private Long assist;


}
