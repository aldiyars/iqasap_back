package kz.devhils.meathouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_order")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users client;

    @ManyToOne(fetch = FetchType.EAGER)
    private Users moderator;

    @ManyToOne(fetch = FetchType.EAGER)
    private PaymentTypes paymentType;

    @Column(name = "is_payed")
    private int isPayed;

    @ManyToOne(fetch = FetchType.EAGER)
    private Statuses status;

    @Column(name = "total")
    private int total;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}
