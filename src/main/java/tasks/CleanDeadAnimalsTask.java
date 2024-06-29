package tasks;

import main.Location;
import services.DeadAnimalCleanerService;

import java.util.concurrent.Callable;

public class CleanDeadAnimalsTask implements Callable<Void> {

    private Location location;
    private final DeadAnimalCleanerService deadAnimalCleanerService;

    public CleanDeadAnimalsTask(Location location) {
        this.location = location;
        deadAnimalCleanerService = DeadAnimalCleanerService.getInstance();
    }

    @Override
    public Void call() {
        synchronized (location) {
            deadAnimalCleanerService.cleanDeadAnimalsInLocation(location);
        }
        return null;
    }
}
