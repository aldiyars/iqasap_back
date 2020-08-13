package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Photo;
import org.apache.catalina.webresources.FileResource;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhotoRepo extends JpaRepository<Photo, Long> {

    Optional<Photo> getByFileName(String fileName);

}
