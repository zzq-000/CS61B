package synthesizer;
import edu.princeton.cs.algs4.In;
import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        for(int i = 0; i < 5; i++){
            arb.dequeue();
        }
        for(int i = 0; i < 3; i++){
            arb.enqueue(i);
        }

    }

    @Test
    public void testIter() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer<>(10);
        for (int i = 0; i < 10; i++) {
            arb.enqueue(i);
        }
        for(int i = 0; i < 5; i++){
            arb.dequeue();
        }
        for(int i = 0; i < 3; i++){
            arb.enqueue(i);
        }
        for (Integer i: arb) {
            System.out.println(i);
        }
//        Iterator<Integer> ite= arb.iterator();
//        while (ite.hasNext()) {
//            Integer t = ite.next();
//            System.out.println(t);
//        }
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
