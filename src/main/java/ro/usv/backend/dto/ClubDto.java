package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ClubDto {

    private Long id;

    private String vision;

    private String history;

    private String trophy;
}

