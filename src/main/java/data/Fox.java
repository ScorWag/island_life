package data;

public class Fox extends Animal {
    private static int countInstances = 0;
    public Fox(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.FOX, AnimalType.FOX + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
