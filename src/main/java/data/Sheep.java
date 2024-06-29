package data;

public class Sheep extends Animal {
    private static int countInstances = 0;

    public Sheep(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.SHEEP, AnimalType.SHEEP + " " + ++countInstances,
                weight, maxSpeed,  satiation,  foodRequired);
    }
}
