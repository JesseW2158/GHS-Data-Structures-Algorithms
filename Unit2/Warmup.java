package Unit2;

public class Warmup {
    public static void main(String[] args) {
        Node List1 = new Node(6, new Node(2, new Node(3, new Node(4, new Node(5, new Node(1))))));

        swapNextTwo(List1);
        swapNextTwo(List1.next.next);
        swapNextTwo(List1.next);
        swapNextTwo(List1);
        System.out.println(List1);
        List1 = bubble(List1);
        System.out.println(List1);
    }

    public static Node bubble(Node head) {
        Node temp = new Node(Integer.MIN_VALUE, head);
        Node runner;
        boolean hasSwap = true;

        while(hasSwap) {
            runner = temp;
            hasSwap = false;
            while(runner != null) {
                if(runner.next != null && runner.next.next != null) {
                    if(runner.next.next.num < runner.next.num) {
                        swapNextTwo(runner);
                        hasSwap = true;
                    }
                }                
                runner = runner.next;
            }
        }
        
        return temp.next;
    }

    public static boolean swapNextTwo(Node node) {
        if(node == null || node.next == null || node.next.next == null) {
            return false;
        }

        Node temp = node.next;
        node.next = node.next.next;
        temp.next = node.next.next;
        node.next.next = temp;

        return true;
    }   
}