package data;

public class Horse extends Animal {
    private static int countInstances = 0;

    public Horse(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.HORSE, AnimalType.HORSE + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
