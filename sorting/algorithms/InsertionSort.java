package algorithms;

import java.util.*;

public class InsertionSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] values) {
        for (int i = 1; i < values.length; i++) {
            // compare with previous element
            // keep swapping this element forwards until it is
            // in a sorted position in the subarray at the front
            for (int j = i - 1; j >= 0; j--) {
                if (this.compare(values, j, j + 1) != 1) {
                    break;
                }

                this.swap(values, j, j + 1);
            }
        }

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();

        for (int i = 1; i < values.length; i++) {
            // compare with previous element
            // keep swapping this element forwards until it is
            // in a sorted position in the subarray at the front
            for (int j = i - 1; j >= 0; j--) {
                this.addState(states, new State<T>(values.clone(), j));
                if (this.compare(values, j, j + 1) != 1) {
                    break;
                }

                this.swap(values, j, j + 1);
                this.addState(states, new State<T>(values.clone(), j + 1, j));
            }

            if (this.isSorted(values)) {
                return states;
            }
        }

        return states;
    }
}
