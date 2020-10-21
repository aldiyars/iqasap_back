package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Feed;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.repositories.FeedRepo;
import kz.devhils.meathouse.service.FeedService;
import kz.devhils.meathouse.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements FeedService {

    @Autowired
    private FeedRepo feedRepo;
    @Autowired
    private StatusService statusService;


    @Override
    public Feed findById(Long id) {
        Feed result = feedRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<Feed> getAll() {
        List<Feed> result = feedRepo.findAll();
        return result;
    }

    @Override
    public Feed save(Feed feed) {
        Status status = statusService.findByName("active");
        feed.setStatus(status);
        Feed result = feedRepo.save(feed);
        return result;
    }

    @Override
    public Feed update(Feed feed) {
        if(feed.getId() != null && findById(feed.getId()) != null){
            feedRepo.save(feed);
        }
        return feed;
    }

    @Override
    public void deleteById(Long id) {
        feedRepo.deleteById(id);
    }

    @Override
    public void delete(Feed feed) {
        feedRepo.delete(feed);
    }

    @Override
    public Feed updateStatus(Long id, Long statusId) {
        Feed result = findById(id);
        Status status = statusService.findById(statusId);
        result.setStatus(status);
        feedRepo.save(result);
        return result;
    }
}
