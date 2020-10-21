package kz.devhils.meathouse.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class FeedRequest {
    private Long id;
    private String title;
    private String description;
    private Long authorId;
}
