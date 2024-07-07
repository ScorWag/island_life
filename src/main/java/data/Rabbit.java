package data;

import prototypes.AnimalPrototype;

public class Rabbit extends Animal {
    private static int countInstances = 0;

    public Rabbit(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
