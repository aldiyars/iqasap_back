package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.OrderAnimal;

import java.util.List;

public interface OrderAnimalService {
    OrderAnimal save(OrderAnimal orderAnimal);
    void deleteById(Long id);
    OrderAnimal getById(Long id);
    List<OrderAnimal> getAll();
}
