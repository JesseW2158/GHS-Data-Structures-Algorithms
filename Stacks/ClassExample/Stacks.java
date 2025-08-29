package Stacks.ClassExample;

import java.util.Stack;

public class Stacks {
    public static void main(String[] args) {
        Stack<Integer> hats = new Stack<Integer>();

        hats.push(3);
        hats.push(8);
        hats.push(6);
        hats.push(3);
        hats.push(0);

        System.out.println(hats.peek());
        System.out.println(hats);

        Stack<Integer> temp = new Stack<Integer>();

        while(!hats.isEmpty()) {
            temp.push(hats.pop());
        }

        while(!temp.isEmpty()) {
            hats.push(temp.pop());
        }

        System.out.println(isPalidrone(hats));

        System.out.println(hats);
    }

    public static boolean isPalidrone(Stack<Integer> hats) {
        Stack<Integer> temp = new Stack<Integer>();
        Stack<Integer> keeper = new Stack<Integer>();
        int num;

        int size = hats.size();

        while(temp.size() != (int) size/2) {
            int a = hats.pop();
            keeper.push(a);
            temp.push(a);
        }
        
        if(size % 2 == 1) {
            num = hats.pop();
            keeper.push(num);
        }

        while(!temp.isEmpty()) {
            int last = temp.pop();
            keeper.push(last);

            if(last != hats.pop()) {
                while(!hats.isEmpty()) {
                    keeper.push(hats.pop());
                }
                while(!keeper.isEmpty()) {
                    hats.push(keeper.pop());
                }
                return false;
            }
        }

        while(!keeper.isEmpty()) {
            hats.push(keeper.pop());
        }

        return true;
    }
}