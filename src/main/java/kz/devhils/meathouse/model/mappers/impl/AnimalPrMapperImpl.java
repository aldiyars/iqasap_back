package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Statuses;
import kz.devhils.meathouse.model.mappers.AnimalProfileMapper;
import kz.devhils.meathouse.model.request.AnimalProfileReq;
import kz.devhils.meathouse.model.response.AnimalProfileRes;
import kz.devhils.meathouse.service.AnimalService;
import kz.devhils.meathouse.service.PhotoService;
import kz.devhils.meathouse.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AnimalPrMapperImpl implements AnimalProfileMapper {

    private AnimalService animalService;
    private StatusService statusService;
    private PhotoService photoService;

    @Override
    public AnimalProfile toEntity(AnimalProfileReq a) {
        Animal animal = animalService.findById(a.getAnimalId());
        Statuses statuses = statusService.findById(a.getStatusId());
//        List<Photo> photos = new ArrayList<>();
//        for (int i = 0; i < a.getPhotos().size(); i++){
//            Photo photo = photoService.findById(((List<Long>) a.getPhotos()).get(i));
//            photos.add(photo);
//        }

        AnimalProfile animalProfile = new AnimalProfile();
        animalProfile.setAnimal(animal);
        animalProfile.setAge(a.getAge());
        animalProfile.setColor(a.getColor());
        animalProfile.setWeight(a.getWeight());
        animalProfile.setBreed(a.getBreed());
        animalProfile.setGender(a.getGender());
//        animalProfile.setPhotos((Set<Photo>) photos);
        animalProfile.setCost(a.getCost());
        animalProfile.setStatuses(statuses);

        return animalProfile;
    }



    @Override
    public AnimalProfileRes toDto(AnimalProfile animalProfile) {

        return null;
    }
}
