package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.module.file.configurations.FileStorageProperties;
import kz.devhils.meathouse.module.file.exceptions.FileStorageException;
import kz.devhils.meathouse.module.file.exceptions.MyFileNotFoundException;
import kz.devhils.meathouse.repositories.PhotoRepo;
import kz.devhils.meathouse.service.PhotoService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
public class PhotoStorageServiceImpl implements PhotoService {


    private final Path fileStorageLocation;
    @Autowired
    private PhotoRepo photoRepo;

    @Autowired
    public PhotoStorageServiceImpl(FileStorageProperties fileStorageProperties,
                                   PhotoRepo photoRepo) {

        this.photoRepo = photoRepo;
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            fileName = System.currentTimeMillis() + "_" + fileName;
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("FileResource not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("FileResource not found " + fileName, ex);
        }
    }


    public Photo save(Photo photo) {
        return photoRepo.save(photo);
    }

    public boolean delete(Photo photo) {
        Resource resource = loadFileAsResource(photo.getFileName());
        try {
            resource.getFile().delete();
        } catch (Exception e) {
            throw new MyFileNotFoundException("FileResource not found " + photo.getFileName());
        }
        photoRepo.delete(photo);
        return true;
    }

    @Override
    public Photo getFileByFileName(String fileName) throws MyFileNotFoundException {
        return photoRepo.getByFileName(fileName).orElse(null);
    }

    @Override
    public Photo findById(Long id) {
        return photoRepo.getOne(id);
    }

    @Override
    public List<Photo> getAll() {
        List<Photo> photos = photoRepo.findAll();
        return photos;
    }


}
