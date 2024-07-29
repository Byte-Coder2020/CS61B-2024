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


}
