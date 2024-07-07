package services;

import data.*;
import factories.AnimalFactory;

import java.util.*;

public class AddNewAnimalsService {

    private final Map<AnimalType, Integer> limits;
    private final AnimalFactory animalFactory;


    public AddNewAnimalsService(Map<AnimalType, Integer> limits) {
        this.limits = limits;
        animalFactory = AnimalFactory.getInstance();
    }

    public int addNewAnimals(AnimalType whom, List<Animal> where, int howMany) {
        int currentPopulation = where.size();
        int maxAnimalsInLocation = limits.get(whom);

        for (int i = 0; i < howMany && currentPopulation < maxAnimalsInLocation; i++) {
            Animal animal = animalFactory.createAnimal(whom);
            animal.setMove(true);
            where.add(animal);
            currentPopulation = where.size();
        }
        return currentPopulation;
    }
}
