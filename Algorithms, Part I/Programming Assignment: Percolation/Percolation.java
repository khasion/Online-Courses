import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] grid;
    private WeightedQuickUnionUF quf;
    private int opensites = 0;
    private int dim;
    private int top;
    private int bot;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) 
    {

        if (n <= 0)
        {
            throw new IllegalArgumentException();
        }

        int c, i, j;
        dim = n;
        grid = new boolean[dim+2][dim+2];
        quf = new WeightedQuickUnionUF(n*n + 3);

        c = 1;
        for (i = 1; i < dim+1; i++)
        {
            for (j = 1; j < dim+1; j++)
            {
                grid[i][j] = false;
                c++;
            }
        }

        bot = c;
        top = 0;
        for (i = 1; i < dim+1; i++)
        {
            quf.union(top, convert2dto1d(1, i));
            quf.union(bot, convert2dto1d(n, i));
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col)
    {
        if (row < 1 || row > dim || col < 1 || col > dim)
        {
            throw new IllegalArgumentException();
        }

        int curr    = convert2dto1d(row, col);
        int up      = convert2dto1d(row-1, col);
        int down    = convert2dto1d(row+1, col);
        int right   = convert2dto1d(row, col+1);
        int left    = convert2dto1d(row, col-1);

        if (!isOpen(row, col))
        {
            grid[row][col] = true;
            if (row-1 >= 1 && isOpen(row-1, col)) { quf.union(curr, up); }
            if (row+1 <= dim && isOpen(row+1, col)) { quf.union(curr, down); }
            if (col-1 >= 1  && isOpen(row, col-1)) { quf.union(curr, left); }
            if (col+1 <= dim  && isOpen(row, col+1)) { quf.union(curr, right); }
            opensites++;
        }
    }
    
    // is the site (row, col) open?
    public boolean isOpen(int row, int col)
    {
        if (row < 1 || row > dim || col < 1 || col > dim)
        {
            throw new IllegalArgumentException();
        }

        return grid[row][col];
    }
    
    // is the site (row, col) full?
    public boolean isFull(int row, int col)
    {
        if (row < 1 || row > dim || col < 1 || col > dim)
        {
            throw new IllegalArgumentException();
        }

        return (isOpen(row, col) && quf.find(convert2dto1d(row, col)) == quf.find(top));
    }
    
    // returns the number of open sites
    public int numberOfOpenSites()
    {
        return opensites;
    }
    
    // does the system percolate?
    public boolean percolates()
    {
        return (quf.find(bot) == quf.find(top));
    }

    private int convert2dto1d(int row, int col)
    {
        if (row < 1 || row > dim || col < 1 || col > dim)
        {
            return 0;
        }

        int pos = dim*(row - 1) + col - 1;
        return pos;
    }
}
