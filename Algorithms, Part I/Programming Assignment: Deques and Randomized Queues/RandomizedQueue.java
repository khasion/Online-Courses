import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr;
    private Integer length = 10;
    private Integer total = 0;

    // construct an empty randomized queue
    public RandomizedQueue()
    {
        length = 10;
        arr = (Item[]) new Object[length];
        total = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty()
    {
        return (total == 0);
    }

    // return the number of items on the randomized queue
    public int size()
    {
        return total;
    }

    // add the item
    public void enqueue(Item item)
    {
        if (item == null)
        {
            throw new IllegalArgumentException();
        }

        if (total >= length)
        {
            int i;
            Item[] new_arr = (Item[]) new Object[length*=2 + 1];
            for (i = 0; i < total; i++)
            {
                new_arr[i] = arr[i];
            }
            arr = new_arr;
        }
        arr[total] = item;
        total++;
    }

    // remove and return a random item
    public Item dequeue()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        Integer index = StdRandom.uniformInt(0, total);

        Item item = arr[index];
        arr[index] = arr[total-1];
        arr[total-1] = null;
        total--;

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException();
        }

        Integer index = StdRandom.uniformInt(0, total);

        Item item = arr[index];
        return item;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() { return new RandomizedQueueIterator(); }

    private class RandomizedQueueIterator implements Iterator<Item>
    {
        private RandomizedQueue<Item> rand;
        private Integer index = 0;

        public RandomizedQueueIterator()
        {
            int i;
            rand = new RandomizedQueue<Item>();

            for (i = 0; i < total; i++)
            {
                rand.enqueue(arr[i]);
            }
        }

        public boolean hasNext() { return (!rand.isEmpty()); }

        public void remove() { throw new UnsupportedOperationException(); }

        public Item next()
        {
            if (rand.isEmpty())
            {
                throw new NoSuchElementException();
            }
            return (rand.dequeue());
        }
    }

    // unit testing (required)
    public static void main(String[] args)
    {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();

        rq.enqueue("A");
        rq.enqueue("B");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.isEmpty());
        rq.enqueue("A");
        rq.enqueue("B");
        rq.enqueue("C");
        rq.enqueue("D");

        System.out.println(rq.sample());
        System.out.println(rq.sample());

        System.out.println("--------");

        Iterator<String> i = rq.iterator();
        while (i.hasNext())
        {
            String s = i.next();
            System.out.println(s);
        }
    }

}