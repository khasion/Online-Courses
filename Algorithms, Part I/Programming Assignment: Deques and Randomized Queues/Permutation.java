import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Permutation {
    public static void main(String[] args)
    {
        int n = Integer.parseInt(args[0]);
        RandomizedQueue<String> q = new RandomizedQueue<String>();
        String str;
        int i = 0;

        for (i = 0; i < n; i++)
        {
            str = StdIn.readString();
            q.enqueue(str);
        }

        for (String s : q)
        {
            StdOut.println(s);
        }
    }
}