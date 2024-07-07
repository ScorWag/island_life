package services;

import data.Animal;
import data.AnimalType;
import main.Location;

import java.util.*;
import java.util.concurrent.*;

import static data.AnimalType.PLANTS;

public class SearchingFoodService {

    private final RandomService randomService;

    public SearchingFoodService() {
        randomService = RandomService.getInstance();
    }

    public Optional<AnimalType> chooseAvailableFoodType(Location where, List<AnimalType> from) {
        ConcurrentHashMap<AnimalType, CopyOnWriteArrayList<Animal>> population = where.getPopulation();
        List<AnimalType> foodListInfo = new ArrayList<>(from);

        while (!foodListInfo.isEmpty()) {
            AnimalType preyAnimalType = foodListInfo.get(randomService.nextInt(foodListInfo.size()));

            if (preyAnimalType.equals(PLANTS) && where.getPlants() > 0) {
                return Optional.of(PLANTS);
            }

            if (population.containsKey(preyAnimalType)) {
                List<Animal> preyList = population.get(preyAnimalType);
                Optional<Animal> prey = preyList.stream().filter(Animal::isAlive).findAny();
                if (prey.isPresent()) {
                    return Optional.of(preyAnimalType);
                }
            }
            foodListInfo.remove(preyAnimalType);
        }

        return Optional.empty();
    }
}
