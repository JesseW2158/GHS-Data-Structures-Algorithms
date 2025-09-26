package Unit1.Stacks.Bears;

public class Forest {
    public static void main(String[] args) {
        Den JBWilliams = new Den();

        for(int i = 0; i < 10; i++) {
            Bear b = new Bear("Bear " + i, Math.random() * 1000 + 100, i);
            System.out.println(b);
            JBWilliams.push(b);
        }

        System.out.println(JBWilliams);
        System.out.println(JBWilliams.peek());

        for(Bear b : JBWilliams)
            System.out.println(b);
    }
}
