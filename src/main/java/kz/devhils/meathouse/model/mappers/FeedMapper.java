package kz.devhils.meathouse.model.mappers;

import kz.devhils.meathouse.model.dtos.request.CreateFeedRequest;
import kz.devhils.meathouse.model.dtos.request.FeedRequest;
import kz.devhils.meathouse.model.dtos.response.FeedResponse;
import kz.devhils.meathouse.model.entities.Feed;

public interface FeedMapper {

    Feed toEntity (FeedRequest feedRequest);
    Feed inCreateEntityToEntity (CreateFeedRequest createFeedRequest);
    FeedResponse toDto (Feed feed);
}
