import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private StuffNode sentinel;
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
        StuffNode newNode = new StuffNode(x, sentinel, sentinel.next);
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size++;
    }

    @Override
    public void addLast(T x) {

    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
