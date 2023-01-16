package algorithms;
import java.util.*;

public class BubbleSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] values) {
        // keep swapping and "bubbling" large values to the end
        for (int i = 0; i < values.length - 1; i++)
            // each subsequent loop ends earlier because the larger values are at the back
            for (int j = 0; j < values.length - i - 1; j++)
                if (this.compare(values, j, j + 1) == 1)
                    this.swap(values, j, j + 1);

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();

        for (int i = 0; i < values.length - 1; i++) {
            for (int j = 0; j < values.length - i - 1; j++) {
                State<T> state;

                if (this.compare(values, j, j + 1) == 1) {
                    this.swap(values, j, j + 1);
                    state = new State<T>(values.clone(), j, j + 1);
                } else {
                    state = new State<T>(values.clone(), j);
                }

                states.add(state);
                if (this.isSorted(values))
                    return states;
            }
        }

        return states;
    }
}
