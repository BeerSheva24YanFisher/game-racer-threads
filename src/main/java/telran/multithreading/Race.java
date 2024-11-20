package telran.multithreading;

public class Race {
    private int distance;
    private int minSleepTimeout;
    private int maxSleepTimeout;
    private Integer winnerNumber = null;

    public Race(int distance, int minSleepTimeout, int maxSleepTimeout) {
        this.distance = distance;
        this.minSleepTimeout = minSleepTimeout;
        this.maxSleepTimeout = maxSleepTimeout;
    }

    public int getDistance() {
        return distance;
    }

    public int getMinSleepTimeout() {
        return minSleepTimeout;
    }

    public int getMaxSleepTimeout() {
        return maxSleepTimeout;
    }

    public synchronized boolean setWinner(int racerNumber) {
        if (winnerNumber == null) {
            winnerNumber = racerNumber;
        }
        return winnerNumber == racerNumber;
    }

    public synchronized Integer getWinner() {
        return winnerNumber;
    }
}