package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Feed;
import kz.devhils.meathouse.model.entities.Status;

import java.util.List;

public interface FeedService {

    Feed findById(Long id);
    List<Feed> getAll();
    Feed save(Feed feed);
    Feed update(Feed feed);
    void deleteById(Long id);
    void delete(Feed feed);
    Feed updateStatus(Long id, Long statusId);
}
