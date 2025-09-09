package Stacks.PrePostInFix;

import java.util.Stack;

public class Postfix {
    public static void main(String[] args) {
        System.out.println(inToPost("(3*4+(5+6*7))/(8-9)"));
    }

    public static int prec(char operator) {
        switch (operator) {
            case '+', '-':
                return 1;
            case '*', '/':
                return 2;
            case '^':
                return 3;
            default:
                return 4;
        }
    }

    public static String inToPost(String infix) {
        String post = "";
        Stack<Character> stack = new Stack<Character>();

        for (char c : infix.toCharArray()) {
            if(Character.isDigit(c)) {
                post += c;
            } else if(c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty()) {
                    if(stack.peek() == '(') {
                        stack.pop();
                        break;
                    } else {
                        post += stack.pop();
                    }
                }
            } else {
                while(!stack.isEmpty() && prec(stack.peek()) >= prec(c)) {
                    post += stack.pop();
                }

                stack.push(c);
            }
        }

        while(!stack.isEmpty()) {
            post += stack.pop();
        }

        return post;
    }

    public static double evaluate(String post) {
        Stack<Double> stack = new Stack<Double>();

        for(char c : post.toCharArray()) {
            if (Character.isDigit(c)) {
                stack.push((double) Character.getNumericValue(c));
            } else {
                double right = stack.pop();
                double left = stack.pop();

                switch (c) {
                    case '+':
                        stack.push((double)(left + right));
                        break;
                    case '-':
                        stack.push((double)(left - right));
                        break;
                    case '*':
                        stack.push((double)(left * right));
                        break;
                    case '/':
                        stack.push((double)(left / right));
                        break;
                    case '^':
                        stack.push((double)(Math.pow(left, right)));
                        break;
                    default:
                        return Double.POSITIVE_INFINITY;
                }
            }
        }

        return stack.pop();
    }
}