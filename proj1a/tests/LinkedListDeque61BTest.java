import edu.princeton.cs.algs4.In;
import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/** Performs some basic linked list tests. */
public class LinkedListDeque61BTest {

     @Test
     /** In this test, we have three different assert statements that verify that addFirst works correctly. */
     public void addFirstTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addFirst("back"); // after this call we expect: ["back"]
         assertThat(lld1.toList()).containsExactly("back").inOrder();

         lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
         assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

         lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
     }

     @Test
     /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
      *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
     public void addLastTestBasic() {
         Deque61B<String> lld1 = new LinkedListDeque61B<>();

         lld1.addLast("front"); // after this call we expect: ["front"]
         lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
         lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
         assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
     }

     @Test
     /** This test performs interspersed addFirst and addLast calls. */
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
     }

    // Below, you'll write your own tests for LinkedListDeque61B.

    @Test
    /** This test determine this list is empty or is noempty. */
    public void testIsEmpty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

        assertThat(lld1.isEmpty()).isTrue();

        lld1.addLast(1);
        assertThat(lld1.isEmpty()).isFalse();
    }

    @Test
    /** This test judges this list's size is zero,and one. */
    public void testSize() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        int excepted = 0;
        assertThat(lld1.size()).isEqualTo(excepted);

        lld1.addLast(1);
        excepted = 1;
        assertThat(lld1.size()).isEqualTo(excepted);
    }

    @Test
    /** Test get method when index is out of bounding for Empty list. */
    public void testGetInEmptyList() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(27138)).isNull();
    }

    @Test
    /** Test get method when index is out of bounding for NO-Empty list. */
    public void testGetInNoEmptyList() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld1.get(-1)).isNull();
        assertThat(lld1.get(27138)).isNull();
    }

    @Test
    /** Test get method in index 0 / 1 for a General List. */
    public void testGeneralList() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        int excepted0 = 1;
        int excepted1 = 2;
        assertThat(lld1.get(0)).isEqualTo(excepted0);
        assertThat(lld1.get(1)).isEqualTo(excepted1);
    }

    @Test
    /** Test getRecursive method when index is out of bounding for Empty list. */
    public void testGetRecursiveInEmptyList() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.getRecursive(-1)).isNull();
        assertThat(lld1.getRecursive(27138)).isNull();
    }

    @Test
    /** Test getRecursive method when index is out of bounding for NO-Empty list. */
    public void testGetRecursiveInNoEmptyList() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        assertThat(lld1.getRecursive(-1)).isNull();
        assertThat(lld1.getRecursive(27138)).isNull();
    }

    @Test
    /** Test getRecursive method in index 0 / 1 for a General List. */
    public void testGetRecursiveGeneralList() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        int excepted0 = 1;
        int excepted1 = 2;
        assertThat(lld1.getRecursive(0)).isEqualTo(excepted0);
        assertThat(lld1.getRecursive(1)).isEqualTo(excepted1);
    }

    @Test
    public void remove_first() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(2).inOrder();
    }

    @Test
    public void remove_last() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void remove_first_to_empty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.removeFirst()).isNull();
    }

    @Test
    public void remove_last_to_empty() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        assertThat(lld1.removeLast()).isNull();
    }

    @Test
    public void remove_first_to_one() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(4).inOrder();
    }

    @Test
    public void remove_last_to_one() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.removeLast();
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
    }
}