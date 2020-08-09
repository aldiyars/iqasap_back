package kz.devhils.meathouse.model.mapper;

import kz.devhils.meathouse.model.dtos.AnimalProfileDto;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;

public class AnimalProfileMapper {

    @Autowired
    AnimalService animalService;

//    public AnimalProfile toAnimalProfile(AnimalProfileDto animalProfileDto){
//        AnimalProfile profile = new AnimalProfile();
//        profile.setAnimal(findAnimal(animalProfileDto.getAnimalId()));
//        profile.setAge(animalProfileDto.getAge());
//        profile.setColor(animalProfileDto.getColor());
//        profile.setWeight(animalProfileDto.getWeight());
//        profile.setBreed(animalProfileDto.getBreed());
//        profile.setGender(animalProfileDto.getGender());
////        profile.setPhotos((Set<Photo>) getAllPhotos(Collections.singletonList(animalProfileDto.photoId)));
//        profile.setCost(animalProfileDto.getCost());
//        return profile;
//    }

    private Animal findAnimal(Long id){
        return animalService.findById(id);
    }
}
