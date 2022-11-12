package ro.usv.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Player {
    private @Id
    @GeneratedValue Long id;

    private String name;

    private String firstName;

    private String nationality;

    private String position;

    private Date birthDate;

    private Integer height;

    private String description;

    @OneToMany(mappedBy = "player", cascade = CascadeType.REMOVE)
    private List<PlayerHistory> playerHistories;

    @ManyToOne
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

}
