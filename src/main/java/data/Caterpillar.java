package data;

public class Caterpillar extends Animal {
    private static int countInstances = 0;

    public Caterpillar(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.CATERPILLAR, AnimalType.CATERPILLAR + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
