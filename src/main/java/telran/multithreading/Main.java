package telran.multithreading;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of racers: ");
        int nRacers = scanner.nextInt();

        System.out.print("Enter the race distance (number of iterations): ");
        int distance = scanner.nextInt();

        System.out.print("Enter minimum sleep timeout (ms): ");
        int minSleepTimeout = scanner.nextInt();

        System.out.print("Enter maximum sleep timeout (ms): ");
        int maxSleepTimeout = scanner.nextInt();

        Race race = new Race(distance, minSleepTimeout, maxSleepTimeout);

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
        
        Integer winner = race.getWinner();
        if (winner != null) {
            System.out.printf("Congratulations to Racer %d - the winner!%n", winner);
        } else {
            System.out.println("No winner, race incomplete!");
        }
        scanner.close();
    }
}