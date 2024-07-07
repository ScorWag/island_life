package services;

import data.Animal;
import data.AnimalType;
import factories.AnimalFactory;

import java.util.List;
import java.util.Map;

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
//            System.out.println("Родился " + whom);
            currentPopulation = where.size();
        }
        return currentPopulation;
    }
}
