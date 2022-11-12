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
public class Club {
    private @Id
    @GeneratedValue Long id;

    private String vision;

    private String history;

    private String trophy;

    @OneToMany(mappedBy = "club",cascade = CascadeType.REMOVE)
    private List<Team> teams;

    @OneToMany(mappedBy = "club",cascade = CascadeType.REMOVE)
    private List<Sponsors> sponsors;
}
