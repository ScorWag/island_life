package data;

import prototypes.AnimalPrototype;

public class Fox extends Animal {
    private static int countInstances = 0;

    public Fox(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
