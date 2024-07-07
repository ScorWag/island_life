package data;

import prototypes.AnimalPrototype;

public class Goat extends Animal {
    private static int countInstances = 0;

    public Goat(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
