package telran.multithreading;

public class Racer extends Thread {
    private Race race;
    private int number;

    public Racer(Race race, int number) {
        this.race = race;
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < race.getDistance(); i++) {
            if (race.getWinner() != -1) {
                // Если уже есть победитель, выходим из цикла
                return;
            }
            System.out.printf("Racer %d: iteration %d\n", number, i + 1);
            try {
                Thread.sleep(race.getRandomSleepTime());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

        if (race.setWinner(number)) {
            System.out.printf("Congratulations to Racer %d - winner!\n", number);
        }
    }
}