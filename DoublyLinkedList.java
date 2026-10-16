import java.util.NoSuchElementException;

/**
 * User-defined Doubly Linked List (DLL).
 * Every operation prints the list (forward and backward) right after it runs.
 */
public class DoublyLinkedList<T> {

    private static class Node<T> {
        T data;
        Node<T> prev;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public int length() {
        return size;
    }

    // INSERTION

    public void addFirst(T data) {
        Node<T> node = new Node<>(data);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
        System.out.println("   " + "addFirst(" + data + ")" + "  =>  " + this);
    }

    public void addLast(T data) {
        Node<T> node = new Node<>(data);
        if (tail == null) {
            head = tail = node;
        } else {
            node.prev = tail;
            tail.next = node;
            tail = node;
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
        if (index == size) {
            addLast(data);
            return;
        }
        Node<T> curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        Node<T> node = new Node<>(data);
        node.prev = curr.prev;
        node.next = curr;
        curr.prev.next = node;
        curr.prev = node;
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
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
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
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
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
        Node<T> curr;
        if (index < size / 2) {
            curr = head;
            for (int i = 0; i < index; i++) {
                curr = curr.next;
            }
        } else {
            curr = tail;
            for (int i = size - 1; i > index; i--) {
                curr = curr.prev;
            }
        }
        curr.prev.next = curr.next;
        curr.next.prev = curr.prev;
        size--;
        System.out.println("   " + "removeAt(" + index + ") removed " + curr.data + "  =>  " + this);
        return curr.data;
    }

    // REVERSAL

    public void reverse() {
        Node<T> curr = head;
        while (curr != null) {
            Node<T> next = curr.next;
            curr.next = curr.prev;
            curr.prev = next;
            curr = next;
        }
        Node<T> temp = head;
        head = tail;
        tail = temp;
        System.out.println("   " + "reverse()" + "  =>  " + this);
    }

    // ---------- DISPLAY ----------

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
                sb.append(" <-> ");
            }
            curr = curr.next;
        }
        return sb.toString();
    }

    /** Prints the list from tail to head (useful to verify prev links). */
    public String toStringBackward() {
        if (tail == null) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder();
        Node<T> curr = tail;
        while (curr != null) {
            sb.append(curr.data);
            if (curr.prev != null) {
                sb.append(" <-> ");
            }
            curr = curr.prev;
        }
        return sb.toString();
    }

    // MAIN method to show working of implemented functions

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list = new DoublyLinkedList<>();

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
        DoublyLinkedList<String> names = new DoublyLinkedList<>();
        names.addLast("Asha");
        names.addLast("Ravi");
        names.addFirst("Meena");
        System.out.println("   length() = " + names.length());
    }
}
