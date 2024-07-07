package services.feeding;

import data.Animal;
import main.Location;
import services.ChangePlantsService;
import services.RandomService;

public class EatAnimalService {
    private static EatAnimalService eatAnimalService;
    private RandomService randomService;
    private ChangePlantsService changePlantsService;

    private EatAnimalService() {
        randomService = RandomService.getInstance();
        changePlantsService = ChangePlantsService.getInstance();
    }

    public static EatAnimalService getInstance() {
        if (eatAnimalService == null) eatAnimalService = new EatAnimalService();
        return eatAnimalService;
    }


    public void eatPlants(Animal who, Location where) {
        float rnd = randomService.nextFloat(who.getAnimalPrototype().getFoodRequired());
        float plantsEating = changePlantsService.decreasePlants(where, rnd);
        who.setSatiation(who.getSatiation() + plantsEating);
    }

    public void eatPrey(Animal who, Animal prey) {
        prey.setAlive(false);
        who.setSatiation(who.getSatiation() + prey.getAnimalPrototype().getWeight());
    }
}
