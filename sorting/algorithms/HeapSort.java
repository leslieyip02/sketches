package algorithms;

import java.util.*;

public class HeapSort extends SortingAlgorithm {
    private <T extends Comparable<T>> void heapify(T[] values,
            int rootIndex, int size, ArrayList<State<T>> states) {

        if (states != null)
            states.add(new State<T>(values.clone(), rootIndex));
        int largestIndex = rootIndex;

        // get indices of child nodes
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < size && this.compare(values, largestIndex, left) < 0)
            largestIndex = left;

        if (right < size && this.compare(values, largestIndex, right) < 0)
            largestIndex = right;

        if (largestIndex != rootIndex) {
            if (states != null)
                states.add(new State<T>(values.clone(), rootIndex, largestIndex));

            this.swap(values, rootIndex, largestIndex);
            this.heapify(values, largestIndex, size, states);
        }
    }

    public <T extends Comparable<T>> void sort(T[] values) {
        // convert values into max heap by swapping max element to root
        for (int i = values.length / 2 - 1; i >= 0; i--)
            this.heapify(values, i, values.length, null);

        // extract max element and swap to back
        for (int i = values.length - 1; i > 0; i--) {
            this.swap(values, 0, i);
            this.heapify(values, 0, i, null);
        }

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();

        for (int i = values.length / 2 - 1; i >= 0; i--)
            this.heapify(values, i, values.length, states);

        for (int i = values.length - 1; i > 0; i--) {
            states.add(new State<T>(values.clone(), 0, i));
            this.swap(values, 0, i);
            this.heapify(values, 0, i, states);
        }

        return states;
    }
}
