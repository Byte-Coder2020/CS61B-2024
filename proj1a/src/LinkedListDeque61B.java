import java.util.List;
import java.util.ArrayList; // import the ArrayList class

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private StuffNode<T> sentinel;
    private int size;

    public class StuffNode<T> {
        private T item;
        private StuffNode prev;
        private StuffNode next;
        public StuffNode(T i, StuffNode p, StuffNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }
    public LinkedListDeque61B() {
        sentinel = new StuffNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel.prev;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        StuffNode<T> newNode = new StuffNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {
        StuffNode<T> newNode = new StuffNode(x, sentinel.prev, sentinel);
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size++;
    }

    @Override
    public List<T> toList() {
        List<T> returnList = new ArrayList<>();
        StuffNode<T> ptr = sentinel.next;
        while (ptr != sentinel) {
            returnList.add(ptr.item);
            ptr = ptr.next;
        }
        return returnList;
    }

    @Override
    public boolean isEmpty() {
        if (sentinel.next == sentinel) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T firstItem = (T) sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        return firstItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastItem = (T) sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        return lastItem;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size || isEmpty()) {
            return null;
        }
        StuffNode<T> ptr = sentinel.next;
        T target = null;
        int current = 0;
        while (ptr != sentinel) {
            if (index == current){
                target = ptr.item;
                break;
            }
            ptr = ptr.next;
            current++;
        }
        return target;
    }

    @Override
    public T getRecursive(int index) {
        if (index < 0 || index >= size || isEmpty()) {
            return null;
        }
        return (T) getRecursive(index, sentinel.next);
    }

    private T getRecursive(int index, StuffNode<T> rest) {
        if (index == 0) {
            return rest.item;
        }
        return (T) getRecursive(index - 1, rest.next);
    }
}
