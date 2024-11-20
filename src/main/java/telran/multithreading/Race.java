package telran.multithreading;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

public class Race {
    private int distance;
    private int minSleepTime;
    private int maxSleepTime;
    private AtomicInteger winner = new AtomicInteger(-1);

    public Race(int distance, int minSleepTime, int maxSleepTime) {
        this.distance = distance;
        this.minSleepTime = minSleepTime;
        this.maxSleepTime = maxSleepTime;
    }

    public int getDistance() {
        return distance;
    }

    public int getRandomSleepTime() {
        return ThreadLocalRandom.current().nextInt(minSleepTime, maxSleepTime + 1);
    }

    public boolean setWinner(int racerNumber) {
        return winner.compareAndSet(-1, racerNumber);
    }

    public int getWinner() {
        return winner.get();
    }
}