package Unit5;

import java.util.HashMap;

public class CatalanNumbersWarmup {
    public static HashMap<Integer, Long> memo = new HashMap<Integer, Long>();

    public static void main(String[] args) {
        for (int i = 0; i < 15; i++) {
            System.out.print(catalanNumber(i) + " ");
        }

        memo.put(0, 1l);
        System.out.println();

        for (int i = 0; i < 15; i++) {
            System.out.print(dynamicCatalan(i) + " ");
        }

    }

    public static long catalanNumber(int n) {
        if (n == 0) {
            return 1;
        }

        long cat = 0;

        for (int i = 1; i <= n; i++) {
            cat += catalanNumber(i - 1) * catalanNumber(n - i);
        }

        return cat;
    }

    public static long dynamicCatalan(int n) {
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        long cat = 0;

        for (int i = 1; i <= n; i++) {
            cat += dynamicCatalan(i - 1) * dynamicCatalan(n - i);
        }
        memo.put(n, cat);

        return cat;
    }
}
