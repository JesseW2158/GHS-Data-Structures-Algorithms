package Unit5;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        
    }

    public static int coinChange(int[] coins, int amount) {
        int[] CC = new int[amount + 1];
        Arrays.fill(CC, amount + 1);
        CC[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i - coins[j] >= 0) {
                    CC[i] = Math.min(CC[i], CC[i - coins[j]] + 1);
                }
            }
        }

        return CC[amount] != amount + 1 ? CC[amount] : -1;
    }
}
