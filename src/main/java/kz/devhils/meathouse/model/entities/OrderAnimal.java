package kz.devhils.meathouse.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "order_animal")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderAnimal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AnimalProfile> services;
}
