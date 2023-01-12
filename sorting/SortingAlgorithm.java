import java.util.Arrays;
import java.util.ArrayList;

// all sorting algorithms will take an array of integers,
// and sort them in ascending order by default
public abstract class SortingAlgorithm {
    protected <T extends Comparable<T>> int compare(T[] values, int i, int j) {
        return values[i].compareTo(values[j]);
    }

    protected final <T> void swap(T[] values, int i, int j) {
        T tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    protected <T extends Comparable<T>> boolean isSorted(T[] values) {
        for (int i = 0; i < values.length - 1; i++)
            if (this.compare(values, i, i + 1) == 1)
                return false;

        return true;
    }

    public <T> void printValues(T[] values) {
        System.out.println(Arrays.toString(values));
    }

    public abstract <T extends Comparable<T>> void sort(T[] values);
    public abstract <T extends Comparable<T>> ArrayList<T[]> states(T[] values);
}