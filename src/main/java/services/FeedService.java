package services;

import data.*;
import lombok.Getter;
import main.*;

import java.util.*;
import java.util.stream.Collectors;


public class FeedService {
    @Getter
    private final Island island;
    private final SearchingFoodService searchingFoodService;
    @Getter
    private final Map<AnimalType, Map<AnimalType, Integer>> chancesToEatInfo;
    private final RandomService randomService;
    private final ChangePlantsService changePlantsService;

    public FeedService(Island island) {
        this.island = island;
        searchingFoodService = new SearchingFoodService();
        chancesToEatInfo = filter(island.getChancesToEatInfo());
        randomService = RandomService.getInstance();
        changePlantsService = ChangePlantsService.getInstance();
    }

    public void feed(Location location) {
        List<Animal> animals = new ArrayList<>(location.getPopulation().values().stream()
                .flatMap(Collection::stream)
                .toList());

        while (!animals.isEmpty()) {
            Animal animal = animals.remove(randomService.nextInt(animals.size()));
            if (animal.isAlive()) {
                Optional<AnimalType> typePrey;
                Animal prey;
                typePrey = determineTypeOfPrey(animal, location);
                if (typePrey.isPresent() &&
                        randomService.isSuccess(getChancesToEatInfo().get(animal.getAnimalType()).get(typePrey.get()))) {
                    if (!typePrey.get().equals(AnimalType.PLANTS)) {
                        prey = location.getPopulation().get(typePrey.get()).get(0);
                        eatPrey(animal, prey);
                    } else eatPlants(animal, location);
                }

                if (animal.getSatiation() <= 0) {
                    animal.setAlive(false);
                }
            }
        }
    }

    public Optional<AnimalType> determineTypeOfPrey(Animal whom, Location where) {
        List<AnimalType> preysListInfo = chancesToEatInfo.get(whom.getAnimalType()).keySet().stream().toList();
        return searchingFoodService.chooseAvailableFoodType(where, preysListInfo);
    }

    public void eatPlants(Animal who, Location where) {
        float rnd = randomService.nextFloat(who.getFoodRequired());
        float plantsEating = changePlantsService.decreasePlants(where, rnd);
        who.setSatiation(who.getSatiation() + plantsEating);
    }

    public void eatPrey(Animal who, Animal prey) {
        prey.setAlive(false);
        who.setSatiation(who.getSatiation() + prey.getWeight());
    }

    private Map<AnimalType, Map<AnimalType, Integer>> filter(
            Map<AnimalType, Map<AnimalType, Integer>> chancesToEatForAllInfo) {

        Map<AnimalType, Map<AnimalType, Integer>> result = new HashMap<>(chancesToEatForAllInfo);

        for (Map.Entry<AnimalType, Map<AnimalType, Integer>> entryPreyList : result.entrySet()) {
            Map<AnimalType, Integer> valuePreyList = entryPreyList.getValue();

            result.put(entryPreyList.getKey(), valuePreyList.entrySet()
                    .stream()
                    .filter(entry -> entry.getValue() > 0)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
        }
        return result;
    }
}
