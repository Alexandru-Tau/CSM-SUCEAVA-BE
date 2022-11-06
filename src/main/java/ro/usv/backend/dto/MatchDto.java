package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.usv.backend.model.MatchType;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
public class MatchDto {
    private Long id;

    private LocalDateTime matchDateTime;

    private Boolean isFinished;

    private MatchType matchType;

    private String location;

    private String liveLink;

    private String finalScore;
}
