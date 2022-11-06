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
public class Club {
    private @Id
    @GeneratedValue Long id;

    private String vision;

    private String history;

    private String trophy;
}
