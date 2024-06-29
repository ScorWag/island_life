package data;

public class Deer extends Animal {
    private static int countInstances = 0;

    public Deer(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.DEER, AnimalType.DEER + " " + ++countInstances,
                weight, maxSpeed,  satiation, foodRequired);
    }
}
