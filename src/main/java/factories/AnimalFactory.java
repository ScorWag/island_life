package factories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import prototypes.AnimalPrototype;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.Resources.ANIMALS;

public class AnimalFactory {
    private static AnimalFactory animalFactory;
    private final Map<AnimalType, AnimalPrototype> animalPrototypes;
    private final String animalsJsonFile;

    private AnimalFactory() {
        animalPrototypes = new HashMap<>();
        animalsJsonFile = ANIMALS.getValue();
        setAnimalPrototypes(animalsJsonFile);
    }

    public static AnimalFactory getInstance() {
        if (animalFactory == null) animalFactory = new AnimalFactory();
        return animalFactory;
    }


    public Animal createAnimal(AnimalType type) {
        Animal animal = null;
        AnimalPrototype prototype = animalPrototypes.get(type);

        switch (type) {

            case WOLF -> animal = new Wolf(prototype);

            case BOA -> animal = new Boa(prototype);

            case FOX -> animal = new Fox(prototype);

            case BEAR -> animal = new Bear(prototype);

            case EAGLE -> animal = new Eagle(prototype);

            case HORSE -> animal = new Horse(prototype);

            case DEER -> animal = new Deer(prototype);

            case RABBIT -> animal = new Rabbit(prototype);

            case MOUSE -> animal = new Mouse(prototype);

            case GOAT -> animal = new Goat(prototype);

            case SHEEP -> animal = new Sheep(prototype);

            case BOAR -> animal = new Boar(prototype);

            case BUFFALO -> animal = new Buffalo(prototype);

            case DUCK -> animal = new Duck(prototype);

            case CATERPILLAR -> animal = new Caterpillar(prototype);

        }

        return animal;
    }

    private void setAnimalPrototypes(String animalsJsonFile) {
        try {
            List<AnimalPrototype> animalJsonList = new ObjectMapper().readValue(
                    Files.readAllBytes(Path.of(animalsJsonFile)), new TypeReference<List<AnimalPrototype>>() {});

            for (AnimalPrototype animalJson : animalJsonList) {
                animalPrototypes.put(animalJson.getAnimalType(), animalJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
