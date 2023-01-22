package algorithms;
import java.util.*;
import java.util.stream.IntStream;

public class MergeSort extends SortingAlgorithm {
    public <T extends Comparable<T>> void merge(T[] values, 
        int low, int high, ArrayList<State<T>> states) {

        // array is split in place so the range is from low to high
        int[] selected = IntStream.rangeClosed(low, high)
            .toArray();

        // for odd number lengthed arrays,
        // there will be a range with length one 
        // duplicating this value causes the sketch to colour this 
        // with the multipleSelectedFill instead of the regular selectedFill
        if (selected.length == 1)
            selected = new int[] { selected[0], selected[0] };

        if (states != null)
            states.add(new State<T>(values.clone(), selected));

        // keep splitting the array in half,
        // until there's only 1 or 2 elements left
        int mid = (low + high) / 2;
        if (high - low > 1) {
            this.merge(values, low, mid, states);
            this.merge(values, mid + 1, high, states);
        }

        // store the sorted subarray
        ArrayList<T> merged = new ArrayList<T>();

        // recombine sorted subarrays
        // use 2 different indices to traverse each subarray
        int frontHalfIndex = low;
        int backHalfIndex = mid + 1;

        // since both subarrays are sorted, 
        // it is easier to push items into the sorted subarray
        while (frontHalfIndex <= mid && backHalfIndex <= high) {
            // stability is preserved since elements from the front
            // are kept at the front even if they are equal
            if (this.compare(values, frontHalfIndex, backHalfIndex) <= 0) {
                merged.add(values[frontHalfIndex]);
                frontHalfIndex++;
            } else {
                merged.add(values[backHalfIndex]);
                backHalfIndex++;
            }
        }
        
        // if there are any element left, 
        // fill in the rest of the subarray
        while (frontHalfIndex <= mid) { 
            merged.add(values[frontHalfIndex]);
            frontHalfIndex++;
        }
        
        while (backHalfIndex <= high) { 
            merged.add(values[backHalfIndex]);
            backHalfIndex++;
        }
        
        // copy the sorted subarray into the original array
        for (int i = 0; i < high - low + 1; i++)
            values[low + i] = merged.get(i);

        if (states != null)
            states.add(new State<T>(values.clone(), selected));
    }

    public <T extends Comparable<T>> void sort(T[] values) {        
        this.merge(values, 0, values.length - 1, null);
        this.printValues(values);
    }
    
    public <T extends Comparable<T>> ArrayList<State<T>> states(T[] values) {
        ArrayList<State<T>> states = new ArrayList<State<T>>();
        this.merge(values, 0, values.length - 1, states);

        return states;
    }
}
