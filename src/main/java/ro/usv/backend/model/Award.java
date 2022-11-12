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
public class Award {
    private @Id
    @GeneratedValue Long id;

    private String typeAward;

    private Date dateAward;

    @ManyToOne
    @JoinColumn(name = "player_history_id", nullable = false)
    private PlayerHistory playerHistory;
}
