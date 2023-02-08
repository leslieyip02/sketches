package algorithms;

import java.util.*;
import java.util.stream.IntStream;

public class QuickSort extends SortingAlgorithm {
    private <T extends Comparable<T>> int partition(T[] values,
            int low, int high, ArrayList<State<T>> states) {

        // pivot on rightmost element
        int pivotIndex = high;
        this.addState(states, new State<T>(values.clone(),
                IntStream.rangeClosed(low, pivotIndex).toArray()));

        // loop from left to find elements larger than the pivot
        int nextIndex = low;
        for (int i = low; i < high; i++) {
            this.addState(states, new State<T>(values.clone(), i));

            // keep smaller values to the front,
            // and swap larger values to the back
            if (this.compare(values, i, pivotIndex) <= 0) {
                this.addState(states, new State<T>(values.clone(), i, nextIndex));
                this.swap(values, nextIndex, i);

                this.addState(states, new State<T>(values.clone(), nextIndex));
                nextIndex++;
            }

            // when the element is larger than the pivot,
            // the loop continues without advancing nextIndex,
            // so nextIndex continues pointing at the element larger than the pivot
            // the process will keep swappinig the smaller values to the front
        }

        // after this swap, the value at the pivotIndex will be in its sorted position
        this.addState(states, new State<T>(values.clone(), nextIndex, pivotIndex));
        this.swap(values, nextIndex, pivotIndex);

        // this is the next pivotIndex
        // all values to its left are less than itself,
        // and all value to its right are greater
        return nextIndex;
    }

    public <T extends Comparable<T>> void recursiveSort(T[] values,
            int low, int high, ArrayList<State<T>> states) {

        if (low < high) {
            // split the array into 2 subarrays
            int pivotIndex = this.partition(values, low, high, states);

            this.addState(states, new State<T>(values.clone(), pivotIndex));
            this.recursiveSort(values, low, pivotIndex - 1, states);

            this.addState(states, new State<T>(values.clone(), pivotIndex));
            this.recursiveSort(values, pivotIndex + 1, high, states);
        }
    }

    public <T extends Comparable<T>> void sort(T[] values) {
        this.recursiveSort(values, 0, values.length - 1, null);
        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();
        this.recursiveSort(values, 0, values.length - 1, states);

        return states;
    }
}
