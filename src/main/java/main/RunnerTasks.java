package main;

import data.Animal;
import services.*;
import tasks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RunnerTasks implements Runnable {
    private long start;
    private long resetFlagsMoveTime;
    private long firstCleanTime = 0;
    private long secondCleanTime = 0;
    private long createPopulationsListTime;
    private long feedTime;
    private long breedTime;
    private long moveTime;
    private List<Location> locations;
    private final PlantsGrowthService plantsGrowthService;
    private final MovingService movingService;
    private final FeedService feedService;
    private final BreedingService breedingService;
    private final ExecutorService executors;


    public RunnerTasks(List<Location> locations, PlantsGrowthService plantsGrowthService, MovingService movingService,
                       FeedService feedService, BreedingService breedingService, ExecutorService executors) {
        this.locations = locations;
        this.plantsGrowthService = plantsGrowthService;
        this.movingService = movingService;
        this.feedService = feedService;
        this.breedingService = breedingService;
        this.executors = executors;
    }


    @Override
    public void run() {
//        System.out.println("-----------------------------------\n");
//        System.out.println("Начало цикла\n");
//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Сброс флагов передвижения\n");
        start = System.currentTimeMillis();

        List<Callable<Void>> resetFlagsMoveTasks = new ArrayList<>();
        for (Location location : locations) {
            resetFlagsMoveTasks.add(new ResetIsMoveFlagTask(location));
        }

        try {
            executors.invokeAll(resetFlagsMoveTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        resetFlagsMoveTime = System.currentTimeMillis() - start;
//        System.out.println("-----------------------------------\n");
//
//
//        System.out.println("Первое очищение острова от мертвых животных\n");
        start = System.currentTimeMillis();
        List<Callable<Void>> cleaningTasks1 = new ArrayList<>();
        for (Location location : locations) {
            cleaningTasks1.add(new CleanDeadAnimalsTask(location));
        }

        try {
            executors.invokeAll(cleaningTasks1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        firstCleanTime = System.currentTimeMillis() - start;
//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Кормление животных\n");
        start = System.currentTimeMillis();

        List<Callable<Void>> feedTasks = new ArrayList<>();

        for (Location location : locations) {
            feedTasks.add(new FeedAnimalsTask(feedService, location));
        }

        try {
            executors.invokeAll(feedTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        feedTime = System.currentTimeMillis() - start;
//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Второе очищение острова от мертвых животных\n");
        start = System.currentTimeMillis();

        List<Callable<Void>> cleaningTasks2 = new ArrayList<>();

        for (Location location : locations) {
            cleaningTasks2.add(new CleanDeadAnimalsTask(location));
        }

        try {
            executors.invokeAll(cleaningTasks2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        secondCleanTime = System.currentTimeMillis() - start;
//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Создание списка популяций\n");
        start = System.currentTimeMillis();

        CopyOnWriteArrayList<CopyOnWriteArrayList<Animal>> populationsList = new CopyOnWriteArrayList<>();
        List<Callable<Void>> createPopulationsListTasks = new ArrayList<>();

        for (Location location : locations) {
            createPopulationsListTasks.add(new CreatePopulationsListTask(populationsList, location));
        }

        try {
            executors.invokeAll(createPopulationsListTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        createPopulationsListTime = System.currentTimeMillis() - start;
//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Размножение животных\n");
        start = System.currentTimeMillis();

        List<Callable<Void>> breedTasks = new ArrayList<>();

        for (CopyOnWriteArrayList<Animal> population : populationsList) {
            breedTasks.add(new BreedAnimalsTask(breedingService, population));
        }

        try {
            executors.invokeAll(breedTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        breedTime = System.currentTimeMillis() - start;
//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Перемещение животных\n");
        start = System.currentTimeMillis();

        List<Callable<Void>> moveTasks = new ArrayList<>();

        for (Location location : locations) {
            moveTasks.add(new MoveAnimalTask(movingService, location));
        }

        try {
            executors.invokeAll(moveTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        moveTime = System.currentTimeMillis() - start;
//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Рост растительности\n");

        plantsGrowthService.plantsGrowthAllLocations(locations);

//        System.out.println("-----------------------------------\n");
//
//        System.out.println("Сброс флагов передвижения занял " + resetFlagsMoveTime + " ms");
//        System.out.println("Очищение острова от мертвых животных заняло " + firstCleanTime + " ms");
//        System.out.println("Кормление животных заняло " + feedTime + " ms");
//        System.out.println("Очищение острова от мертвых животных заняло " + secondCleanTime + " ms");
//        System.out.println("Создание списка всех популяций заняло " + createPopulationsListTime + " ms");
//        System.out.println("Размножение животных заняло " + breedTime + " ms");
//        System.out.println("Перемещение животных заняло " + moveTime + " ms");
//
//
//
//        System.out.println("Конец цикла\n");
    }
}
