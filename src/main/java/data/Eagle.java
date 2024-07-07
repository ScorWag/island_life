package data;

import prototypes.AnimalPrototype;

public class Eagle extends Animal {
    private static int countInstances = 0;

    public Eagle(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
