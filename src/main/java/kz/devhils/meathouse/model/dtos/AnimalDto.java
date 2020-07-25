package kz.devhils.meathouse.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel(description = "Таблица которая описывает категорию")
public class AnimalDto {
    private String name;
    private String icon;
}
