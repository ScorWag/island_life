package services;

import data.Animal;
import main.Location;

public class MoveAnimalService {
    private static MoveAnimalService moveAnimalService;

    private MoveAnimalService() {}

    public static MoveAnimalService getInstance() {
        if (moveAnimalService == null) moveAnimalService = new MoveAnimalService();
        return moveAnimalService;
    }


    public void move(Animal animal, Location from, Location to) {
        from.getPopulation().get(animal.getAnimalType()).remove(animal);
        to.getPopulation().get(animal.getAnimalType()).add(animal);

        animal.setCoordinateX(to.getX());
        animal.setCoordinateY(to.getY());
    }
}
