package algorithms;

import java.util.*;
import java.util.stream.Collectors;

public class State<T> {
    public T[] values;
    public int[] selected;

    // default value of -1 means no swap occurred
    public int swappedWith = -1;

    /**
     * State with only 1 element at index selected
     * 
     * @param values
     * @param index
     */
    public State(T[] values, int index) {
        this.values = values;
        this.selected = new int[] { index };
    }

    /**
     * State with a range of elements selected
     * 
     * @param values
     * @param index
     */
    public State(T[] values, int[] index) {
        this.values = values;
        this.selected = index;
    }

    /**
     * State with a swap between elements
     * 
     * @param values
     * @param index
     * @param swappedWith
     */
    public State(T[] values, int index, int swappedWith) {
        this.values = values;
        this.selected = new int[] { index };
        this.swappedWith = swappedWith;
    }

    /**
     * Converts values and selected indices to a comma separated string
     * 
     * @return comma separated values to be stored in the csv
     */
    public String toCSVRow() {
        String values = Arrays.stream(this.values)
                .map((i) -> i.toString())
                .collect(Collectors.joining(","));

        String selected = Arrays.stream(this.selected)
                .mapToObj((i) -> String.valueOf(i))
                .collect(Collectors.joining(","));

        // selected values are comma-separated in the second-to-last column
        // the index for the swapped value is stored in the last column
        return String.format("%s,\"%s\",%d", values,
                selected, this.swappedWith);
    }
}
