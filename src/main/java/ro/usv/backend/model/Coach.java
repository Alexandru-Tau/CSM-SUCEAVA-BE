package ro.usv.backend.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Coach {
    private @Id
    @GeneratedValue Long id;

    private String name;

    private String firstName;

    private String nationality;

    private String position;

    private Date birthDate;

    private String description;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "team_id", referencedColumnName = "id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "championship_id")
    private Championship championship;

}


