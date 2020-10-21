package kz.devhils.meathouse.controller.rest;

import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.dtos.request.CreateFeedRequest;
import kz.devhils.meathouse.model.dtos.request.FeedRequest;
import kz.devhils.meathouse.model.dtos.response.FeedResponse;
import kz.devhils.meathouse.model.entities.Feed;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.model.mappers.FeedMapper;
import kz.devhils.meathouse.service.FeedService;
import kz.devhils.meathouse.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/feed")
public class FeedRestController {

    @Autowired
    private FeedService feedService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private FeedMapper feedMapper;

    public Photo uploadFile(MultipartFile file) {
        String fileName = photoService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/photo/")
                .path("downloadFile/")
                .path(fileName)
                .toUriString();

        String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/photo/")
                .path("get/")
                .path(fileName)
                .toUriString();

        Photo fileResourceDto = Photo.builder()
                .fileDownloadUri(fileDownloadUri)
                .fileUrl(fileUrl)
                .fileName(fileName)
                .size(file.getSize())
                .fileType(file.getContentType())
                .build();

        Photo fileResource = photoService.save(fileResourceDto);
        return fileResource;
    }

    @GetMapping("{id}")
    @ApiOperation("Получение Feed по ID")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Feed result = feedService.findById(id);
        return new ResponseEntity<>(feedMapper.toDto(result), HttpStatus.OK);
    }

    @GetMapping()
    @ApiOperation("Получение вес список Feed")
    public ResponseEntity<?> getAll(){
        List<Feed> result = feedService.getAll();

        List<FeedResponse> responses = result.stream().map(e -> feedMapper.toDto(e)).collect(Collectors.toList());
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping()
    @ApiOperation("Создание Feed")
    public ResponseEntity<?> addFeed(@ModelAttribute CreateFeedRequest createFeedRequest, @RequestPart MultipartFile file){
        Photo photo = uploadFile(file);
        Feed feed = feedMapper.inCreateEntityToEntity(createFeedRequest);
        feed.setPhoto(photo);
        Feed result = feedService.save(feed);
        return new ResponseEntity<>(feedMapper.toDto(result), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    @ApiOperation("Удаление Feed по ID")
    public ResponseEntity<?> delete(@PathVariable Long id){
        feedService.deleteById(id);
        return new ResponseEntity<>("Deleted article by ID: ", HttpStatus.OK);
    }

    @PutMapping()
    @ApiOperation("Обнавление Feed")
    public ResponseEntity<?> updateFeed(@RequestBody FeedRequest feedRequest){
        Feed result = feedService.update(feedMapper.toEntity(feedRequest));
        return new ResponseEntity<>(feedMapper.toDto(result), HttpStatus.OK);
    }

    @PutMapping("{id}/{statusid}")
    @ApiOperation("Обнавление статус Feed по ID и StatusId")
    public ResponseEntity<?> updateStatus(@PathVariable Long id, @PathVariable Long statusid){
        Feed result = feedService.updateStatus(id, statusid);
        return new ResponseEntity<>(feedMapper.toDto(result), HttpStatus.OK);
    }
}
