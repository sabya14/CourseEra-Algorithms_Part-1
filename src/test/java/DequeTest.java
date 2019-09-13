import org.junit.Test;

import static org.junit.Assert.*;

public class DequeTest {

    @Test
    public void shouldReturnDequeInstance() {
        Deque deque = new Deque();
        assertNotNull(deque);
    }


    @Test
    public void shouldbeEmptyAfterInitialization() {
        Deque deque = new Deque();
        assertTrue(deque.isEmpty());
    }

    @Test
    public void shouldNotbeEmptyAfterInsertOneElement() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("String");
        assertFalse(deque.isEmpty());
    }

    @Test
    public void shouldNotbeEmptyAfterInsertOneElementUsingAddLast() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("String");
        assertFalse(deque.isEmpty());
    }

    @Test
    public void shouldReturnCorrectSize() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("String");
        assertEquals(1, deque.size());
        deque.addFirst("String 1");
        deque.addFirst("String 2");
        assertEquals(3, deque.size());
    }

    @Test
    public void shouldReturnCorrectSizeAfterAddingToTail() {
        Deque<String> deque = new Deque<String>();
        deque.addLast("String");
        deque.addLast("String 1");
        deque.addLast("String 2");
        assertEquals(3, deque.size());
    }

    @Test
    public void shouldRemoveShouldDecreaseSizeByOne() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("String");
        deque.addFirst("String 1");
        deque.addFirst("String 2");
        assertEquals("String 2", deque.removeFirst());
        assertEquals(2, deque.size());
    }

    @Test
    public void shouldRemoveShouldDecreaseSizeByOneWhenUsingRemoveFromLast() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("String");
        deque.addFirst("String 1");
        deque.addFirst("String 2");
        assertEquals("String", deque.removeLast());
        assertEquals(2, deque.size());
    }

    @Test
    public void testDequeIteratorWorks() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("String");
        deque.addFirst("String 1");
        deque.addFirst("String 2");
        assertEquals("String 2", deque.iterator().next());
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void shouldRaiseExceptionWhenNullAdded() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("String");
        deque.addFirst(null);
    }

    @Test(expected = java.lang.IllegalArgumentException.class)
    public void shouldRaiseExceptionWhenNullAddedAtLast() {
        Deque<String> deque = new Deque<String>();
        deque.addFirst("String");
        deque.addLast(null);
    }

    @Test(expected = java.util.NoSuchElementException.class)
    public void shouldRaiseExceptionWhenRemoveCalledWhenDequeIsEmpty() {
        Deque<String> deque = new Deque<String>();
        deque.removeFirst();
    }
}