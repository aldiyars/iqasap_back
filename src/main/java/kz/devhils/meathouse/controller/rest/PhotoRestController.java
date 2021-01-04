package kz.devhils.meathouse.controller.rest;

import io.swagger.annotations.ApiOperation;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.module.file.exceptions.MyFileNotFoundException;
import kz.devhils.meathouse.service.PhotoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/photo")
@CrossOrigin(origins = "http://iqasap.taq.kz")
public class PhotoRestController {

    @Value("${image.not.found.src}")
    public String NO_IMAGE;

    @Autowired
    private PhotoService photoService;


    public Photo uploadFile(MultipartFile file) {

        String fileName = photoService.storeFile(file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/v1/files/")
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

//    @PostMapping("/uploadMultipleFiles")
//    @ApiOperation("Залить все файлы")
//    public List<Photo> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
//        return Arrays.asList(files)
//                .stream()
//                .map(file -> uploadFile(file))
//                .collect(Collectors.toList());
//    }

    @PostMapping("/uploadPhoto")
    @ApiOperation("Залить файл")
    public Photo uploadPhoto(@RequestPart MultipartFile file) {
        Photo photo = uploadFile(file);
        return photo;
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    @ApiOperation("Скачать файл")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = photoService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @GetMapping("/get/{fileName:.+}")
    @ApiOperation("Скачать файл")
    public ResponseEntity<byte[]> showFile(@PathVariable String fileName, HttpServletRequest request) throws IOException {

        Resource resource = photoService.loadFileAsResource(fileName);
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
        }

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(Files.readAllBytes(resource.getFile().toPath()));
    }

//    @GetMapping(value = "/no-image")
//    @ApiOperation("Фотография по умолчанию")
//    public @ResponseBody
//    ResponseEntity<byte[]> getFile() throws IOException {
//        File file = new ClassPathResource(NO_IMAGE).getFile();
//
//        Tika tika = new Tika();
//        String mimeType = tika.detect(file);
//        MediaType mediaType = null;
//        if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_JPEG_VALUE)) {
//            mediaType = MediaType.IMAGE_JPEG;
//        } else if (mimeType.trim().equalsIgnoreCase(MediaType.IMAGE_PNG_VALUE)) {
//            mediaType = MediaType.IMAGE_PNG;
//        }
//        return ResponseEntity.ok().contentType(mediaType).body(Files.readAllBytes(file.toPath()));
//    }

    @DeleteMapping("/deleteFile/{fileName:.+}")
    @ApiOperation("Удаление файла")
    public ResponseEntity<?> delete(@PathVariable String fileName) throws ServiceException, MyFileNotFoundException {
        Photo fileResource = (photoService.getFileByFileName(fileName));
        if (fileResource != null) {
            photoService.delete(fileResource);
        } else {
            throw new MyFileNotFoundException("file with name " + fileName + " not found");
        }
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<?> getAll(){
        List<Photo> photos = photoService.getAll();
        return new ResponseEntity<>(photos, HttpStatus.OK);
    }
}
