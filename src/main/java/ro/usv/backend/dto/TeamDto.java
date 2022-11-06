package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.usv.backend.model.TeamType;

@Getter
@Setter
@AllArgsConstructor
public class TeamDto {
    private Long id;

    private String logo;

    private TeamType teamType;

}
