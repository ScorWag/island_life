package main;

import data.Animal;
import services.*;
import services.breeding.BreedingService;
import services.feeding.FeedService;
import services.moving.MovingService;
import tasks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class RunnerTasks implements Runnable {
    private List<Location> locations;
    private final PlantsGrowthService plantsGrowthService;
    private final MovingService movingService;
    private final FeedService feedService;
    private final BreedingService breedingService;
    private final ExecutorService executors;


    public RunnerTasks(List<Location> locations, ServicesContainer servicesContainer, ExecutorService executors) {
        this.locations = locations;
        this.plantsGrowthService = servicesContainer.getPlantsGrowthService();
        this.movingService = servicesContainer.getMovingService();
        this.feedService = servicesContainer.getFeedService();
        this.breedingService = servicesContainer.getBreedingService();
        this.executors = executors;
    }


    @Override
    public void run() {
//        Сброс флагов передвижения

        List<Callable<Void>> resetFlagsMoveTasks = new ArrayList<>();
        for (Location location : locations) {
            resetFlagsMoveTasks.add(new ResetIsMoveFlagTask(location));
        }

        try {
            executors.invokeAll(resetFlagsMoveTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Первое очищение острова от мертвых животных

        List<Callable<Void>> cleaningTasks1 = new ArrayList<>();
        for (Location location : locations) {
            cleaningTasks1.add(new CleanDeadAnimalsTask(location));
        }

        try {
            executors.invokeAll(cleaningTasks1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Кормление животных

        List<Callable<Void>> feedTasks = new ArrayList<>();

        for (Location location : locations) {
            feedTasks.add(new FeedAnimalsTask(feedService, location));
        }

        try {
            executors.invokeAll(feedTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Второе очищение острова от мертвых животных

        List<Callable<Void>> cleaningTasks2 = new ArrayList<>();

        for (Location location : locations) {
            cleaningTasks2.add(new CleanDeadAnimalsTask(location));
        }

        try {
            executors.invokeAll(cleaningTasks2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Создание списка популяций

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

//        Размножение животных

        List<Callable<Void>> breedTasks = new ArrayList<>();

        for (CopyOnWriteArrayList<Animal> population : populationsList) {
            breedTasks.add(new BreedAnimalsTask(breedingService, population));
        }

        try {
            executors.invokeAll(breedTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


//        Перемещение животных

        List<Callable<Void>> moveTasks = new ArrayList<>();

        for (Location location : locations) {
            moveTasks.add(new MoveAnimalTask(movingService, location));
        }

        try {
            executors.invokeAll(moveTasks);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        Рост растительности

        plantsGrowthService.plantsGrowthAllLocations(locations);

    }
}
