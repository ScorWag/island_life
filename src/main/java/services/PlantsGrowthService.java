package services;

import data.AnimalType;
import main.Location;

import java.util.List;

public class PlantsGrowthService {
    private static PlantsGrowthService plantsGrowthService;
    private final RandomService randomService;
    private final ChangePlantsService changePlantsService;

    private PlantsGrowthService() {
        randomService = RandomService.getInstance();
        changePlantsService = ChangePlantsService.getInstance();
    }

    public static PlantsGrowthService getInstance() {
        if (plantsGrowthService == null) plantsGrowthService = new PlantsGrowthService();
        return plantsGrowthService;
    }

    public void plantsGrowthAllLocations(List<Location> locations) {
        for (Location location : locations) {
            plantsGrowthInLocation(location);
        }
    }

    public void plantsGrowthInLocation(Location location) {
        int capacity = location.getCapacity().get(AnimalType.PLANTS);
        changePlantsService.increasePlants(location, randomService.nextInt(capacity));
    }
}
