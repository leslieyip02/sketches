package algorithms;

import java.util.*;

public class SimpleSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] values) {
        // bad time complexity but this is so simple :->
        // go through the whole array and keep front subarray sorted
        for (int i = 0; i < values.length; i++) {
            for (int j = 0; j < values.length; j++) {
                if (this.compare(values, i, j) == -1) {
                    this.swap(values, i, j);
                }
            }
        }

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();

        for (int i = 0; i < values.length; i++) {
            this.addState(states, new State<T>(values.clone(), i));
            for (int j = 0; j < values.length; j++) {
                this.addState(states, new State<T>(values.clone(), j));
                if (this.compare(values, i, j) == -1) {
                    this.addState(states, new State<T>(values.clone(), i, j));
                    this.swap(values, i, j);
                }
            }
        }

        return states;
    }
}
