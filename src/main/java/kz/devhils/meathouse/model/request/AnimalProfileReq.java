package kz.devhils.meathouse.model.request;

import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.model.entities.Statuses;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalProfileReq {

    private Long animalId;
    private Long statusId;
    private int age;
    private String color;
    private int weight;
    private String breed;
    private int gender;
    private Double cost;
}
