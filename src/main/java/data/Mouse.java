package data;

public class Mouse extends Animal {
    private static int countInstances = 0;

    public Mouse(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.MOUSE, AnimalType.MOUSE + " " + ++countInstances,
                weight, maxSpeed,  satiation,  foodRequired);
    }
}
