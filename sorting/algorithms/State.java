package algorithms;
import java.util.*;

public class State<T> {
    public T[] values;
    public int index;

    // default value of -1 means no swap occurred
    public int swappedWith = -1;

    public State(T[] values, int index) {
        this.values = values;
        this.index = index;
    }

    public State(T[] values, int index, int swappedWith) {
        this.values = values;
        this.index = index;
        this.swappedWith = swappedWith;
    }

    public String toString() {
        return String.format("Values: %s, Index: %d, Swapped with: %d", 
            Arrays.toString(this.values), this.index, this.swappedWith);
    }
}
