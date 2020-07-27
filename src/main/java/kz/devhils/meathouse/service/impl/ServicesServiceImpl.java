package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Services;
import kz.devhils.meathouse.repositories.ServicesRepo;
import kz.devhils.meathouse.service.ServicesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServicesServiceImpl implements ServicesService {

    private ServicesRepo servicesRepo;

    @Override
    public Services findById(Long id) {
        Services result = servicesRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<Services> getAll() {
        List<Services> result = servicesRepo.findAll();
        return result;
    }

    @Override
    public Services save(Services service) {
        Services result = servicesRepo.save(service);
        return result;
    }

    @Override
    public Services update(Services service) {
        if (service.getId() != null && findById(service.getId()) != null){
            servicesRepo.save(service);
        }
        return service;
    }

    @Override
    public void delete(Services service) {
        servicesRepo.delete(service);
    }

    @Override
    public void deleteById(Long id) {
        servicesRepo.deleteById(id);
    }
}
