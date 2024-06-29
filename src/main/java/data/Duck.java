package data;

public class Duck extends Animal {
    private static int countInstances = 0;

    public Duck(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.DUCK, AnimalType.DUCK + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
