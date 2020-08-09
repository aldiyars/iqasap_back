package kz.devhils.meathouse.model.dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import kz.devhils.meathouse.model.entities.Animal;
import kz.devhils.meathouse.model.entities.AnimalProfile;
import kz.devhils.meathouse.model.entities.Photo;
import kz.devhils.meathouse.service.AnimalService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Таблица которая описывает AnimalProfile")
@AllArgsConstructor
public class AnimalProfileDto {

    private Long animalId;
    private int age;
    private String color;
    private String weight;
    private String breed;
    private int gender;
//    private Long photoId;
    private Double cost;

}
