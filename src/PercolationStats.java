import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private final double[] results;

    public PercolationStats(int n, int trials) {
        if ( n <= 0 || trials <= 0 ) throw new IllegalArgumentException();
        this.results = new double[trials];
        int sitesCount = n * n;
        for (int i = 0; i < trials; i++){
            results[i] = (double)performSimulation(n).numberOfOpenSites() / (double)sitesCount;
        }
    }

    public double mean() {
        return StdStats.mean(results);
    }


    public double stddev() {
        return StdStats.stddev(results);
    }


    public double confidenceLo() {
        return mean() - (1.96 * stddev()) / Math.sqrt(results.length);
    }


    public double confidenceHi() {
        return mean() + (1.96 * stddev()) / Math.sqrt(results.length);
    }

    private Percolation performSimulation(int n){
        Percolation p = new Percolation(n);
        while ( !p.percolates() ){
            int row = StdRandom.uniform(1, n + 1);
            int col = StdRandom.uniform(1, n + 1);
            p.open(row, col);
        }
        return p;
    }

    public static void main(String[] args){
        if (args.length != 2){
            throw new IllegalArgumentException("n, T are missing");
        }
        int n = Integer.parseInt(args[0]);
        int t =  Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, t);

        System.out.println("mean                    = " + ps.mean());
        System.out.println("stddev                  = " + ps.stddev());

        System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() + "]");


    }


}
