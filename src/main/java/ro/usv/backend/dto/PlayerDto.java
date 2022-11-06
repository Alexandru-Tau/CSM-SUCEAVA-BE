package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
@Setter
@Getter
@AllArgsConstructor
public class PlayerDto {
    private  Long id;

    private String name;

    private String firstName;

    private String nationality;

    private String position;

    private Date birthDate;

    private Integer height;

    private String description;
}
