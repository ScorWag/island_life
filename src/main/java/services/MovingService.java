package services;

import data.Animal;
import main.Island;

public class MovingService {
    private final MoveAnimalService moveAnimalService;

    public MovingService(Island island) {
        moveAnimalService = MoveAnimalService.getInstance(island);
    }

    public void move(Animal animal) {
        if (animal.isAlive() && animal.getSatiation() >= 0 && !animal.isMove()) {
            moveAnimalService.move(animal);

            animal.setSatiation(animal.getSatiation() - (animal.getAnimalPrototype().getFoodRequired() / 1.5F));
            animal.setMove(true);
        } else if (!animal.isMove()) animal.setAlive(false);
    }
}
