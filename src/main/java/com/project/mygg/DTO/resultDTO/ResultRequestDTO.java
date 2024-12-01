package com.project.mygg.DTO.resultDTO;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResultRequestDTO {

    @NotEmpty(message = "경기 일자는 필수입니다.")
    private String resultDate;


    @NotEmpty(message = "내용은 필수입니다.")
    private String content;

    @NotEmpty(message = "POG는 필수입니다.")
    private String pog;

    private String writer;
}
