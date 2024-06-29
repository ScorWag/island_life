package tasks;

import data.Animal;
import lombok.AllArgsConstructor;
import main.Location;

import java.util.concurrent.Callable;
import java.util.concurrent.CopyOnWriteArrayList;

@AllArgsConstructor
public class CreatePopulationsListTask implements Callable<Void> {
    private CopyOnWriteArrayList<CopyOnWriteArrayList<Animal>> populationsList;
    private Location location;

    @Override
    public Void call() {
        CopyOnWriteArrayList<CopyOnWriteArrayList<Animal>> populationLocationList = new CopyOnWriteArrayList<>(
                location.getPopulation().values().stream().toList()
        );
        populationsList.addAll(populationLocationList);

        return null;
    }
}
