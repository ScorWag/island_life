package tasks;

import main.Location;
import services.FeedService;

import java.util.concurrent.Callable;


public class FeedAnimalsTask implements Callable<Void> {
    private final FeedService feedService;
    private Location location;

    public FeedAnimalsTask(FeedService feedService, Location location) {
        this.feedService = feedService;
        this.location = location;
    }

    @Override
    public Void call() {
        synchronized (location) {
            feedService.feed(location);
        }
        return null;
    }
}
