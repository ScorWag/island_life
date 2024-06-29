package services;

import data.Animal;
import data.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BreedingService {
    private final Map<AnimalType, Integer> capacityLocation;
    private final RandomService randomService;
    private final AddNewAnimalsService addNewAnimalsService;

    public BreedingService(Map<AnimalType, Integer> capacityLocation) {
        this.capacityLocation = capacityLocation;
        randomService = RandomService.getInstance();
        addNewAnimalsService = new AddNewAnimalsService(capacityLocation);
    }

    public void breed(List<Animal> population) {
        if (!population.isEmpty()) {
            int currentPopulation = population.size();
            Animal animal = population.get(0);
            int maxAnimalsInLocation = capacityLocation.get(animal.getAnimalType());
            List<Animal> freeAnimals = new ArrayList<>(population);

            while (freeAnimals.size() > 1 && currentPopulation < maxAnimalsInLocation) {
                freeAnimals.remove(0);
                freeAnimals.remove(0);
                int rnd = randomService.nextInt(2);
                currentPopulation = addNewAnimalsService.addNewAnimals(animal.getAnimalType(), population, rnd);
            }
        }
    }
}


