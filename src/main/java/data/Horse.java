package data;

import prototypes.AnimalPrototype;

public class Horse extends Animal {
    private static int countInstances = 0;

    public Horse(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
