package kz.devhils.meathouse.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "t_orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User client;

    @ManyToOne(fetch = FetchType.EAGER)
    private User moderator;

    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentType paymentType;

    @Column(name = "is_payed")
    private int isPayed;

    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<OrderAnimal> orderAnimals;

    @Column(name = "total")
    private double total;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt = new Date();

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }
}
