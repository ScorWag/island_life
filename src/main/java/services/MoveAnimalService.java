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
        Stack<Location> way = buildWayService
                .buildWay(animal.getAnimalPrototype().getMaxSpeed(), start, island.getIslandMap());
        Location locationMove;

        while (!way.isEmpty()) {
            locationMove = way.pop();
            synchronized (locationMove) {
                if (locationMove.getPopulation().get(animal.getAnimalPrototype().getAnimalType()).size() <
                        island.getCapacityLocation().get(animal.getAnimalPrototype().getAnimalType())) {
                    moving(animal, start, locationMove);
                    return;
                }
            }
        }
    }


    private void moving(Animal animal, Location from, Location to) {
        from.getPopulation().get(animal.getAnimalPrototype().getAnimalType()).remove(animal);
        to.getPopulation().get(animal.getAnimalPrototype().getAnimalType()).add(animal);

        animal.setCoordinateX(to.getX());
        animal.setCoordinateY(to.getY());
    }
}
