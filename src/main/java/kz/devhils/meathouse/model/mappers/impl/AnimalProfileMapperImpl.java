package kz.devhils.meathouse.model.mappers.impl;

import kz.devhils.meathouse.model.dtos.response.AnimalResponse;
import kz.devhils.meathouse.model.dtos.response.AnimalServiceResponse;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.model.mappers.AnimalMapper;
import kz.devhils.meathouse.model.mappers.AnimalProfileMapper;
import kz.devhils.meathouse.model.dtos.request.AnimalProfileReq;
import kz.devhils.meathouse.model.dtos.response.AnimalProfileResponse;
import kz.devhils.meathouse.service.AnimalService;
import kz.devhils.meathouse.service.AnimalServiceService;
import kz.devhils.meathouse.service.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AnimalProfileMapperImpl implements AnimalProfileMapper {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalServiceService animalServiceService;
    @Autowired
    private AnimalMapper animalMapper;

    @Override
    public AnimalProfile toEntity(AnimalProfileReq a) {
        Animal animal = animalService.findById(a.getAnimalId());

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

        animalProfile.setAnimalServices(animalServices);

        return animalProfile;
    }

    @Override
    public AnimalProfileResponse toDto(AnimalProfile animalProfile) {
        AnimalProfileResponse result = new AnimalProfileResponse();

        List<String> photos = new ArrayList<>();
        for (int i = 0; i < animalProfile.getPhotos().size(); i++){
            photos.add(animalProfile.getPhotos().get(i).getFileUrl());
        }

        List<AnimalServiceResponse> animalServiceResponseList = new ArrayList<>();
        for (int i = 0; i < animalProfile.getAnimalServices().size(); i++){
            AnimalServiceResponse animalServiceResponse = new AnimalServiceResponse();
            animalServiceResponse.setId(animalProfile.getAnimalServices().get(i).getId());
            animalServiceResponse.setServiceName(animalProfile.getAnimalServices().get(i).getService().getName());
            animalServiceResponse.setAnimalName(animalProfile.getAnimalServices().get(i).getAnimal().getName());
            animalServiceResponse.setCost(animalProfile.getAnimalServices().get(i).getCost());

            animalServiceResponseList.add(animalServiceResponse);
        }

        result.setAnimalServiceResponses(animalServiceResponseList);
        result.setId(animalProfile.getId());
        result.setAnimal(animalMapper.toDto(animalProfile.getAnimal()));
        result.setAge(animalProfile.getAge());
        result.setColor(animalProfile.getColor());
        result.setWeight(animalProfile.getWeight());
        result.setBreed(animalProfile.getBreed());
        result.setGender(animalProfile.getGender());
        result.setCost(animalProfile.getCost());
        result.setPhotos(photos);

        return result;
    }
}
