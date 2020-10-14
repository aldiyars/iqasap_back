package kz.devhils.meathouse.repositories;

import kz.devhils.meathouse.model.entities.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Long> {

    List<Order> getByClient_Id(Long id);
    List<Order> getByModerator_id(Long id);
}
