package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.OrderAnimal;
import kz.devhils.meathouse.repositories.OrderAnimalRepo;
import kz.devhils.meathouse.service.OrderAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderAnimalServiceImpl implements OrderAnimalService {

    @Autowired
    OrderAnimalRepo orderAnimalRepo;

    @Override
    public OrderAnimal save(OrderAnimal orderAnimal) {
        OrderAnimal result = orderAnimalRepo.save(orderAnimal);
        return result;
    }

    @Override
    public void deleteById(Long id) {
        orderAnimalRepo.deleteById(id);
    }

    @Override
    public OrderAnimal getById(Long id) {
        OrderAnimal result = orderAnimalRepo.getOne(id);
        return result;
    }

    @Override
    public List<OrderAnimal> getAll() {
        List<OrderAnimal> orderAnimals = orderAnimalRepo.findAll();
        return orderAnimals;
    }
}
