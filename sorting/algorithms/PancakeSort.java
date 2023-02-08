package algorithms;

import java.util.*;
import java.util.stream.IntStream;

public class PancakeSort extends SortingAlgorithm {
    private <T> void flip(T[] values, int startIndex,
            int endIndex, ArrayList<State<T>> states) {

        // show the flip
        if (states != null)
            states.add(new State<T>(values.clone(), startIndex, endIndex));
            
        for (int i = 0; i <= (endIndex - startIndex) / 2; i++) {
            this.swap(values, startIndex + i, endIndex - i);
        }
        
        if (states != null)
            states.add(new State<T>(values.clone(), startIndex));
    }

    public <T extends Comparable<T>> void sort(T[] values) {
        for (int i = values.length - 1; i >= 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j < i; j++) {
                if (this.compare(values, maxIndex, j) < 0) {
                    maxIndex = j;
                }
            }

            // flip max value in subarray of length i - 1 to the front
            this.flip(values, 0, maxIndex, null);
            // flip the max value into index i
            this.flip(values, 0, i, null);
        }

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();

        for (int i = values.length - 1; i >= 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j <= i; j++) {
                if (this.compare(values, maxIndex, j) < 0) {
                    maxIndex = j;
                }
            }

            int[] selected = IntStream.rangeClosed(0, maxIndex)
                .toArray();
            states.add(new State<T>(values.clone(), selected));
            this.flip(values, 0, maxIndex, states);
            
            selected = IntStream.rangeClosed(0, i)
                .toArray();
            states.add(new State<T>(values.clone(), selected));
            this.flip(values, 0, i, states);
        }

        return states;
    }
}
