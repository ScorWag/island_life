package services;

import data.Animal;
import main.Island;
import main.Location;

import java.util.Stack;

public class MovingService {
    private final Island island;
    private final BuildWayService buildWayService;
    private final MoveAnimalService moveAnimalService;


    public MovingService(Island island) {
        this.island = island;
        buildWayService = BuildWayService.getInstance();
        moveAnimalService = MoveAnimalService.getInstance();
    }

    public void move (Animal animal) {
        if (animal.isAlive()  && animal.getSatiation() >= 0 && !animal.isMove()) {
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
            animal.setSatiation(animal.getSatiation() - (animal.getFoodRequired() / 1.5F));
            animal.setMove(true);
        } else if (!animal.isMove()) animal.setAlive(false);
    }
}
