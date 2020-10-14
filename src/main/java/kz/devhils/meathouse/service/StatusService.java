package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Status;

import java.util.List;


public interface StatusService {

    Status findById(Long id);
    List<Status> getAll();
    Status addStatus(Status status);
    Status update(Status status);
    void delete(Status status);
    void deleteById(Long id);
    Status findByName(String name);
}
