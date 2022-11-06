package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.usv.backend.model.ChampionshipType;

@Setter
@Getter
@AllArgsConstructor
public class ChampionshipDto {
    private Long id;

    private ChampionshipType champType;

    private String edition;

    private String trophy;
}
