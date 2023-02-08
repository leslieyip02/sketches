package algorithms;

import java.io.*;
import java.util.*;

// all sorting algorithms will take an array of integers,
// and sort them in ascending order by default
public abstract class SortingAlgorithm {
    /**
     * Compares element at i with element at j
     * 
     * @param values - current array of values
     * @param i      - index of 1st element
     * @param j      - index of 2nd element
     * 
     * @return
     *         - < 0 : i less than j
     *         - = 0 : i equal to j
     *         - > 0 : i more than j
     */
    protected <T extends Comparable<T>> int compare(T[] values, int i, int j) {
        return values[i].compareTo(values[j]);
    }

    /**
     * Swaps element at i with element at j
     * 
     * @param values - current array of values
     * @param i      - index of 1st element
     * @param j      - index of 2nd element
     */
    protected final <T> void swap(T[] values, int i, int j) {
        T tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    /**
     * Checks if values are sorted ascending
     */
    protected <T extends Comparable<T>> boolean isSorted(T[] values) {
        for (int i = 0; i < values.length - 1; i++) {
            if (this.compare(values, i, i + 1) == 1) {
                return false;
            }
        }

        return true;
    }

    public abstract <T extends Comparable<T>> void sort(T[] values);

    /**
     * Used to add a state, which will be added to the states csv
     */
    public <T extends Comparable<T>> void addState(
            ArrayList<State<T>> states, State<T> state) {
        if (states != null) {
            states.add(state);
        }
    }

    public abstract <T extends Comparable<T>> ArrayList<State<T>> states(T[] values);

    public <T> void printValues(T[] values) {
        System.out.println(Arrays.toString(values));
    }

    /**
     * Stores states in a csv so that the processing sketch can access the values
     */
    public <T extends Comparable<T>> void writeToCsv(T[] values) throws IOException {
        File csvFile = new File("./stateData.csv");
        if (csvFile.exists())
            csvFile.delete();

        csvFile = new File("./stateData.csv");
        csvFile.createNewFile();

        ArrayList<State<T>> states = this.states(values);
        try (PrintWriter csvWriter = new PrintWriter(csvFile)) {
            for (State<T> state : states)
                csvWriter.println(state.toCSVRow());
        }

        System.out.print("Sorted: ");
        this.printValues(states.get(states.size() - 1).values);
    }
}