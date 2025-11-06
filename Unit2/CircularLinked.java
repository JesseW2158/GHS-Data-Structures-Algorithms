package Unit2;

public class CircularLinked {
    public static void main(String[] args) {
        Node List1 = new Node(1, new Node(2, new Node(3, new Node(4, new Node(5, new Node(6))))));
        Node List2 = new Node(7, new Node(8, new Node(9, new Node(10, new Node(11, new Node(12))))));

        Node temp = CircleLink(List1, List2);

        System.out.println(temp);
    }

    public static Node CircleLink(Node list1, Node list2) {
        if(list1 == null) {
            return list2;
        }

        if(list2 == null) {
            return list1;
        }

        Node runner = list1;

        while(runner.next != null) {
            runner = runner.next;
        }

        runner.next = list2;

        while(runner.next != null) {
            runner = runner.next;
        }

        runner.next = list1;

        return list1;
    }
}
