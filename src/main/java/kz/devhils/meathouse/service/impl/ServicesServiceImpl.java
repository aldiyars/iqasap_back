package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Service;
import kz.devhils.meathouse.repositories.ServicesRepo;
import kz.devhils.meathouse.service.ServicesService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServicesServiceImpl implements ServicesService {

    @Autowired
    private ServicesRepo servicesRepo;

    @Override
    public Service findById(Long id) {
        Service result = servicesRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<Service> getAll() {
        List<Service> result = servicesRepo.findAll();
        return result;
    }

    @Override
    public Service save(Service service) {
        Service result = servicesRepo.save(service);
        return result;
    }

    @Override
    public Service update(Service service) {
        if (service.getId() != null && findById(service.getId()) != null){
            servicesRepo.save(service);
        }
        return service;
    }

    @Override
    public void delete(Service service) {
        servicesRepo.delete(service);
    }

    @Override
    public void deleteById(Long id) {
        servicesRepo.deleteById(id);
    }
}
