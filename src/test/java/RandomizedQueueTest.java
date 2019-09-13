import org.junit.Test;

import static org.junit.Assert.*;
public class RandomizedQueueTest {

    @Test
    public void shouldReturnDequeInstance() {
        RandomizedQueue randomizedQueue = new RandomizedQueue();
        assertNotNull(randomizedQueue);
    }

    @Test
    public void shouldbeEmptyAfterInitialization() {
        RandomizedQueue randomizedqueue = new RandomizedQueue();
        assertTrue(randomizedqueue.isEmpty());
    }

    @Test
    public void shouldNotbeEmptyAfterInsertOneElement() {
        RandomizedQueue<String> randomizedqueue = new RandomizedQueue<String>();
        randomizedqueue.enqueue("String");
        assertFalse(randomizedqueue.isEmpty());
    }

    @Test
    public void shouldReturnARandomItem() {
        RandomizedQueue<String> randomizedqueue = new RandomizedQueue<String>();
        randomizedqueue.enqueue("String 2");
        randomizedqueue.enqueue("String 1");
        randomizedqueue.enqueue("String 3");
        assertNotNull(randomizedqueue.dequeue());

    }

    @Test
    public void shouldBeAbleToDequeAllItems() {
        RandomizedQueue<String> randomizedqueue = new RandomizedQueue<String>();
        randomizedqueue.enqueue("String 2");
        randomizedqueue.enqueue("String 1");
        randomizedqueue.enqueue("String 0");
        randomizedqueue.dequeue();
        randomizedqueue.dequeue();
        randomizedqueue.dequeue();
        assertTrue(randomizedqueue.isEmpty());
    }

    @Test
    public void shouldReturnASampleItemButNotRemove() {
        RandomizedQueue<String> randomizedqueue = new RandomizedQueue<String>();
        randomizedqueue.enqueue("String 2");
        randomizedqueue.enqueue("String 1");
        randomizedqueue.enqueue("String 3");
        randomizedqueue.enqueue("String 4");
        randomizedqueue.enqueue("String 5");
        randomizedqueue.enqueue("String 6");
        System.out.println(randomizedqueue.sample());
        System.out.println(randomizedqueue.sample());
        System.out.println(randomizedqueue.sample());
    }

}