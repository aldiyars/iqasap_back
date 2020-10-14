package kz.devhils.meathouse.model.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


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
    private List<Long> animalServiceIds;
}
