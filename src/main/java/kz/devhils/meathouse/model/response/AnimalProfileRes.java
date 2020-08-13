package kz.devhils.meathouse.model.response;

import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.Statuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalProfileRes {

    private Long id;
    private Animal animal;
    private int age;
    private String color;
    private String weight;
    private String breed;
    private int gender;
    private Set<Photo> photos;
    private Double cost;
    private Statuses statuses;
    private Date createdAt;
}
