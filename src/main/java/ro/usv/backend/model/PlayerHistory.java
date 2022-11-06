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
public class PlayerHistory {
    private @Id
    @GeneratedValue Long id;

    private String rol;

    private String period;

}
