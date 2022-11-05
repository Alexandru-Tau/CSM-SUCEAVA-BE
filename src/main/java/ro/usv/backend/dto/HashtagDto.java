package ro.usv.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class HashtagDto {
    private Long id;

    private String tag;
}
