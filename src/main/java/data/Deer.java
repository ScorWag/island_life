package data;

import prototypes.AnimalPrototype;

public class Deer extends Animal {
    private static int countInstances = 0;

    public Deer(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
