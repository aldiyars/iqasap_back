package kz.devhils.meathouse.model.dtos.response;

import kz.devhils.meathouse.model.dtos.request.AnimalCreate;
import kz.devhils.meathouse.model.entities.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnimalCreateResp {

    Long id;
    String name;
    String photo;
    Date createdAt;


    public static AnimalCreateResp fromEntity(Animal animal){
        AnimalCreateResp result = new AnimalCreateResp();
        result.setId(animal.getId());
        result.setName(animal.getName());
        result.setPhoto(animal.getPhoto().getFileDownloadUri());
        result.setCreatedAt(animal.getCreatedAt());
        return result;
    }
}
