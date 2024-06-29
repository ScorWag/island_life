package data;

public class Buffalo extends Animal {
    private static int countInstances = 0;

    public Buffalo(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.BUFFALO, AnimalType.BUFFALO + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
