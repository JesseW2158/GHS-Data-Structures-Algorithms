package Unit5;

public class DiceRolls {
    public static void main(String[] args) {
        System.out.println(diceRolls(7, 18));
    }

    public static int diceRolls(int dice, int total) {
        int[][] table = new int[dice + 1][total + 1];
        table[0][0] = 1;

        for (int die = 1; die <= dice; die++) {
            for (int sum = 0; sum <= total; sum++) {
                int count = 0;

                for (int i = 1; i <= 6; i++) {
                    count += sum - i >= 0 ? table[die - 1][sum - i] : 0;
                }

                table[die][sum] = count;
            }
        }

        return table[dice][total];
    }
}
