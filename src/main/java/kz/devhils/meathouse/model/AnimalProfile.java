package kz.devhils.meathouse.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "animal_profile")
public class AnimalProfile {
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
    private String weight;

    @Column(name = "breed")
    private String breed;

    @Column(name = "gender")
    private int gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Photo> photos;

    @Column(name = "cost")
    private int cost;

    @ManyToOne(fetch = FetchType.EAGER)
    private Statuses statuses;

    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt;

}
