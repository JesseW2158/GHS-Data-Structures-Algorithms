// package Unit2.LinkedLists;

// public class Warmup {
//     public static void main(String[] args) {
//         Node L1 = new Node(1, new Node(3, new Node(5)));
//         Node L2 = new Node(2, new Node(4, new Node(6)));

//         Node head = mergeSortedList(L1, L2);

//         while()
//     }

//     public static Node mergeSortedList(Node L1, Node L2) {
//         Node runner = L2;
//         Node current = L1;
//         Node head = (L1.num < L2.num) ? L1 : L2;
        
//         if(runner.num > current.num) {
//             head.next = runner;
//             current = current.next;
//         }

//         while (runner.next != null || current.next != null) {
//             while(!(runner.next.num > current.num)) {
//                 head.next = runner;
//                 head = head.next;
//                 runner = runner.next;    
//             }
//             while(!(current.next.num > runner.num)) {
//                 head.next = current;
//                 head = head.next;
//                 current = current.next;
//             }
//         }

//         return head;
//     }
// }