package datastructureproject.datastructures;

public class RandomImp {

    private long seed;

    public RandomImp() {
        this.seed = System.nanoTime();
    }

    public RandomImp(long seed) {
        this.seed = seed;
    }

    public int nextInt() {
        return (int) System.nanoTime() % Integer.MAX_VALUE;
    }

    public int nextInt(int upperBound) {
        return (int) System.nanoTime() % upperBound;
    }

    public int nextInt(int lowerBound, int upperBound) {
        return lowerBound + ((int) System.nanoTime() % (upperBound-lowerBound));
    }

    public long nextLong() {
        return System.nanoTime() % Long.MAX_VALUE;
    }

    public long nextLong(long upperBound) {
        return System.nanoTime() % upperBound;
    }

    public long nextLong(long lowerBound, long upperBound) {
        return lowerBound + (System.nanoTime() % (upperBound-lowerBound));
    }
}
