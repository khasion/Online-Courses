import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node<Item> head;
    private Node<Item> tail;
    private Integer length = 0;

    // construct an empty deque
    public Deque() 
    {
        head = null;
        tail = null;
    }

    // is the deque empty?
    public boolean isEmpty()
    {
        return (length == 0);
    }

    // return the number of items on the deque
    public int size()
    {
        return length;
    }

    // add the item to the front
    public void addFirst(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        // Insertion
        if (head == null)
        {
            head = new Node<Item>(item);
            tail = head;
        }
        else
        {
            Node<Item> temp = new Node<Item>(item);

            temp.setNext(head);
            head.setPrev(temp);
            head = temp;
        }
        length++;
    }

    // add the item to the back
    public void addLast(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }
        // Insertion
        if (tail == null)
        {
            tail = new Node<Item>(item);
            head = tail;
        }
        else
        {
            Node<Item> temp = new Node<Item>(item);

            temp.setPrev(tail);
            tail.setNext(temp);
            tail = temp;
        }

        length++;
    }

    // remove and return the item from the front
    public Item removeFirst()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        Node<Item> first = head;

        Item item = first.getItem();

        head = first.getNext();
        first.setNext(null);
        if (head != null)
        {
            head.setPrev(null);
        }
        first = null;

        length--;
        if (length == 0) { head = null; tail = null; }
        return item;
    }

    // remove and return the item from the back
    public Item removeLast()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        Node<Item> last = tail;

        Item item = last.getItem();

        tail = last.getPrev();
        last.setPrev(null);
        if (tail != null)
        {
            tail.setNext(null);
        }
        last = null;

        length--;
        if (length == 0) { head = null; tail = null; }
        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item>
    {
        private Node<Item> curr;

        public DequeIterator() { curr = head; }

        public boolean hasNext() { return (curr != null); }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next()
        {
            Item item = curr.getItem();
            curr = curr.getNext();
            return item;
        }
    }

    private class Node<Item>
    {
        private Item        item = null;
        private Node<Item>  next = null;
        private Node<Item>  prev = null;

        public Node() { }

        public Node(Item item)
        {
            this.item = item;
            next = null;
            prev = null;
        }

        public Node<Item>   getNext()                   { return next; }
        public void         setNext(Node<Item> next)    { this.next = next; }

        public Node<Item>   getPrev()                   { return prev; }
        public void         setPrev(Node<Item> prev)    { this.prev = prev; }

        public Item         getItem()                   { return item; }
        public void         setItem(Item item)          { this.item = item; }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        Deque<String> dq = new Deque<String>();

        dq.addFirst("A");
        dq.addLast("B");
        System.out.println(dq.removeFirst());
        System.out.println(dq.removeLast());
        System.out.println(dq.isEmpty());
        dq.addFirst("A");
        dq.addLast("B");
        dq.addFirst("C");
        dq.addFirst("D");
        dq.addLast("E");
        dq.addLast("F");
        System.out.println(dq.removeLast());
        System.out.println(dq.removeFirst());

        for (String s : dq)
        {
            System.out.println(s);
        }
    }

}