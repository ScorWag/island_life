package tasks;

import data.Animal;
import lombok.AllArgsConstructor;
import services.BreedingService;

import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class BreedAnimalsTask implements Callable<Void> {
    private BreedingService breedingService;
    private CopyOnWriteArrayList<Animal> population;


    @Override
    public Void call() {
        synchronized (population) {
            breedingService.breed(population);
        }
        return null;
    }
}
