import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        // TODO: Fill in this function.
        Map<Character, Integer> myMap = new TreeMap<>();
        char first = 'a';
        for (int i = 1; i < 26 + 1; i++) {
            myMap.put((char)(first + i - 1), i);
        }
        return myMap;
    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        // TODO: Fill in this function.
        Map<Integer, Integer> squareMap = new TreeMap<>();
        for (int i = 0; i < nums.size(); i++) {
            squareMap.put(nums.get(i), nums.get(i) * nums.get(i));
        }
        return squareMap;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        // TODO: Fill in this function.
        Map<String, Integer> wordsMap = new TreeMap<>();
        for (int i = 0; i < words.size(); i++) {
            if (wordsMap.containsKey(words.get(i))) {
                wordsMap.put(words.get(i), wordsMap.get(words.get(i)) + 1);
            } else {
                wordsMap.put(words.get(i), 1);
            }
        }
        return wordsMap;
    }
}
