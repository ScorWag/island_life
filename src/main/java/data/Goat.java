package data;

public class Goat extends Animal {
    private static int countInstances = 0;

    public Goat(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.GOAT, AnimalType.GOAT + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
