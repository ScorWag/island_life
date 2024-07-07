package data;

import prototypes.AnimalPrototype;

public class Boar extends Animal {
    private static int countInstances = 0;

    public Boar(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
