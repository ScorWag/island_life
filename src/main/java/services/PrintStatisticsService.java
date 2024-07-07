package services;

import data.Animal;
import data.AnimalType;
import main.Location;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class PrintStatisticsService {
    private static PrintStatisticsService printStatisticsService;

    private PrintStatisticsService() {}

    public static PrintStatisticsService getInstance() {
        if (printStatisticsService == null) printStatisticsService = new PrintStatisticsService();
        return printStatisticsService;
    }

    public void printAllPopulations(List<Location> locations) throws IOException {
        Map<AnimalType, Integer> populations = new HashMap<>();
        for (Location location : locations) {
            addPopulations(location, populations);
        }

        for (Map.Entry<AnimalType, Integer> entry : populations.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        System.out.println();
    }

    private void addPopulations (Location from, Map<AnimalType, Integer> to) {
        Map<AnimalType, CopyOnWriteArrayList<Animal>> population = from.getPopulation();
        for (Map.Entry<AnimalType, CopyOnWriteArrayList<Animal>> entry : population.entrySet()) {
            if (to.containsKey(entry.getKey())) {
                to.compute(entry.getKey(), (k, currentSize) -> currentSize + entry.getValue().size());
            } else to.put(entry.getKey(), entry.getValue().size());
        }
    }
}
