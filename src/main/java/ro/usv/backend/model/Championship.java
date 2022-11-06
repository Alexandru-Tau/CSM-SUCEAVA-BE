package ro.usv.backend.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Championship {
    private @Id
    @GeneratedValue Long id;

    @Enumerated(EnumType.STRING)
    private ChampionshipType champType;

    private String edition;

    private String trophy;


}
