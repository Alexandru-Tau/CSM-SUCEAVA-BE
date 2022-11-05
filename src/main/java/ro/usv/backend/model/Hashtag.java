package ro.usv.backend.model;

import lombok.*;

import javax.persistence.*;
@Entity
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
@ToString @EqualsAndHashCode
public class Hashtag {
    private @Id @GeneratedValue Long id;

    private String tag;

    @ManyToOne
    @JoinColumn(name = "news_id", nullable = false)
    private News news;
}
