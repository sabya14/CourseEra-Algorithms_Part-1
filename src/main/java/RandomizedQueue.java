import java.util.Iterator;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private QueueNode head;
    private int size;

    public RandomizedQueue() {
        int size = 0;
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> queue = new RandomizedQueue<>();
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        System.out.println(queue.size());
        for (Integer i : queue) {
            System.out.println(i);
        }
        while (!queue.isEmpty()) System.out.println(queue.dequeue());
        System.out.println(queue.size());
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedDequeIterator<>(this);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item data) {
        if (data == null) {
            throw new IllegalArgumentException("Null value provided");
        }
        QueueNode node = new QueueNode<Item>(data, null);
        if (head == null) {
            head = node;
            size++;
        } else {
            node.next = head;
            head = node;
            size++;
        }
    }

    private QueueNode getRandomNode() {
        if (size == 1) {
            return null;
        }
        Random rand = new Random();
        int randomIndex = rand.nextInt(size - 1);
        QueueNode node = head;
        int counter = 0;
        while (counter < randomIndex) {
            node = node.next;
            counter++;
        }
        return node;
    }

    public Item dequeue() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Randomized Queue is empty");
        }
        QueueNode node = getRandomNode();
        if (node != null) {
            QueueNode nextNode = node.next;
            Item item = (Item) nextNode.data;
            if (nextNode.next != null) {
                node.next = nextNode.next;
            } else {
                node.next = null;
            }
            size--;
            return item;
        } else {
            Item item = (Item) head.data;
            head = null;
            size--;
            return item;
        }
    }

    public Item sample() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Randomized Queue is empty");
        }
        QueueNode node = getRandomNode();
        if (node != null) {
            QueueNode nextNode = node.next;
            Item item = (Item) nextNode.data;
            return item;
        } else {
            Item item = (Item) head.data;
            return item;
        }
    }


    private class QueueNode<Item> {
        Item data;
        QueueNode next;

        public QueueNode(Item data, QueueNode next) {
            this.data = data;
            this.next = next;
        }

        public QueueNode<Item> getNext() {
            return next;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }
    }

    private class RandomizedDequeIterator<Item> implements Iterator<Item> {

        QueueNode head;
        public RandomizedDequeIterator(RandomizedQueue<Item> deque) {
            this.head = deque.head;
        }

        @Override
        public boolean hasNext() {
            return head != null;
        }

        @Override
        public Item next() {
            if (head != null) {
                Item data = (Item) head.data;
                head = head.getNext();
                return data;
            } else {
                throw new java.util.NoSuchElementException("Deque is empty");
            }
        }
        @Override
        public void remove() {
            throw new UnsupportedOperationException("Deque is empty");
        }

    }

}
