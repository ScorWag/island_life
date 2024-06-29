package data;

public class Wolf extends Animal {
    private static int countInstances = 0;

    public Wolf(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.WOLF, AnimalType.WOLF + " " + ++countInstances,
                weight, maxSpeed,  satiation,  foodRequired);
    }
}
