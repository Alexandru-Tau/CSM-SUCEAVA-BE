package ro.usv.backend.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString @EqualsAndHashCode
public class News {
    private @Id @GeneratedValue Long id;

    private String title;

    private String description;

    private Boolean isPosted;

    private Boolean isAppointed;

    private LocalDateTime dateTime;

    private Boolean isDraft;

    @Enumerated(EnumType.STRING)
    private NewsType newsType;

    @OneToMany(mappedBy = "news",cascade = CascadeType.REMOVE)
    private List<Hashtag> hashtags;


}
