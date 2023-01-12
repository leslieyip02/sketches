import java.util.ArrayList;

public class BubbleSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] values) {
        for (int i = 0; i < values.length - 1; i++)
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
            }
        }

        return states;
    }
}
