package telran.multithreading;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter the number of racers:");
            int nRacers = scanner.nextInt();

            System.out.println("Enter the race distance (number of iterations):");
            int distance = scanner.nextInt();

            System.out.println("Enter minimum sleep time (ms):");
            int minSleepTime = scanner.nextInt();

            System.out.println("Enter maximum sleep time (ms):");
            int maxSleepTime = scanner.nextInt();

            Race race = new Race(distance, minSleepTime, maxSleepTime);
            Racer[] racers = new Racer[nRacers];

            for (int i = 0; i < nRacers; i++) {
                racers[i] = new Racer(race, i + 1);
                racers[i].start();
            }

            for (Racer racer : racers) {
                try {
                    racer.join();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

            System.out.printf("Race finished! Winner is Racer %d\n", race.getWinner());
        }
    }
}