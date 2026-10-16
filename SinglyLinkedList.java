import java.util.NoSuchElementException;

/**
 * User-defined Singly Linked List (SLL).
 * Every operation prints the list right after it runs.
 */
public class SinglyLinkedList<T> {

    private static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int size;

    public int length() {
        return size;
    }

    // INSERTION

    public void addFirst(T data) {
        Node<T> node = new Node<>(data);
        node.next = head;
        head = node;
        size++;
        System.out.println("   " + "addFirst(" + data + ")" + "  =>  " + this);
    }

    public void addLast(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = node;
        } else {
            Node<T> curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = node;
        }
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
        Node<T> prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node<T> node = new Node<>(data);
        node.next = prev.next;
        prev.next = node;
        size++;
        System.out.println("   " + "addAt(" + index + ", " + data + ")" + "  =>  " + this);
    }

    //REMOVAL

    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        T data = head.data;
        head = head.next;
        size--;
        System.out.println("   " + "removeFirst() removed " + data + "  =>  " + this);
        return data;
    }

    public T removeLast() {
        if (head == null) {
            throw new NoSuchElementException("List is empty");
        }
        if (head.next == null) {
            return removeFirst();
        }
        Node<T> prev = head;
        while (prev.next.next != null) {
            prev = prev.next;
        }
        T data = prev.next.data;
        prev.next = null;
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
        Node<T> prev = head;
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
        Node<T> prev = null;
        Node<T> curr = head;
        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        head = prev;
        System.out.println("   " + "reverse()" + "  =>  " + this);
    }

    // DISPLAY

    @Override
    public String toString() {
        if (head == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        Node<T> curr = head;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.next != null) {
                sb.append(" -> ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }

    // MAIN method to show working of implemented functions

    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

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

        // use case to show that the same class works for any data type
        SinglyLinkedList<String> names = new SinglyLinkedList<>();
        names.addLast("Asha");
        names.addLast("Ravi");
        names.addFirst("Meena");
        System.out.println("   length() = " + names.length());
    }
}
