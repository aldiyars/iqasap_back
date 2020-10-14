package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Service;

import java.util.List;

public interface ServicesService {

    Service findById(Long id);
    List<Service> getAll();
    Service save(Service service);
    Service update(Service service);
    void delete(Service service);
    void deleteById(Long id);
}
