package data;

public class Boa extends Animal{
    private static int countInstances = 0;

    public Boa(float weight, int maxSpeed, float satiation, float foodRequired) {
        super(AnimalType.BOA, AnimalType.BOA + " " + ++countInstances,
                weight, maxSpeed,  satiation,  foodRequired);
    }
}
