package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Services;

import java.util.List;

public interface ServicesService {

    Services findById(Long id);
    List<Services> getAll();
    Services save(Services service);
    Services update(Services service);
    void delete(Services service);
    void deleteById(Long id);
}
