package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.repositories.StatusRepo;
import kz.devhils.meathouse.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusServiceImp implements StatusService {

    @Autowired
    private StatusRepo statusRepo;

    @Override
    public Status findById(Long id) {
        Status result = statusRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<Status> getAll() {
        List<Status> result = statusRepo.findAll();
        return result;
    }

    @Override
    public Status addStatus(Status status) {
        Status result = statusRepo.save(status);
        return result;
    }

    @Override
    public Status update(Status status) {
        if (status.getId() != null && statusRepo.findById(status.getId()).orElse(null) != null){
            statusRepo.save(status);
        }
        return status;
    }

    @Override
    public void delete(Status status) {
        statusRepo.delete(status);
    }

    @Override
    public void deleteById(Long id) {
        statusRepo.deleteById(id);
    }

    @Override
    public Status findByName(String name) {
        Status result = statusRepo.findByName(name);
        return result;
    }
}
