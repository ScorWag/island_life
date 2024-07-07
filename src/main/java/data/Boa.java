package data;

import prototypes.AnimalPrototype;

public class Boa extends Animal{
    private static int countInstances = 0;

    public Boa(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
