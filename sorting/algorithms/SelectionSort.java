package algorithms;
import java.util.*;

public class SelectionSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] values) {
        for (int i = 0; i < values.length; i++) {
            int minIndex = i;

            // find minimum in current subarray
            for (int j = i + 1; j < values.length; j++)
                if (this.compare(values, j, minIndex) == -1)
                    minIndex = j;

            // select smallest and swap to front
            this.swap(values, i, minIndex);
        }

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();

        for (int i = 0; i < values.length; i++) {
            states.add(new State<T>(values.clone(), i));
            int minIndex = i;
            
            // find minimum in current subarray
            for (int j = i + 1; j < values.length; j++) {
                states.add(new State<T>(values.clone(), j));
                if (this.compare(values, j, minIndex) == -1)
                    minIndex = j;
            }

            // select smallest and swap to front
            this.swap(values, i, minIndex);
            states.add(new State<T>(values.clone(), minIndex, i));
            if (this.isSorted(values))
                return states;
        }

        return states;
    }
}
