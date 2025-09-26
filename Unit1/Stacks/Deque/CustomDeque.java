package Unit1.Stacks.Deque;

public class CustomDeque<T> {
    T[] container;   
    int front, back;
    
    @SuppressWarnings("unchecked")
    public CustomDeque(int capacity) {
        container = (T[]) new Object[capacity];

        front = -1;
        back = -1;
    }

    public T addFront(T item) {
        if(front == -1) {
            front = container.length/2;
            back = container.length/2;
            container[front] = item;
            return item;
        }

        container[--front] = item;
        
        if(front == 0) {

        }

        return item;
    }
}