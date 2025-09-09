package Stacks;

import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

public class Stacks {
    public static void main(String[] args) {
        String a = "{({[([[{{}}]])]})}";
        
        String b = "{({[([{{}}]])]})}";

        System.out.println(validExpression(a));
        System.out.println(validExpression(b));
    }

    public static boolean validExpression(String expression) {
        Map<Character, Character> pairs = new HashMap<>();  
        pairs.put('(', ')');
        pairs.put('[', ']');
        pairs.put('{', '}');

        Stack<Character> stack = new Stack<Character>();

        for (char c : expression.toCharArray()) {
            if (pairs.containsKey(c)) {
                stack.push(c);
                continue;
            }
            if(stack.isEmpty() || pairs.get(stack.pop()) != c) {
                return false;
            }
        }

        return true;
    }

    public static String stripBrackets(String expression) {
        String brackets = "(){}[]";
        String output = "";

        for(char c : expression.toCharArray()) {
            if(brackets.indexOf(c) > -1) {
                output += c;
            }
        }

        return output;
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

    public static void palidrone() {
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
}