import java.util.LinkedList;
import java.util.ListIterator;


public class UnionFind {
    // TODO: Instance variables
    private int[] sets;
    private final int DEFAULT_SET_VALUE = -1;
    private int size;
    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        sets = new int[N];
        for (int i = 0; i < N; i++) {
            sets[i] = DEFAULT_SET_VALUE;
        }
        size = N;
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        int rootIndex = find(v);
        return -1 * sets[rootIndex];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return sets[v];
    }

    /*Connects v1 to the root of set that v2 belongs to,Note: v1'Tree is larger than v2*/
    private void connect(int v1, int v2) {
        int rootOfLargeTree = find(v1);
        int rootOfSmallTree = find(v2);
        if (rootOfLargeTree != rootOfSmallTree) {
            int sizeOfSmallTree = sizeOf(rootOfSmallTree);
            int sizeOfLargeTree = sizeOf(rootOfLargeTree);
            sets[rootOfSmallTree] = rootOfLargeTree;
            sets[rootOfLargeTree] = -(sizeOfSmallTree + sizeOfLargeTree);
        }
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (find(v1) == find(v2)) {
            return true;
        }
        return false;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        if (v >= size || v < 0) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }

        // TODO: YOUR CODE HERE
        if (sets[v] < 0) {
            return v;
        }

        if (sets[sets[v]] < 0) {
            return sets[v];
        }

        // 路径压缩之前，找到根节点
        int currIndex = v;
        LinkedList<Integer> chair = new LinkedList<>();

        // 迭代找到根节点，并将路径上所有节点加入链表
        while (sets[currIndex] >= 0) {
            chair.add(currIndex);
            currIndex = sets[currIndex];
        }

        // 现在 currIndex 是根节点，开始路径压缩，将路径上所有节点直接指向根节点
        ListIterator<Integer> iter = chair.listIterator(chair.size());
        while (iter.hasPrevious()) {
            sets[iter.previous()] = currIndex;
        }

        return currIndex;  // 返回根节点
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        int size1 = sizeOf(v1);
        int size2 = sizeOf(v2);
        if (size1 > size2) {
            connect(v1, v2);
        } else if (size1 == size2) {
            connect(v2, v1);
        } else {
            connect(v2, v1);
        }
    }

}
