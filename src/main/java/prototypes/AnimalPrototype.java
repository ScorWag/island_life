package prototypes;

import data.AnimalType;
import lombok.*;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnimalPrototype {
    private AnimalType animalType;
    private float weight;
    private int maxSpeed;
    private float satiation;
    private float foodRequired;
}
