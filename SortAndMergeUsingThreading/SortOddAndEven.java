import java.util.Arrays;

public class SortOddAndEven implements Runnable{
    private final int arr[];
    private final boolean isOdd;
    private int sortedArray[];

    public SortOddAndEven(final int[] arr, final boolean isOdd) {
        this.arr = arr;
        this.isOdd = isOdd;
    }

    @Override
    public void run() {
        sortedArray = Arrays.stream(arr).filter(isOdd ? (a -> a%2 != 0) : (a -> a%2 == 0)).sorted().toArray();
    }

    public int[] getSortedArray() {
        return sortedArray;
    }
}
