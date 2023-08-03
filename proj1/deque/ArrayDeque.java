package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }
    private int arrayIndex(int index) {
        if (nextFirst + 1 + index >= items.length) {
            return nextFirst + 1 + index - items.length;
        } else {
            return nextFirst + 1 + index;
        }
    }

    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        nextFirst = 4;
        nextLast = nextFirst + size + 1;
        for (int i = 0; i < size; i += 1) {
            int idx = arrayIndex(i);
            newItems[nextFirst + 1 + i] = items[idx];
        }
        items = newItems;
    }

    public void addFirst(T item) {
        if (size == items.length - 2) {
            resize((int) (items.length * 2));
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        if (nextFirst < 0) {
            nextFirst = items.length - 1;
        }
        size += 1;
    }

    public void addLast(T item) {
        if (size == items.length - 2) {
            resize((int) (items.length * 2));
        }
        items[nextLast] = item;
        nextLast += 1;
        if (nextLast == items.length) {
            nextLast = 0;
        }
        size += 1;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        int idx = arrayIndex(0);
        T item = items[idx];
        items[idx] = null;
        size -= 1;
        nextFirst = idx;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        int idx = arrayIndex(size - 1);
        T item = items[idx];
        items[idx] = null;
        size -= 1;
        nextLast = idx;
        return item;
    }

    public T get(int index) {
        int idx = arrayIndex(index);
        return items[idx];
    }

    public void printDeque() {
        for (T i : this) {
            System.out.print(i + " ");
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;

        private ArrayDequeIterator() {
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
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> oa = (Deque<T>) o;
        if (oa.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i += 1) {
            if (!(oa.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}
