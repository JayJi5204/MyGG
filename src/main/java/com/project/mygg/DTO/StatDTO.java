package com.project.mygg.DTO;

import com.project.mygg.enums.ChampionName;
import com.project.mygg.enums.Line;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatDTO {
    private ChampionName championName;
   private Line line;
    private Long Kill;
    private Long Death;
    private Long Assist;
}
