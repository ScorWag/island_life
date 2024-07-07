package data;

import prototypes.AnimalPrototype;

public class Caterpillar extends Animal {
    private static int countInstances = 0;

    public Caterpillar(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
