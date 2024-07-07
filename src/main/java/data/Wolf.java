package data;

import prototypes.AnimalPrototype;

public class Wolf extends Animal {
    private static int countInstances = 0;

    public Wolf(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
