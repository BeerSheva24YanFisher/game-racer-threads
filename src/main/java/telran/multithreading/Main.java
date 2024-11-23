package telran.multithreading;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;

import telran.view.InputOutput;
import telran.view.Item;
import telran.view.Menu;
import telran.view.StandardInputOutput;

public class Main {
    private static final int MAX_THREADS = 10;
    private static final int MIN_THREADS = 2;
    private static final int MIN_DISTANCE = 100;
    private static final int MAX_DISTANCE = 3500;
    private static final int MIN_SLEEP = 2;
    private static final int MAX_SLEEP = 5;

    private static final String fileName = "results.txt";

    public static void main(String[] args) {
        InputOutput io = new StandardInputOutput();
        Item[] items = getItems();
        Menu menu = new Menu("Race Game", items);
        menu.perform(io);
    }

    private static Item[] getItems() {
        Item[] res = {
            Item.of("Start new game", Main::startGame),
            Item.ofExit()
        };
        return res;
    }

    static void startGame(InputOutput io) {
        int nThreads = io.readNumberRange("Enter number of the racers", "Wrong number of the racers",
                MIN_THREADS, MAX_THREADS).intValue();
        int distance = io.readNumberRange("Enter distance",
                "Wrong Distance", MIN_DISTANCE, MAX_DISTANCE).intValue();
        Race race = new Race(distance, MIN_SLEEP, MAX_SLEEP);
        Racer[] racers = new Racer[nThreads];
        startRacers(racers, race);
        joinRacers(racers);
        displayResults(racers, race);
    }

    private static void displayResults(Racer[] racers, Race race) {
        long startTime = race.getStartTime();
        Arrays.sort(racers, Comparator.comparingLong(Racer::getFinishTime));

        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("Results Table:");
            writer.printf("%-6s %-12s %-15s\n", "Place", "Racer_Number", "Running_Time (ms)");

            for (int i = 0; i < racers.length; i++) {
                long runningTime = racers[i].getFinishTime() - startTime;
                writer.printf("%-6d %-12d %-15d\n", i + 1, racers[i].getNumber(), runningTime);
            }

            System.out.println("Results have been saved to " + fileName);
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void joinRacers(Racer[] racers) {
        for (int i = 0; i < racers.length; i++) {
            try {
                racers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void startRacers(Racer[] racers, Race race) {
        for (int i = 0; i < racers.length; i++) {
            racers[i] = new Racer(race, i + 1);
            racers[i].start();
        }
    }
}