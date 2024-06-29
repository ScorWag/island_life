package services;

import lombok.Getter;
import main.Location;

@Getter
public class ChooseDirectionService {
    private final RandomService randomService;

    public ChooseDirectionService() {
        randomService = RandomService.getInstance();
    }

    public Location chooseNextLocationByDirection(Location currentLocation, Location[][] locations) {
        Location location;
        int x = currentLocation.getX();
        int y = currentLocation.getY();
        int d = randomService.getDirection();

        if (randomService.nextBoolean()) {
            int dx = x + d;
            if (dx < locations[0].length && dx >= 0) x = dx;
            else if (dx >= locations[0].length && dx > 0) x = x - 1;
            else if (dx < 0) x = x + 1;
        } else {
            int dy = y + d;
            if (dy < locations.length && dy >= 0) y = dy;
            else if (dy >= locations.length && dy > 0) y = y - 1;
            else if (dy < 0) y = y + 1;
        }

        location = locations[x][y];

        return location;
    }
}
