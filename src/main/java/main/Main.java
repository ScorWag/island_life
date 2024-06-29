package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import prototypes.IslandPrototype;
import services.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.*;

import static main.Resources.*;

public class Main {
    public static void main(String[] args) throws IOException {
        String islandConfigFile = ISLAND_CONFIG.getValue();
        String chanceToEatFile = CHANCE_TO_EAT.getValue();
        long start;
        long preparationTime;


//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Подготовка\n");
//
//        start = System.currentTimeMillis();

        IslandPrototype islandPrototype = new ObjectMapper()
                .readValue(Path.of(islandConfigFile).toFile(), IslandPrototype.class);
        islandPrototype.setChancesToEatInfo(chanceToEatFile);

        Island island = new Island(islandPrototype);

        island.init();

        Location[][] islandMap = island.getIslandMap();
        CopyOnWriteArrayList<Location> locations = new CopyOnWriteArrayList<>();

        for (Location[] value : islandMap) {
            locations.addAll(Arrays.asList(value).subList(0, islandMap[0].length));
        }

        PlantsGrowthService plantsGrowthService = PlantsGrowthService.getInstance();
        MovingService movingService = new MovingService(island);
        FeedService feedService = new FeedService(island);
        BreedingService breedingService = new BreedingService(island.getCapacityLocation());
        ExecutorService executors = Executors.newCachedThreadPool();
        PrintStatisticsService printStatisticsService = PrintStatisticsService.getInstance();

//        preparationTime = System.currentTimeMillis() - start;
//        System.out.println("Подготовка заняла " + preparationTime  + " ms");

       Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new RunnerTasks(
                locations, plantsGrowthService, movingService, feedService, breedingService, executors
        ), 0, 1, TimeUnit.SECONDS);

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(() -> {
            try {
                printStatisticsService.printAllPopulations(locations);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, 5, 3, TimeUnit.SECONDS);
    }
}