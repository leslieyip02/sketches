import java.util.Arrays;
import java.util.ArrayList;

public class Main {
    private static Integer[] defaultValues = { 5, 2, 7, 4, 8, 1, 3, 6 };
    
    public static void main(String[] args) {
        Integer[] values = defaultValues;

        if (args.length > 0) {
            values = new Integer[args.length];
            for (int i = 0; i < args.length; i++)
                values[i] = Integer.parseInt(args[i]);
        }

        SortingAlgorithm sorter = new BubbleSort();
        ArrayList<Integer[]> states = sorter.states(values);

        for (Integer[] state : states)
            System.out.println(Arrays.toString(state));
    }
}
