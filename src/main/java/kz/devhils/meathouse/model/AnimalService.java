package kz.devhils.meathouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "animal_service")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Animal animalType;

    @ManyToOne(fetch = FetchType.EAGER)
    private Services service;

    @Column(name = "cost")
    private int cost;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;
}
