package kz.devhils.meathouse.model.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Таблица которая описывает AnimalProfile")
@AllArgsConstructor
public class AnimalProfileDto{

    private Long id;
    private Long animalId;
    private Long statusesId;
    private int age;
    private String color;
    private int weight;
    private String breed;
    private int gender;
    private Double cost;
}
