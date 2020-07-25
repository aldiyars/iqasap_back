package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
