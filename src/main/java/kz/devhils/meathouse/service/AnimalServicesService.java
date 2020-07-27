package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.AnimalServices;

import java.util.List;

public interface AnimalServicesService {

    AnimalServices findById(Long id);
    List<AnimalServices> getAll();
    AnimalServices add(AnimalServices service);
    AnimalServices update(AnimalServices service);

}
