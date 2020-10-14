package kz.devhils.meathouse.model.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "t_order_delivery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDelivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private User deliver;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "finish_date")
    private Date finishDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;

}
