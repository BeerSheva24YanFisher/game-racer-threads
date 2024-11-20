package telran.multithreading;

import java.util.Random;

public class Racer extends Thread {
    private Race race;
    private int number;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < race.getDistance(); i++) {
            try {
                int sleepTime = race.getMinSleepTimeout() + 
                                random.nextInt(race.getMaxSleepTimeout() - race.getMinSleepTimeout() + 1);
                Thread.sleep(sleepTime);
                System.out.printf("Racer %d completed iteration %d%n", number, i + 1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        if (race.setWinner(number)) {
            System.out.printf("Racer %d is the winner!%n", number);
        }
    }
}