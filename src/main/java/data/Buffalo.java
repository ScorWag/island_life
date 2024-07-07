package data;

import prototypes.AnimalPrototype;

public class Buffalo extends Animal {
    private static int countInstances = 0;

    public Buffalo(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
