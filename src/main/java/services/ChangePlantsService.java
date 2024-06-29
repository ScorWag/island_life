package services;

import main.Location;

public class ChangePlantsService {
    private static ChangePlantsService changePlantsService;


    private ChangePlantsService () {}

    public static ChangePlantsService getInstance() {
        if (changePlantsService == null) changePlantsService = new ChangePlantsService();
        return changePlantsService;
    }

    public void increasePlants(Location where, float howMuch) {
        float werePlants = where.getPlants();
        where.setPlants(werePlants + howMuch);
    }

    public float decreasePlants(Location where, float howMuch) {
        float werePlants = where.getPlants();
        where.setPlants(werePlants - howMuch);
        return werePlants - where.getPlants();
    }
}
