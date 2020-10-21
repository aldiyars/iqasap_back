package kz.devhils.meathouse.model.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AnimalProfileResponse {

    private Long id;
    private AnimalResponse animal;
    private int age;
    private String color;
    private int weight;
    private String breed;
    private int gender;
    private List<String> photos;
    private List<AnimalServiceResponse> animalServiceResponses;
    private Double cost;
}
