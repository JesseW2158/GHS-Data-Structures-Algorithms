package Unit5;

public class Knapsack {
    public static void main(String[] args) {
        int[] weights = {3,2,4,5,1};
        double[] values = {50,40,70,80,10};
        System.out.println(knapsack(7, weights, values));
    }

    public static double knapsack(int capacity, int[] weights, double[] values) {
        double[][] table = new double[weights.length + 1][capacity + 1];

        for (int i = 1; i < table.length; i++) {
            for (int size = 1; size <= capacity; size++) {
                if (weights[i - 1] <= size) {
                    table[i][size] = Math.max(table[i - 1][size], values[i - 1] + table[i - 1][size - weights[i - 1]]);
                }
            }
        }

        return table[weights.length][capacity];
    }
}
