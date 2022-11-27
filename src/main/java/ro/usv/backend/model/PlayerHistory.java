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

public class PlayerHistory {
    private @Id
    @GeneratedValue Long id;

    private String rol;

    private String period;

    @OneToMany(mappedBy = "playerHistory",cascade = CascadeType.REMOVE)
    private List<Award> awards;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player player;
}
