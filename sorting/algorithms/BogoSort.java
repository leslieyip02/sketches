package algorithms;

import java.util.*;
import java.util.stream.IntStream;

public class BogoSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] values) {
        // keep randomly shuffling until sorted
        while (!this.isSorted(values)) {
            for (int i = 0; i < values.length; i++) {
                this.swap(values, i, (int) (Math.random() * i));
            }
        }

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();

        // all elements are selected at each step
        int[] selected = IntStream.rangeClosed(0, values.length - 1)
                .toArray();

        states.add(new State<>(values.clone(), selected));
        while (!this.isSorted(values)) {
            for (int i = 0; i < values.length; i++) {
                this.swap(values, i, (int) (Math.random() * i));
            }

            states.add(new State<>(values.clone(), selected));
        }

        return states;
    }
}
