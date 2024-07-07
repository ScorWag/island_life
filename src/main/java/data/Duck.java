package data;

import prototypes.AnimalPrototype;

public class Duck extends Animal {
    private static int countInstances = 0;

    public Duck(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
