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
        top = c+1;
        for (i = 1; i < dim+1; i++)
        {
            quf.union(top, i);
            quf.union(bot, c-i);
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
            quf.union(curr, up);
            quf.union(curr, down);
            quf.union(curr, right);
            quf.union(curr, left);
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

        return (quf.find(convert2dto1d(row, col)) == quf.find(top) && isOpen(row, col));
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
        int pos = dim*(row - 1) + col - 1;
        return pos;
    }
}
