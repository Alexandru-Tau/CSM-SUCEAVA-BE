package ro.usv.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Sponsors {
    private @Id
    @GeneratedValue Long id;

    private String name;

    private String logo;

    private String linkSite;

    private String edition;

}
