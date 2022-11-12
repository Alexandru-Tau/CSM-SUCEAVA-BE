package ro.usv.backend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Match {
    private @Id
    @GeneratedValue Long id;

    private LocalDateTime matchDateTime;

    private Boolean isFinished;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;


    private String location;

    private String liveLink;

    private String finalScore;

    @OneToMany(mappedBy = "match",cascade = CascadeType.REMOVE)
    private List<Team> teams;

    @ManyToOne
    @JoinColumn(name = "championship_id", nullable = false)
    private Championship championship;

}
