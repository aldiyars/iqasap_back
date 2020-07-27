package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.Statuses;

import java.util.List;

public interface StatusService {

    Statuses findById(Long id);
    List<Statuses> getAll();
    Statuses addStatus(Statuses status);
    Statuses update(Statuses status);
    void delete(Statuses status);
    void deleteById(Long id);
}
