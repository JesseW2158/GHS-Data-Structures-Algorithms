package Unit4;

import java.util.Stack;

public class Warmup {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        for (int i = 0; i < 10; i++) {
            stack.push((int)(Math.random() * 15));
        }

        System.out.println(stack);

        Stack<Integer> temp = new Stack<Integer>();
        boolean swapped = true;

        while(swapped && !stack.isEmpty()) {
            swapped = false;
            temp.push(stack.pop());

            while (!stack.isEmpty()) {
                int a = stack.pop();
                int b = temp.pop();

                if(a < b) {
                    swapped = true;
                    temp.push(a);
                    temp.push(b);
                } else {
                    temp.push(b);
                    temp.push(a);
                }
            }

            while(!temp.isEmpty()) {
                stack.push(temp.pop());
            }
        }

        System.out.println(stack);
    }
}