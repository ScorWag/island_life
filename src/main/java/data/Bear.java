package data;

import prototypes.AnimalPrototype;

public class Bear extends Animal {
    private static int countInstances = 0;

    public Bear(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
