package kz.devhils.meathouse.service.impl;

import kz.devhils.meathouse.model.entities.PaymentType;
import kz.devhils.meathouse.model.entities.Statuses;
import kz.devhils.meathouse.repositories.PaymentTypeRepo;
import kz.devhils.meathouse.service.PaymentTypeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private PaymentTypeRepo paymentTypeRepo;

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
    public PaymentType updateStatus(Long id, Statuses status) {
        PaymentType result = findById(id);
        result.setStatus(status);
        paymentTypeRepo.save(result);
        return result;
    }
}
