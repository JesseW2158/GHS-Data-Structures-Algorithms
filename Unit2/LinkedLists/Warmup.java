package Unit2.LinkedLists;

public class Warmup {
    public static void main(String[] args) {
        Node L1 = new Node(1, new Node(3, new Node(5)));
        Node L2 = new Node(2, new Node(4, new Node(6, new Node(8))));

        Node head = mergeSortedList(L1, L2);

        System.out.println(getMiddle(head));
    }

    public static Node getMiddle(Node head) {
        if(head == null) {
            return null;
        }
    
        if(head.next == null) {
            return head;
        }
        
        Node FastRunner = head.next;
        Node SlowRunner = head.next;

        while(FastRunner != null && FastRunner.next != null) {
            FastRunner = FastRunner.next.next;

            if(FastRunner == null) {
                break;
            }
            
            SlowRunner = SlowRunner.next;
        }

        return SlowRunner;
    }

    public static boolean HasCycle(Node head) {
        if(head == null || head.next == null) {
            return false;
        }
        
        Node FastRunner = head.next;
        Node SlowRunner = head.next;

        while(FastRunner != null) {
            FastRunner = FastRunner.next;
            
            if(FastRunner == SlowRunner) {
                return true;
            }

            FastRunner = FastRunner.next;
            SlowRunner = SlowRunner.next;
        }

        return false;
    }

    public static Node mergeSortedList(Node L1, Node L2) {
        if(L1 == null) {
            return L2;
        }

        if(L2 == null) {
            return L1;
        }

        Node head;

        if(L1.num < L2.num) {
            head = new Node(L1.num);
            L1 = L1.next;
        } else {
            head = new Node(L2.num);
            L2 = L2.next;
        } 

        Node runner = head;
        
        while(L1 != null && L2 != null) {
            if(L1.num < L2.num) {
                runner.next = L1;
                L1 = L1.next;
            } else if(L2.num < L1.num) {
                runner.next = L2;
                L2 = L2.next;
            }
            runner = runner.next;
        }

        runner.next = (L1 != null) ? L1 : L2;

        return head;
    }
}