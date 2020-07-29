package kz.devhils.meathouse.controller.rest;

import kz.devhils.meathouse.model.entities.Feed;
import kz.devhils.meathouse.model.entities.Statuses;
import kz.devhils.meathouse.service.FeedService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "api/v1/feed")
public class FeedRestController {

    private final FeedService feedService;

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Feed result = feedService.findById(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Feed> result = feedService.getAll();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<?> addFeed(@RequestBody Feed feed){
        Feed result = feedService.save(feed);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<?> updateFeed(@RequestBody Feed feed){
        Feed result = feedService.update(feed);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @RequestBody Statuses status){
        Feed result = feedService.updateStatus(id, status);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
