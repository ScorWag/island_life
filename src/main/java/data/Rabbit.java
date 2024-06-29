package data;

public class Rabbit extends Animal {
    private static int countInstances = 0;

    public Rabbit(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.RABBIT, AnimalType.RABBIT + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
