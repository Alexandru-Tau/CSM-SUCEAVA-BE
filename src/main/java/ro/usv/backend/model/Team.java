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
public class Team {
    private @Id
    @GeneratedValue Long id;

    private String logo;

    @Enumerated(EnumType.STRING)
    private TeamType teamType;


}
