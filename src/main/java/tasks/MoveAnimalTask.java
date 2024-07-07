package tasks;

import data.Animal;
import lombok.AllArgsConstructor;
import main.Location;
import services.moving.MovingService;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

@AllArgsConstructor
public class MoveAnimalTask implements Callable<Void> {
    private MovingService movingService;
    private Location location;

    @Override
    public Void call() {
        List<Animal> animals = new ArrayList<>(location.getPopulation().values().stream()
                .flatMap(Collection::stream)
                .toList());

        while (!animals.isEmpty()) {
            Animal animal = animals.remove(animals.size() - 1);
            movingService.move(animal);
        }

        return null;
    }
}
