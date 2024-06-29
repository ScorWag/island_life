package tasks;

import data.Animal;
import data.AnimalType;
import lombok.AllArgsConstructor;
import main.Location;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class ResetIsMoveFlagTask implements Callable<Void> {
    private Location location;

    @Override
    public Void call() {
        Map<AnimalType, CopyOnWriteArrayList<Animal>> population = location.getPopulation();
        for (CopyOnWriteArrayList<Animal> entry : population.values()) {
            entry.forEach(animal -> animal.setMove(false));
        }
        return null;
    }
}
