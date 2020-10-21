package kz.devhils.meathouse.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedResponse {

    private Long id;
    private String title;
    private String description;
    private Long authorId;
    private String createdAt;
    private String photoUrl;
}
