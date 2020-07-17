package kz.devhils.meathouse.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_services")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AnimalService> services;
}
