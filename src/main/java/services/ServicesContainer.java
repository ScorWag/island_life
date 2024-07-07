package services;

import lombok.Getter;
import main.Island;
import services.breeding.BreedingService;
import services.feeding.FeedService;
import services.moving.MovingService;

@Getter
public class ServicesContainer {
    private final BreedingService breedingService;
    private final PlantsGrowthService plantsGrowthService;
    private final MovingService movingService;
    private final FeedService feedService;
    private final PrintStatisticsService printStatisticsService;


    public ServicesContainer(Island island) {
        breedingService = new BreedingService(island.getCapacityLocation());
        plantsGrowthService = PlantsGrowthService.getInstance();
        movingService = new MovingService(island);
        feedService = new FeedService(island);
        printStatisticsService = PrintStatisticsService.getInstance();
    }
}
