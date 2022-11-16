import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test
    public void testIsPalindrome() {
        boolean actual = palindrome.isPalindrome("alpha");

        assertFalse(actual);

        actual = palindrome.isPalindrome("alphae");

        assertFalse(actual);

        actual = palindrome.isPalindrome("betaateb");

        assertTrue(actual);

        actual = palindrome.isPalindrome("aka");

        assertTrue(actual);

    }

    @Test
    public void testIsPalindromeOffByOne() {
        CharacterComparator cc = new OffByOne();
        boolean actual = palindrome.isPalindrome("flake", cc);

        assertTrue(actual);

        actual = palindrome.isPalindrome("alphae");

        assertFalse(actual);

        actual = palindrome.isPalindrome("betaateb");

        assertTrue(actual);

        actual = palindrome.isPalindrome("aka");

        assertTrue(actual);

    }

}
