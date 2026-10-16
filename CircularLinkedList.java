import java.util.NoSuchElementException;

/**
 * User-defined Circular Singly Linked List (CLL).
 * tail.next is always the head.
 * Every operation prints the list right after it runs.
 */
public class CircularLinkedList<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> tail;
    private int size;

    public int length() {
        return size;
    }

    // INSERTION

    public void addFirst(T data) {
        Node<T> node = new Node<>(data);
        if (tail == null) {
            node.next = node;
            tail = node;
        } else {
            node.next = tail.next;
            tail.next = node;
        }
        size++;
        System.out.println("   " + "addFirst(" + data + ")" + "  =>  " + this);
    }

    public void addLast(T data) {
        Node<T> node = new Node<>(data);
        if (tail == null) {
            node.next = node;
        } else {
            node.next = tail.next;
            tail.next = node;
        }
        tail = node;
        size++;
        System.out.println("   " + "addLast(" + data + ")" + "  =>  " + this);
    }

    /** Inserts data at the given index (0 to size, inclusive). */
    public void addAt(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            addFirst(data);
            return;
        }
        if (index == size) {
            addLast(data);
            return;
        }
        Node<T> prev = tail.next; // head
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node<T> node = new Node<>(data);
        node.next = prev.next;
        prev.next = node;
        size++;
        System.out.println("   " + "addAt(" + index + ", " + data + ")" + "  =>  " + this);
    }

    // REMOVAL

    public T removeFirst() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        Node<T> head = tail.next;
        T data = head.data;
        if (size == 1) {
            tail = null;
        } else {
            tail.next = head.next;
        }
        size--;
        System.out.println("   " + "removeFirst() removed " + data + "  =>  " + this);
        return data;
    }

    public T removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = tail.data;
        if (size == 1) {
            tail = null;
        } else {
            Node<T> prev = tail.next; // head
            while (prev.next != tail) {
                prev = prev.next;
            }
            prev.next = tail.next;
            tail = prev;
        }
        size--;
        System.out.println("   " + "removeLast() removed " + data + "  =>  " + this);
        return data;
    }

    /** Removes the element at the given index (0 to size-1). */
    public T removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<T> prev = tail.next; // head
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        T data = prev.next.data;
        prev.next = prev.next.next;
        size--;
        System.out.println("   " + "removeAt(" + index + ") removed " + data + "  =>  " + this);
        return data;
    }

    // REVERSAL

    public void reverse() {
        if (size > 1) {
            Node<T> head = tail.next;
            Node<T> prev = tail;
            Node<T> curr = head;
            do {
                Node<T> next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            } while (curr != head);
            tail = head; // old head is the new tail
        }
        System.out.println("   " + "reverse()" + "  =>  " + this);
    }

    // DISPLAY

    @Override
    public String toString() {
        if (tail == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        Node<T> head = tail.next;
        Node<T> curr = head;
        do {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        } while (curr != head);
        sb.append("(back to ").append(head.data).append(")");
        return sb.toString();
    }

    // MAIN method to show working of implemented functions

    public static void main(String[] args) {
        CircularLinkedList<Integer> list = new CircularLinkedList<>();

        list.addFirst(20);
        list.addFirst(10);
        list.addLast(40);
        list.addAt(2, 30);
        System.out.println("   length() = " + list.length());

        list.reverse();

        list.removeFirst();
        list.removeLast();
        list.removeAt(1);
        System.out.println("   length() = " + list.length());

        //use case to show that the same class works for any data type
        CircularLinkedList<String> names = new CircularLinkedList<>();
        names.addLast("Asha");
        names.addLast("Ravi");
        names.addFirst("Meena");
        System.out.println("   length() = " + names.length());
    }
}
