import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {

    private NodeItem head, tail;
    private int size;
    public Deque() {
        int size = 0;
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        deque.addFirst("A");
        deque.addLast("B");
        System.out.println(deque.size());
        System.out.println(deque.removeFirst());
        System.out.println(deque.removeLast());
        System.out.println(deque.isEmpty());
    }

    public Iterator<Item> iterator() {
        return new DequeIterator<>(this);
    }

    public boolean isEmpty() {
        return head == null;
    }

    public void addFirst(Item data) {
        if (data == null) {
            throw new IllegalArgumentException("Null value provided");
        }
        NodeItem NodeItem = new NodeItem<Item>(data, null);
        if (head == null) {
            head = tail = NodeItem;
        } else {
            NodeItem.next = head;
            head = NodeItem;
        }
        size++;
    }

    public void addLast(Item data) {
        if (data == null) {
            throw new IllegalArgumentException("Null value provided");
        }
        NodeItem NodeItem = new NodeItem<Item>(data, null);
        if (tail == null) {
            head = tail = NodeItem;
        } else {
            tail.next = NodeItem;
            tail = NodeItem;
        }
        size++;
    }

    public int size() {
        return size;
    }

    public Item removeFirst() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        if (size() == 1) {
            Item item = (Item) tail.data;
            head = tail = null;
            size--;
            return item;
        }
        if (head != null) {
            Item item = (Item) head.data;
            head = head.next;
            size--;
            return item;
        } else {
            return null;
        }
    }

    public Item removeLast() {
        if (isEmpty()) {
            throw new java.util.NoSuchElementException("Deque is empty");
        }
        if (size() == 1) {
            Item item = (Item) tail.data;
            head = tail = null;
            size--;
            return item;
        }
        if (tail != null) {
            Item item = (Item) tail.data;
            NodeItem current = head;
            while (current.next != tail) {
                current = current.next;
            }
            current.next = null;
            tail = current;
            size--;
            return item;
        } else {
            return null;
        }
    }

    private class DequeIterator<Item> implements Iterator<Item> {
        NodeItem head;

        public DequeIterator(Deque<Item> deque) {
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

    private class NodeItem<Item> {
        Item data;
        NodeItem next;

        public NodeItem(Item data, NodeItem next) {
            this.data = data;
            this.next = next;
        }

        public NodeItem<Item> getNext() {
            return next;
        }

        public void setNext(NodeItem next) {
            this.next = next;
        }
    }
}

