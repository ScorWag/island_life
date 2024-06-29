package services;

import data.Animal;
import data.AnimalType;
import main.*;

import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class PopulateService {
    private final AnimalFactory animalFactory;
    private final RandomService randomService;
    private final PlantsGrowthService plantsGrowthService;

    public PopulateService() {
        animalFactory = AnimalFactory.getInstance();
        randomService = RandomService.getInstance();
        plantsGrowthService = PlantsGrowthService.getInstance();
    }

    public void populate(Location location) {
        int rnd;

        for(Map.Entry<AnimalType, Integer> entry : location.getCapacity().entrySet()) {
            if (entry.getKey().equals(AnimalType.PLANTS)) {
                plantsGrowthService.plantsGrowthInLocation(location);
            }
            else {
                rnd = randomService.nextInt(entry.getValue()) + 1;
                CopyOnWriteArrayList<Animal> animals = new CopyOnWriteArrayList<>();
                for (int i = 0; i < rnd; i++) {
                    Animal animal = animalFactory.createAnimal(entry.getKey());
                    animal.setCoordinateX(location.getX());
                    animal.setCoordinateY(location.getY());
                    animals.add(animal);
                }
                location.getPopulation().put(entry.getKey(), animals);
            }
        }
    }
}
