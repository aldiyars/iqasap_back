package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.PaymentType;
import kz.devhils.meathouse.model.entities.Status;
import kz.devhils.meathouse.repositories.PaymentTypeRepo;
import kz.devhils.meathouse.service.PaymentTypeService;
import kz.devhils.meathouse.service.StatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentTypeServiceImpl implements PaymentTypeService {

    @Autowired
    private PaymentTypeRepo paymentTypeRepo;
    @Autowired
    private StatusService statusService;

    @Override
    public PaymentType findById(Long id) {
        PaymentType result = paymentTypeRepo.findById(id).orElse(null);
        return result;
    }

    @Override
    public List<PaymentType> getAll() {
        List<PaymentType> result = paymentTypeRepo.findAll();
        return result;
    }

    @Override
    public PaymentType save(PaymentType paymentType) {
        Status status = statusService.findByName("active");
        paymentType.setStatus(status);
        PaymentType result = paymentTypeRepo.save(paymentType);
        return result;
    }

    @Override
    public PaymentType update(PaymentType paymentType) {
        if(paymentType.getId() != null && findById(paymentType.getId()) != null){
            paymentTypeRepo.save(paymentType);
        }
        return paymentType;
    }

    @Override
    public void delete(PaymentType paymentType) {
        paymentTypeRepo.delete(paymentType);
    }

    @Override
    public void deleteById(Long id) {
        paymentTypeRepo.deleteById(id);
    }

    @Override
    public PaymentType updateStatus(Long id, Status status) {
        PaymentType result = findById(id);
        result.setStatus(status);
        paymentTypeRepo.save(result);
        return result;
    }
}
