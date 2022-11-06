package ro.usv.backend.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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

}


