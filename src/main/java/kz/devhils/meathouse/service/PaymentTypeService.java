package kz.devhils.meathouse.service;

import kz.devhils.meathouse.model.entities.PaymentType;
import kz.devhils.meathouse.model.entities.Status;

import java.util.List;

public interface PaymentTypeService {

    PaymentType findById(Long id);
    List<PaymentType> getAll();
    PaymentType save(PaymentType paymentType);
    PaymentType update(PaymentType paymentType);
    void delete(PaymentType paymentType);
    void deleteById(Long id);
    PaymentType updateStatus(Long id, Status statuse);
}
