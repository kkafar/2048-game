package game.util;

public class Random {
    /**
     * @return random integer from [lowerBound, upperBound) interval
     */
    public static int randomInt(int lowerBound, int upperBound) {
        return (int) (Math.rint(Random.randomFromInterval(lowerBound, upperBound - 1)));
    }

    /**
     * @return random number from [lowerBound, upperBound) interval
     */
    public static double randomFromInterval(double lowerBound, double upperBound) {
        if (lowerBound > upperBound) 
            throw new IllegalArgumentException("Random: randomFromInterval: lowerBound >= upperBound");

        return Math.random() * (upperBound - lowerBound) + lowerBound;
    }
}
