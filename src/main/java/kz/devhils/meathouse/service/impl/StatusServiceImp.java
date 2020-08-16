package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Statuses;
import kz.devhils.meathouse.repositories.StatusRepo;
import kz.devhils.meathouse.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class StatusServiceImp implements StatusService {

    private StatusRepo statusRepo;

    @Override
    public Statuses findById(Long id) {
        Statuses result = statusRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<Statuses> getAll() {
        List<Statuses> result = statusRepo.findAll();
        return result;
    }

    @Override
    public Statuses addStatus(Statuses status) {
        Statuses result = statusRepo.save(status);
        return result;
    }

    @Override
    public Statuses update(Statuses status) {
        if (status.getId() != null && statusRepo.findById(status.getId()).orElse(null) != null){
            statusRepo.save(status);
        }
        return status;
    }

    @Override
    public void delete(Statuses status) {
        statusRepo.delete(status);
    }

    @Override
    public void deleteById(Long id) {
        statusRepo.deleteById(id);
    }
}
