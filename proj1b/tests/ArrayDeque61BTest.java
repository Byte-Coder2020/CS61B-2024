import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

    @Test
    @DisplayName("addFirst works on an empty deque")
    public void addFirstFromEmptyTest() {
         Deque61B<String> ad1 = new ArrayDeque61B<>();
         ad1.addFirst("a");
         String excepted = "a";
         assertThat(ad1.toList()).containsExactly(excepted);
    }

    @Test
    @DisplayName("addLast works on an empty deque")
    public void addLastFromEmptyTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("a");
        String excepted = "a";
        assertThat(ad1.toList()).containsExactly(excepted);
    }

    @Test
    @DisplayName("addFirst works on an no-empty deque")
    public void addFirstFromNoEmptyTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addFirst("a");
        ad1.addFirst("b");
        assertThat(ad1.toList()).containsExactly("a", "b");
    }

    @Test
    @DisplayName("addLast works on an no-empty deque")
    public void addLastFromNoEmptyTest() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("a");
        ad1.addLast("b");
        assertThat(ad1.toList()).containsExactly("a", "b");
    }

    @Test
    @DisplayName("addFirst works when called on a full underlying array")
    public void addFirstTriggerResize() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addFirst("a");
        ad1.addFirst("b");
    }

    @Test
    @DisplayName("addLast works when called on a full underlying array")
    public void addLastTriggerResize() {
        Deque61B<String> ad1 = new ArrayDeque61B<>();
        ad1.addLast("a");
        ad1.addLast("b");
    }


    @Test
    @DisplayName("Check removeFirst works")
    public void removeFirstTest() {

    }

    @Test
    @DisplayName("Check removeLast works")
    public void removeLastTest() {

    }

    @Test
    @DisplayName("remove first to empty")
    public void removeFirstTOEmptyTest() {

    }

    @Test
    @DisplayName("remove last to empty")
    public void removeLastTOEmptyTest() {

    }

    @Test
    @DisplayName("remove first to one")
    public void removeFirstTOOneTest() {

    }

    @Test
    @DisplayName("remove last to one")
    public void removeLastToOneTest() {

    }

    @Test
    @DisplayName("remove first to trigger resize")
    public void removeFirstToTriggerResizeTest() {

    }

    @Test
    @DisplayName("remove last to trigger resize")
    public void removeLastToTriggerResizeTest() {

    }

    @Test
    @DisplayName("Get valid test")
    public void getVaildTest() {

    }

    @Test
    @DisplayName("Check out of bound on a large index")
    public void getObbLargeTest() {

    }

    @Test
    @DisplayName("Check out out of bound on a negative index")
    public void getObbNeg() {

    }
}
