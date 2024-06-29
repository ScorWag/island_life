package services;

import data.Animal;
import data.AnimalType;

import main.Location;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;


public class DeadAnimalCleanerService{
    private static DeadAnimalCleanerService deadAnimalCleanerService;

    private DeadAnimalCleanerService() {}

    public static DeadAnimalCleanerService getInstance() {
        if (deadAnimalCleanerService == null) deadAnimalCleanerService = new DeadAnimalCleanerService();
        return deadAnimalCleanerService;
    }

    public void cleanDeadAnimalsInLocation(Location location) {
        Map<AnimalType, CopyOnWriteArrayList<Animal>> population = location.getPopulation();
        for (CopyOnWriteArrayList<Animal> entry : population.values()) {
            entry.removeIf(animal -> !animal.isAlive());
        }
    }
}
