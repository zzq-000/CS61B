public class Palindrome {

    public Deque<Character> wordToDeque(String word){
        LinkedListDeque<Character> deque = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            deque.addLast(word.charAt(i));
        }
        return deque;
    }

    public boolean isPalindrome(String word) {
        if (word == null || word.length() < 2) {
            return true;
        }
        Deque<Character> deque = wordToDeque(word);

        String reverse = "";
        while (deque.size() > 0) {
            reverse += deque.removeLast();
        }
        return reverse.equals(word);
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word == null || word.length() < 2) {
            return true;
        }
        int head = 0;
        int tail = word.length() - 1;

        while (head < tail && cc.equalChars(word.charAt(head), word.charAt(tail))) {
            head += 1;
            tail -= 1;
        }
        return head >= tail;
    }
}
