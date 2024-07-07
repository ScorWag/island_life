package data;

import prototypes.AnimalPrototype;

public class Mouse extends Animal {
    private static int countInstances = 0;

    public Mouse(AnimalPrototype animalPrototype) {
        super(animalPrototype, animalPrototype.getAnimalType() + " " + ++countInstances);
    }
}
