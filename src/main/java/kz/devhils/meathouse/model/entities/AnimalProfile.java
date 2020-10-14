package kz.devhils.meathouse.model.entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_animal_profile")
public class AnimalProfile{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Animal animal;

    @Column(name = "age")
    private int age;

    @Column(name = "color")
    private String color;

    @Column(name = "weight")
    private int weight;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private int gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Photo> photos;

    @OneToMany(fetch = FetchType.LAZY)
    private List<AnimalService> animalServices;

    @Column(name = "cost")
    private Double cost;

    @ManyToOne(fetch = FetchType.EAGER)
    private Status status;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt = new Date();

    @PrePersist
    public void prePersist(){
        this.createdAt = new Date();
    }

}
