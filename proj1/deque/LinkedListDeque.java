package deque;

import java.util.Iterator;
import java.util.LinkedList;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private Node sentinel;
    private int size;

    private class Node {
        public T item;
        public Node next;
        public Node prev;
        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node p = new Node(item, sentinel, sentinel.next);
        sentinel.next = p;
        p.next.prev = p;
        size = size + 1;
    }

    public void addLast(T item) {
        Node p = new Node(item, sentinel.prev, sentinel);
        sentinel.prev = p;
        p.prev.next = p;
        size = size + 1;
    }


    public int size() {
        return size;
    }

    public void printDeque() {
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
        }
    }

    public T removeFirst() {

        if (isEmpty()) {
            return null;
        }
        Node n = sentinel.next;
        T item = n.item;
        sentinel.next = n.next;
        n.next.prev = sentinel;
        n.item = null;
        n.next = null;
        n.prev = null;
        size = size - 1;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node n = sentinel.prev;
        T item = n.item;
        sentinel.prev = n.prev;
        n.prev.next = sentinel;
        n.item = null;
        n.next = null;
        n.prev = null;
        size = size - 1;
        return item;
    }

    public T get(int index) {
        if (index >= size()) {
            return null;
        }
        Node p = sentinel.next;
        int i = 0;
        while (i < index) {
            p = p.next;
        }
        return p.item;
    }

    public T getRecursiveHelper(int index, int nodeIndex, Node p) {
        if (nodeIndex == index) {
            return p.item;
        }
        return getRecursiveHelper(index, nodeIndex + 1, p.next);
    }
    public T getRecursive(int index) {
        if (index < 0 || index >= size()) {
            return null;
        }
        return getRecursiveHelper(0, 0, sentinel.next);
    }

    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int wizPos;
        private LinkedListIterator() {
            wizPos = 0;
        }

        public boolean hasNext() {
            return wizPos < size;
        }

        public T next() {
            T item = get(wizPos);
            wizPos += 1;
            return item;
        }
    }

    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> ol = (Deque<T>) o;
        if (ol.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(ol.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
