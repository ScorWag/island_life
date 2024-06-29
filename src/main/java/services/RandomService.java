package services;

import java.util.concurrent.ThreadLocalRandom;

public class RandomService {
    private static RandomService randomService;
    private final ThreadLocalRandom threadLocalRandom;

    private RandomService() {
        threadLocalRandom = ThreadLocalRandom.current();
    }

    public static RandomService getInstance() {
        if (randomService == null || !randomService.threadLocalRandom.equals(ThreadLocalRandom.current())) {
            return randomService = new RandomService();
        } else return randomService;
    }

    public boolean isSuccess(int chance) {
        return threadLocalRandom.nextInt(100) < chance;
    }

    public int getDirection() {
        if (nextBoolean()) return 1;
        else return -1;
    }

    public int nextInt(int bound) {
        return threadLocalRandom.nextInt(bound);
    }

    public float nextFloat(float bound) {
        return threadLocalRandom.nextFloat(bound);
    }

    public boolean nextBoolean() {
        return threadLocalRandom.nextBoolean();
    }
}
