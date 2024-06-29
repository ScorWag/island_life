package data;

public class Boar extends Animal {
    private static int countInstances = 0;

    public Boar(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.BOAR, AnimalType.BOAR + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
