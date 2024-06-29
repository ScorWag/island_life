package data;

public class Bear extends Animal {
    private static int countInstances = 0;

    public Bear(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.BEAR, AnimalType.BEAR + " " + ++countInstances,
                weight, maxSpeed,  satiation,  foodRequired);
    }
}
