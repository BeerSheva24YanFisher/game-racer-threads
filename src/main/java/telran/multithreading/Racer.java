package telran.multithreading;

import java.util.Random;

public class Racer extends Thread {
    private Race race;
    private int number;
    private long finishTime; // время завершения гонки

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    public long getFinishTime() {
        return finishTime;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public void run() {
        int minSleep = race.getMinSleep();
        int maxSleep = race.getMaxSleep();
        int distance = race.getDistance();
        Random random = new Random();

        for (int i = 0; i < distance; i++) {
            try {
                sleep(random.nextInt(minSleep, maxSleep + 1));
                System.out.printf("%d - step %d\n", number, i);
            } catch (InterruptedException e) {}
        }
        
        finishTime = System.currentTimeMillis();
        race.winner.compareAndSet(-1, number);
    }
}