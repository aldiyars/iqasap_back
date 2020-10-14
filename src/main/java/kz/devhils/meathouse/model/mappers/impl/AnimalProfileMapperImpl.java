package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.model.mappers.AnimalProfileMapper;
import kz.devhils.meathouse.model.dtos.request.AnimalProfileReq;
import kz.devhils.meathouse.model.dtos.response.AnimalProfileRes;
import kz.devhils.meathouse.service.AnimalService;
import kz.devhils.meathouse.service.AnimalServiceService;
import kz.devhils.meathouse.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AnimalProfileMapperImpl implements AnimalProfileMapper {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalServiceService animalServiceService;
    @Autowired
    private StatusService statusService;

    @Override
    public AnimalProfile toEntity(AnimalProfileReq a) {
        Animal animal = animalService.findById(a.getAnimalId());
        Status status = statusService.findById(a.getStatusId());

        List<kz.devhils.meathouse.model.entities.AnimalService> animalServices = new ArrayList<>();
        if (a.getAnimalServiceIds().size() >=1){
            int ids = a.getAnimalServiceIds().size();
            for (int i = 0; i < ids; i++){
                kz.devhils.meathouse.model.entities.AnimalService animalService = animalServiceService.findById(a.getAnimalServiceIds().get(i));
                animalServices.add(animalService);
            }
        }
        AnimalProfile animalProfile = new AnimalProfile();
        animalProfile.setAnimal(animal);
        animalProfile.setAge(a.getAge());
        animalProfile.setColor(a.getColor());
        animalProfile.setWeight(a.getWeight());
        animalProfile.setBreed(a.getBreed());
        animalProfile.setGender(a.getGender());
        animalProfile.setCost(a.getCost());
        animalProfile.setStatus(status);

        animalProfile.setAnimalServices(animalServices);

        return animalProfile;
    }



    @Override
    public AnimalProfileRes toDto(AnimalProfile animalProfile) {

        return null;
    }
}
