package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.AnimalService;

import java.util.List;

public interface AnimalServiceService {

    AnimalService findById(Long id);
    List<AnimalService> getAll();
    AnimalService add(AnimalService service);
    AnimalService update(AnimalService service);
    void deleteById(Long id);
}
