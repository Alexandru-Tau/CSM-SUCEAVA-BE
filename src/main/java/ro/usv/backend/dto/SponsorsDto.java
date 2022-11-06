package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class SponsorsDto {
    private Long id;

    private String name;

    private String logo;

    private String linkSite;

    private String edition;
}
