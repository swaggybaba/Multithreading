package org.example;

public class MergeSortedArrays implements Runnable{

    private final int array1[];
    private final int array2[];
    private int mergedArray[];

    public MergeSortedArrays(int array1[], int array2[]) {
        this.array1 = array1;
        this.array2 = array2;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mergedArray= new int[array1.length + array2.length];
        int i = 0, j = 0, k = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                mergedArray[k++] = array1[i++];
            } else {
                mergedArray[k++] = array2[j++];
            }
        }
        while (i < array1.length) {
            mergedArray[k++] = array1[i++];
        }
        while (j < array2.length) {
            mergedArray[k++] = array2[j++];
        }
    }

    public int[] getMergedArray() {
        return mergedArray;
    }
}
