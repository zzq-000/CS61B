import static org.junit.Assert.*;
import org.junit.Test;
public class TestArrayDequeGold {
    public static final int TIMES = 100;
    public static final double A1 = 0.25;
    public static final double A2 = 0.5;
    public static final double A3 = 0.75;
    public static final double A4 = 1;
    @Test
    public void testArrayDequeGold() {
        StudentArrayDeque<Integer> actual = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> expect = new ArrayDequeSolution<>();
        String info = "";
        for (int i = 0; i < TIMES; i++) {
            double action = StdRandom.uniform();
            if (action < A1) {
                actual.addLast(i);
                expect.addLast(i);
                info += "addLast(" + i + ")\n";
            } else if (action < A2) {
                actual.addFirst(i);
                expect.addFirst(i);
                info += "addFirst(" + i + ")\n";
            } else if (action < A3) {
                if (!actual.isEmpty()) {
                    Integer a = actual.removeFirst();
                    Integer b = expect.removeFirst();
                    info += "removeFirst()\n";
                    assertEquals(info, a, b);
                }

            } else if (action < A4) {
                if (!actual.isEmpty()) {
                    Integer a = actual.removeLast();
                    Integer b = expect.removeLast();
                    info += "removeLast()\n";
                    assertEquals(info, a, b);
                }

            }
        }
    }
}
