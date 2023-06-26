import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.MyStack;

public class MyStackTests<E> {

    MyStack<E> stackOne, stackTwo, stackThree;
    String testString = "String 0 String 1 String 2 String 3";
    String testString2 = "String 0 String 1 String 2";

    @BeforeEach
    void setUp() throws Exception {
        stackOne = new MyStack<>();
        stackTwo = new MyStack<>();

        stackOne.push((E) "String 0");
        stackOne.push((E) "String 1");
        stackOne.push((E) "String 2");
        stackOne.push((E) "String 3");

        stackTwo.push((E) "String Zero");
        stackTwo.push((E) "String One");
        stackTwo.push((E) "String Two");
        stackTwo.push((E) "String Three");
    }

    @AfterEach
    void tearDown() {
        stackOne.clear();
        stackTwo.clear();
    }

    @Test
    void testMyStack() {
        assertNull(stackThree);
        stackThree = new MyStack<>();
        assertNotNull(stackThree);
        assertEquals(0, stackThree.size());
    }

    @Test
    void testPush() {
        assertEquals(testString, stackOne.toString());
        stackOne.push((E)"String 4");
        assertEquals(testString + " String 4", stackOne.toString());
    }

    @Test
    void testPop() {
        assertEquals(testString, stackOne.toString());
        E poppedElement = (E)stackOne.pop();
        assertEquals("String 3", poppedElement);
        assertEquals(testString2, stackOne.toString());
    }

    @Test
    void testPeek() {
        assertEquals(testString, stackOne.toString());
        E peekedElement = (E)stackOne.peek();
        assertEquals("String 3", peekedElement);
    }

    @Test
    void testEquals() {
        stackThree = stackOne;
        assertEquals(stackOne, stackThree);
        assertTrue(stackOne.equals(stackThree));
    }

    @Test
    void testNotEquals() {
        assertNotEquals(stackOne, stackTwo);
        assertFalse(stackOne.equals(stackTwo));
    }

    @Test
    void testSearch() {
        assertEquals(1, stackOne.search((E)"String 3"));
        assertEquals(2, stackOne.search((E)"String 2"));
        assertEquals(3, stackOne.search((E)"String 1"));
        assertEquals(4, stackOne.search((E)"String 0"));
        assertEquals(-1, stackOne.search((E)"String 5"));
        assertEquals(1, stackTwo.search((E)"String Three"));
        assertEquals(2, stackTwo.search((E)"String Two"));
        assertEquals(3, stackTwo.search((E)"String One"));
        assertEquals(4, stackTwo.search((E)"String Zero"));
        assertEquals(-1, stackTwo.search((E)"String Five"));
    }
}
