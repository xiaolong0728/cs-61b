package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        correct.addLast(5);
        correct.addLast(10);
        correct.addLast(15);

        broken.addLast(5);
        broken.addLast(10);
        broken.addLast(15);

        assertEquals(correct.size(), broken.size());

        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
        assertEquals(correct.removeLast(), broken.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> correct = new AListNoResizing<>();
        BuggyAList<Integer> broken = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                //System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int correctSize = correct.size();
                int brokenSize = broken.size();
                //System.out.println("size: " + correctSize + "size: " + brokenSize);
                assertEquals(correctSize, brokenSize);
            } else if (operationNumber == 2) {
                if (correct.size() > 0 && broken.size() > 0) {
                    int correctLast = correct.getLast();
                    int brokenLast = broken.getLast();
                    //System.out.println("getLast(" + correctLast + ")" + " getLast(" + brokenLast + ")");
                    assertEquals(correctLast, brokenLast);
                }
            } else if (operationNumber == 3) {
                if (correct.size() > 0 && broken.size() > 0) {
                    int correctLast = correct.removeLast();
                    int brokenLast = broken.removeLast();
                    //System.out.println("removeLast(" + correctLast + ")" + " removeLast(" + brokenLast + ")");
                    assertEquals(correctLast, brokenLast);
                }
            }
        }
    }
}
