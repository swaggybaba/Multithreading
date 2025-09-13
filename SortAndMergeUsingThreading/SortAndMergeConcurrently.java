public class SortAndMergeConcurrently {
    private int arr[];

    public SortAndMergeConcurrently(int[] arr) {
        this.arr = arr;
    }

    public void sortAndMergeUsingThread() {
        if (this.arr == null || this.arr.length == 0) {
            return;
        }

        final var sortEvens = new SortOddAndEven(this.arr, false);
        final var sortOdds = new SortOddAndEven(this.arr, true);

        Thread thread1 = new Thread(sortEvens);
        Thread thread2 = new Thread(sortOdds);

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int sortedEvenArray[] = sortEvens.getSortedArray();
        int sortedOddArray[] = sortOdds.getSortedArray();

        printArray(sortedEvenArray);
        printArray(sortedOddArray);

        MergeSortedArrays mergeSortedArrays = new MergeSortedArrays(sortedEvenArray, sortedOddArray);
        Thread thread3 = new Thread(mergeSortedArrays);
        thread3.start();
        try {
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        printArray(mergeSortedArrays.getMergedArray());
    }

    private void printArray(int a[]) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
}
