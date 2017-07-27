import junit.framework.TestCase;
import java.util.*;

public class StringSearcherTest extends TestCase {
    private StringSearcher searcher;

    protected void setUp() throws Exception {
        searcher = new StringSearcher();
    }

    public void testBCTNoRepeatLetters() throws Exception {
        String input = "CAT";
        HashMap<String, Integer> output = searcher.makeBadCharTable(input, "S");
        assertEquals(new Integer(2), output.get("C"));
        assertEquals(new Integer(1), output.get("A"));
        assertEquals(new Integer(3), output.get("T"));
        assertEquals(new Integer(3), output.get("S"));
    }

    public void testBCTRepeatInternalLetter() throws Exception {
        String input = "CABAT";
        HashMap<String, Integer> output = searcher.makeBadCharTable(input, "");
        assertEquals(new Integer(4), output.get("C"));
        assertEquals(new Integer(1), output.get("A"));
        assertEquals(new Integer(2), output.get("B"));
        assertEquals(new Integer(5), output.get("T"));
    }

    public void testBCTRepeatFinalLetter() throws Exception {
        String input = "BREFBE";
        HashMap<String, Integer> output = searcher.makeBadCharTable(input, "");
        assertEquals(new Integer(1), output.get("B"));
        assertEquals(new Integer(4), output.get("R"));
        assertEquals(new Integer(3), output.get("E"));
        assertEquals(new Integer(2), output.get("F"));
    }

    public void testGetBadCharShiftReturns1() throws Exception {
        String text = "CATARAT";
        String pattern = "CRAT";
        int position = 0;
        HashMap<String, Integer> table = searcher.makeBadCharTable(pattern, text);
        assertEquals(1, searcher.getBadCharShift(text, pattern, position, table));
    }

    public void testGetBadCharShiftReturns2() throws Exception {
        String text = "CATARAT";
        String pattern = "CRAT";
        int position = 1;
        HashMap<String, Integer> table = searcher.makeBadCharTable(pattern, text);
        assertEquals(2, searcher.getBadCharShift(text, pattern, position, table));
    }
}
