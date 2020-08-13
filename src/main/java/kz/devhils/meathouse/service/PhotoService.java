package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.module.file.exceptions.MyFileNotFoundException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {

    public String storeFile(MultipartFile file);

    public Resource loadFileAsResource(String fileName);

    public Photo save(Photo photo);

    public boolean delete(Photo photo);

    public Photo getFileByFileName(String fileName) throws MyFileNotFoundException;

    public Photo findById(Long id);
}
