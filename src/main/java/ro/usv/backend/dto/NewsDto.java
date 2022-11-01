package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.usv.backend.model.NewsType;

import java.time.LocalDateTime;

/***
 * data to object obiecte care vin din front
 */
@Setter @Getter @AllArgsConstructor
public class NewsDto {
    private Long id;

    private String title;

    private String description;

    private LocalDateTime dateTime;

    private Boolean isDraft;

    private NewsType newsType;
}
