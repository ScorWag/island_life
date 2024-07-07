package services;

import data.Animal;
import main.Island;
import main.Location;

import java.util.Stack;

public class MoveAnimalService {
    private static MoveAnimalService moveAnimalService;
    private final BuildWayService buildWayService;
    private Island island;

    private MoveAnimalService(Island island) {
        this.island = island;
        buildWayService = BuildWayService.getInstance();
    }

    public static MoveAnimalService getInstance(Island island) {
        if (moveAnimalService == null || !moveAnimalService.island.equals(island)) {
            moveAnimalService = new MoveAnimalService(island);
        }
        return moveAnimalService;
    }


    public void move(Animal animal) {
        Location start = island.getIslandMap()[animal.getCoordinateX()][animal.getCoordinateY()];
        Stack<Location> way = buildWayService.buildWay(animal.getMaxSpeed(), start, island.getIslandMap());
        Location locationMove;

        while (!way.isEmpty()) {
            locationMove = way.pop();
            synchronized (locationMove) {
                if (locationMove.getPopulation().get(animal.getAnimalType()).size() <
                        island.getCapacityLocation().get(animal.getAnimalType())) {
                    moveAnimalService.move(animal, start, locationMove);
                    return;
                }
            }
        }
    }


    private void move(Animal animal, Location from, Location to) {
        from.getPopulation().get(animal.getAnimalType()).remove(animal);
        to.getPopulation().get(animal.getAnimalType()).add(animal);

        animal.setCoordinateX(to.getX());
        animal.setCoordinateY(to.getY());
    }
}
