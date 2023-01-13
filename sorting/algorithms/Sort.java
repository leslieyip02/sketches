package algorithms;
import java.io.*;

public class Sort {
    private static Integer[] defaultValues = { 5, 2, 7, 4, 8, 1, 3, 6 };
    
    public static void main(String[] args) throws IOException {
        Integer[] values = defaultValues;

        if (args.length > 0) {
            values = new Integer[args.length];
            for (int i = 0; i < args.length; i++)
                values[i] = Integer.parseInt(args[i]);
        }

        SortingAlgorithm sorter = new BubbleSort();
        sorter.writeToCsv(values);
    }
}
