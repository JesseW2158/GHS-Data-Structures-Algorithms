package Queues.UniversalStudios;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StandInLine {
    public static void main(String[] args) {
        Queue<String> TheHulk = new LinkedList<String>();

        TheHulk.offer("Sarge");
        TheHulk.offer("Ak");
        TheHulk.offer("Ali");

        for(String s : TheHulk) {
            System.out.println(s);
        }
        
        System.out.println(TheHulk.peek());
        
        // for(int i = 0; i < 5; i++) {
        //     System.out.println(TheHulk.poll());
        // }

        stutter(TheHulk);
    }

    public static void stutter(Queue<String> queue) {
        for(int i = 0; i < queue.size(); i++) {
            queue.offer(queue.peek());
            queue.offer(queue.poll());
        }
    }

    public static void mirror(Queue<String> queue) {
        Stack<String> aux = new Stack<String>();

        for(String s : queue) {
            aux.push(s);
        }
    }
}
