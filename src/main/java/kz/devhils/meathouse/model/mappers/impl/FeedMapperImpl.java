package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.request.CreateFeedRequest;
import kz.devhils.meathouse.model.dtos.request.FeedRequest;
import kz.devhils.meathouse.model.dtos.response.FeedResponse;
import kz.devhils.meathouse.model.entities.Feed;
import kz.devhils.meathouse.model.entities.User;
import kz.devhils.meathouse.model.mappers.FeedMapper;
import kz.devhils.meathouse.service.FeedService;
import kz.devhils.meathouse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class FeedMapperImpl implements FeedMapper {
    
    @Autowired
    private UserService userService;
    @Autowired
    private FeedService feedService;

    @Override
    public Feed toEntity(FeedRequest feedRequest) {
        User author = userService.findById(feedRequest.getAuthorId());
        Feed feed = feedService.findById(feedRequest.getId());

        feed.setId(feedRequest.getId());
        feed.setTitle(feed.getTitle());
        feed.setDescription(feedRequest.getDescription());
        feed.setAuthor(author);

        return feed;
    }

    @Override
    public Feed inCreateEntityToEntity(CreateFeedRequest createFeedRequest) {
        User user = userService.findById(createFeedRequest.getAuthorId());
        
        Feed feed = new Feed();
        feed.setAuthor(user);
        feed.setTitle(createFeedRequest.getTitle());
        feed.setDescription(createFeedRequest.getDescription());
        
        return feed;
    }

    @Override
    public FeedResponse toDto(Feed feed) {
        String authorFullName = feed.getAuthor().getFirstName() + " " + feed.getAuthor().getLastName();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        String createdAt = dateFormat.format(feed.getCreatedAt());
        FeedResponse feedResponse = new FeedResponse();
        feedResponse.setId(feed.getId());
        feedResponse.setTitle(feed.getTitle());
        feedResponse.setDescription(feed.getDescription());
        feedResponse.setAuthorFullName(authorFullName);
        feedResponse.setCreatedAt(createdAt);
        feedResponse.setPhotoUrl(feed.getPhoto().getFileUrl());
        return feedResponse;
    }
}
