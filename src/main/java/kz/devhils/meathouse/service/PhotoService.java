package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.module.file.exceptions.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    String storeFile(MultipartFile file);
    Resource loadFileAsResource(String fileName);
    Photo save(Photo photo);
    boolean delete(Photo photo);
    Photo getFileByFileName(String fileName) throws MyFileNotFoundException;
    Photo findById(Long id);
}
