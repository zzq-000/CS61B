/** Performs some basic linked list tests. */
public class ArrayDequeTest {

    /* Utility method for printing out empty checks. */
    public static boolean checkEmpty(boolean expected, boolean actual) {
        ArrayDeque<Integer> a = new ArrayDeque<>();

        expected = true;
        actual = a.isEmpty();
        if (expected != actual) {
            System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Utility method for printing out empty checks. */
    public static boolean checkSize(int expected, int actual) {
        ArrayDeque<Integer> a = new ArrayDeque<>();
        a.addFirst(5);
        a.addLast(8);
        actual = a.size();
        expected = 2;

        if (expected != actual) {
            System.out.println("size() returned " + actual + ", but expected: " + expected);
            return false;
        }
        return true;
    }

    /* Prints a nice message based on whether a test passed.
     * The \n means newline. */
    public static void printTestStatus(boolean passed) {
        if (passed) {
            System.out.println("Test passed!\n");
        } else {
            System.out.println("Test failed!\n");
        }
    }

    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public static void addIsEmptySizeTest() {
        System.out.println("Running add/isEmpty/Size test.");
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<String> lld1 = new ArrayDeque<>();

        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        passed = checkSize(1, lld1.size()) && passed;
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.addLast("middle");
        passed = checkSize(2, lld1.size()) && passed;

        lld1.addLast("back");
        passed = checkSize(3, lld1.size()) && passed;

        System.out.println("Printing out deque: ");
        lld1.printDeque();

        printTestStatus(passed);

//		for (int i = 0; i < lld1.size(); i++) {
//			System.out.printf("%s %s\n", lld1.getRecursive(i), lld1.getIndex(i));
//		}
//
//		lld1.removeLast();
//
//		for (int i = 0; i < lld1.size(); i++) {
//			System.out.printf("%s %s\n", lld1.getRecursive(i), lld1.getIndex(i));
//		}

    }

    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public static void addRemoveTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        // should be empty
        boolean passed = checkEmpty(true, lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        passed = checkEmpty(false, lld1.isEmpty()) && passed;

        lld1.removeFirst();
        // should be empty
        passed = checkEmpty(true, lld1.isEmpty()) && passed;

        printTestStatus(passed);
    }
    public static void circleTest() {

        System.out.println("Running add/remove test.");

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        ArrayDeque<Integer> lld1 = new ArrayDeque<>();
        // should be empty
        lld1.addFirst(10);
        lld1.addFirst(20);
        lld1.addFirst(30);
        lld1.addFirst(40);
        lld1.addFirst(50);
        lld1.addFirst(60);
        lld1.addFirst(70);
        lld1.addFirst(80);
        lld1.addFirst(90);
        lld1.addFirst(100);
        lld1.addFirst(110);
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();
        lld1.removeFirst();

        lld1.printDeque();
        System.out.printf("\n%d,%b",lld1.size(),lld1.isEmpty());
    }
    public static void main(String[] args) {
//        checkSize(0,0);
//        System.out.println("Running tests.\n");
        addIsEmptySizeTest();
        addRemoveTest();
        circleTest();
//        System.out.print(1);
//        System.out.print(2);
//        System.out.print(3);
    }
}