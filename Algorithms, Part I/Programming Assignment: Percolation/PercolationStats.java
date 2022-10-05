import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private double[] values;
    private int t;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials)
    {
        if (n <= 0 || trials <= 0)
        {
            throw new IllegalArgumentException();
        }

        values = new double[trials];
        t = trials;

        int i = 0;
        for (i = 0; i < trials; i++)
        {
            Percolation perc = new Percolation(n);
            while (!perc.percolates())
            {
                perc.open((int) StdRandom.uniformDouble(1, n+1), (int) StdRandom.uniformDouble(1, n+1));
            }
            values[i] = (perc.numberOfOpenSites()/(double)((n)*(n)));
        }
    }

    // sample mean of percolation threshold
    public double mean()
    {
        return StdStats.mean(values);
    }

    // sample standard deviation of percolation threshold
    public double stddev()
    {
        return StdStats.stddev(values);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo()
    {
        return mean() - (1.96*stddev())/(Math.sqrt(t)); 
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi()
    {
        return mean() + (1.96*stddev())/(Math.sqrt(t)); 
    }

   // test client (see below)
   public static void main(String[] args)
   {
        PercolationStats stats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean                    = " + stats.mean());
        System.out.println("stddev                  = " + stats.stddev());
        System.out.println("95% confidence interval = [" + stats.confidenceLo() + 
                ", " + stats.confidenceHi() + "]");
   }
}
