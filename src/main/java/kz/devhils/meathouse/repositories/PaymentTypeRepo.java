package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentTypeRepo extends JpaRepository<PaymentType, Long> {
}
