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
public class Team {
    private @Id
    @GeneratedValue Long id;

    private String logo;

    private String name;

    @Enumerated(EnumType.STRING)
    private TeamType teamType;

    @OneToMany(mappedBy = "team",cascade = CascadeType.REMOVE)
    private List<Player> players;

    @OneToOne(mappedBy = "team")
    private Coach coach;

    @ManyToOne
    @JoinColumn(name = "match_id")
    private Match match;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}
