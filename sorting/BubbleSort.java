import java.util.ArrayList;

public class BubbleSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void sort(T[] values) {
        for (int i = 0; i < values.length - 1; i++)
            for (int j = 0; j < values.length - i - 1; j++)
                if (this.compare(values, j, j + 1) == 1)
                    this.swap(values, j, j + 1);

        this.printValues(values);
    }

    public <T extends Comparable<T>> ArrayList<T[]> states(T[] values) {
        ArrayList<T[]> states = new ArrayList<T[]>();

        states.add(values.clone());
        for (int i = 0; i < values.length - 1; i++) {
            for (int j = 0; j < values.length - i - 1; j++) {
                if (this.compare(values, j, j + 1) == 1) {
                    this.swap(values, j, j + 1);
                    states.add(values.clone());
                }
            }
        }

        return states;
    }
}
