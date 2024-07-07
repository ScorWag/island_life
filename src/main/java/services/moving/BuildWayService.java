package services.moving;

import main.Location;
import services.RandomService;

import java.util.Stack;

public class BuildWayService {
    private static BuildWayService buildWayService;
    private final ChooseDirectionService chooseDirectionService;

    private BuildWayService() {
        chooseDirectionService = new ChooseDirectionService();
    }

    public static BuildWayService getInstance() {
        if (buildWayService == null) buildWayService = new BuildWayService();
        return buildWayService;
    }

    public Stack<Location> buildWay(int maxSpeed, Location from, Location[][] locations) {
        Stack<Location> way = new Stack<>();
        Location currentLocation = from;

        for (int i = 0; i < RandomService.getInstance().nextInt(maxSpeed); i++) {
            currentLocation = chooseDirectionService.chooseNextLocationByDirection(currentLocation, locations);
            if (!currentLocation.equals(from)) way.add(currentLocation);
        }

        return way;
    }
}
