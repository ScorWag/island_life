package main;

import data.AnimalType;
import lombok.*;
import prototypes.IslandPrototype;
import services.PopulateService;

import java.util.Map;

@Getter
@ToString
@EqualsAndHashCode
public class Island {
    private final int length;
    private final int width;
    private final Map<AnimalType, Integer> capacityLocation;
    private final Map<AnimalType, Float> foodRequired;
    private final Map<AnimalType, Map<AnimalType, Integer>> chancesToEatInfo;
    private Location[][] islandMap;
    private PopulateService populateService;

    public Island (IslandPrototype islandPrototype) {
        this.length = islandPrototype.getLength();
        this.width = islandPrototype.getWidth();
        this.capacityLocation = islandPrototype.getCapacityLocation();
        this.foodRequired = islandPrototype.getFoodRequired();
        this.chancesToEatInfo = islandPrototype.getChancesToEatInfo();

    }

    public void init() {
        populateService = new PopulateService();

        islandMap = new Location[length][width];
        for (int y = 0; y < islandMap.length; y++) {
            for (int x = 0; x < islandMap[0].length; x++) {
                Location location = new Location(x, y, capacityLocation);
                populateService.populate(location);
                islandMap[y][x] = location;
            }
        }
    }
}
