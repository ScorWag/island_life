package factories;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import prototypes.AnimalPrototype;

import java.io.IOException;
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

            case WOLF -> animal = new Wolf(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case BOA -> animal = new Boa(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case FOX -> animal = new Fox(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case BEAR -> animal = new Bear(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case EAGLE -> animal = new Eagle(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case HORSE -> animal = new Horse(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case DEER -> animal = new Deer(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case RABBIT -> animal = new Rabbit(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case MOUSE -> animal = new Mouse(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case GOAT -> animal = new Goat(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case SHEEP -> animal = new Sheep(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case BOAR -> animal = new Boar(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case BUFFALO -> animal = new Buffalo(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case DUCK -> animal = new Duck(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

            case CATERPILLAR -> animal = new Caterpillar(prototype.getWeight(), prototype.getMaxSpeed(),
                    prototype.getSatiation(), prototype.getFoodRequired());

        }

        return animal;
    }

    private void setAnimalPrototypes(String animalsJsonFile) {
        try {
            List<AnimalPrototype> animalJsonList = new ObjectMapper().readValue(Path.of(animalsJsonFile).toFile(),
                    new TypeReference<List<AnimalPrototype>>() {});
            for (AnimalPrototype animalJson : animalJsonList) {
                animalPrototypes.put(animalJson.getAnimalType(), animalJson);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
