package main;

import data.Animal;
import data.AnimalType;

import lombok.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Getter
public class Location {
    private final int x;
    private final int y;
    private ConcurrentHashMap<AnimalType, CopyOnWriteArrayList<Animal>> population;
    private volatile float plants;
    private final Map<AnimalType, Integer> capacity;

    public Location(int x, int y, Map<AnimalType, Integer> capacity) {
        this.x = x;
        this.y = y;
        population = new ConcurrentHashMap<>();
        this.capacity = capacity;
    }

    public void setPlants(float plants) {
        if (plants < 0) {
            this.plants = 0;
            return;
        }
        if (plants > capacity.get(AnimalType.PLANTS)) {
            this.plants = capacity.get(AnimalType.PLANTS);
            return;
        }
        this.plants = plants;
    }

    @Override
    public String toString() {
        Map<AnimalType, Integer> countsAnimal = population.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().size()));
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                ", population=" + countsAnimal +
                ", plants=" + plants +
                '}';
    }
}
