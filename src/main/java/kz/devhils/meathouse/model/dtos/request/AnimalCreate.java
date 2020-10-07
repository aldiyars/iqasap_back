package kz.devhils.meathouse.model.dtos.request;

import kz.devhils.meathouse.model.entities.Animal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnimalCreate {
    String name;

    public static Animal fromDto(AnimalCreate animal){
        Animal result = new Animal();
        result.setName(animal.getName());
        return result;
    }
}
