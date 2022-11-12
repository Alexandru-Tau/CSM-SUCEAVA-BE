package ro.usv.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "championship",cascade = CascadeType.REMOVE)
    private List<Match> matches;



}
