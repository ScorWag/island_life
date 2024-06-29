package data;

public class Eagle extends Animal {
    private static int countInstances = 0;

    public Eagle(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.EAGLE, AnimalType.EAGLE + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
