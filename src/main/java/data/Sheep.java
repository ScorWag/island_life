package data;

import prototypes.AnimalPrototype;

public class Sheep extends Animal {
    private static int countInstances = 0;

    public Sheep(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
