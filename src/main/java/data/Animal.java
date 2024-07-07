package data;

import lombok.*;
import prototypes.AnimalPrototype;

@ToString
@Getter
public abstract class Animal {

    AnimalPrototype animalPrototype;

    private final String name;

    private volatile float satiation;
    @Setter
    private volatile boolean isAlive;

    @Setter
    private volatile boolean isMove;

    @Setter
    private volatile int coordinateX;

    @Setter
    private volatile int coordinateY;



    public Animal(AnimalPrototype animalPrototype, String name) {
        this.animalPrototype = animalPrototype;
        this.name = name;
        isAlive = true;
        isMove = false;
        satiation = 0;
    }

    public void setSatiation(float satiation) {
        this.satiation = satiation < 0 ? 0 : Math.min(satiation, animalPrototype.getFoodRequired());
    }
}

