package data;

import lombok.*;

@AllArgsConstructor
public class AnimalContext {
    private final AnimalType animalType;
    private final float weight;
    private final int maxSpeed;
    private final float foodRequired;
}
