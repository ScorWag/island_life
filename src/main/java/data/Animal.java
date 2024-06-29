package data;

import lombok.*;

@ToString
@Getter
public abstract class Animal {
    @ToString.Exclude
    private final AnimalType animalType;

    private final String name;

    @ToString.Exclude
    private final float weight;

    @ToString.Exclude
    private final int maxSpeed;

    private volatile float satiation;

    @Setter
    private volatile boolean isAlive;

    @Setter
    private volatile boolean isMove;

    @Setter
    private volatile int coordinateX;

    @Setter
    private volatile int coordinateY;

    private final float foodRequired;

    public Animal(AnimalType animalType, String name, float weight, int maxSpeed,
                  float satiation, float foodRequired) {
        this.animalType = animalType;
        this.name = name;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.satiation = satiation;
        this.foodRequired = foodRequired;
        isAlive = true;
        isMove = false;
    }

    public void setSatiation(float satiation) {
        this.satiation = satiation < 0 ? 0 : Math.min(satiation, foodRequired);
    }
}

