import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * <b>Percolation</b>
 *
 *
 *
 * @author Maxim.Kirilov
 */
public class Percolation {

    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final boolean[] openSites;
    private final int virtualStart, virtualEnd;
    private final int n;

    private int numberOfOpenSites = 0;

    public Percolation(int n){
        if ( n <= 0 ) throw new IllegalArgumentException("n <= 0 ");
        this.n = n;
        int sites = n * n;
        weightedQuickUnionUF = new WeightedQuickUnionUF(sites + 2);
        virtualStart = 0;
        virtualEnd = sites + 1;
        openSites = new boolean[sites + 2];
        openSites[virtualStart] = openSites[virtualEnd] = true;
        for (int i = 1; i < n; i++){
            openSites[i] = false;
        }
    }


    public void open(int row, int col){
        checkIndices(row, col);
        if ( ! isOpen(row, col) ){
            int siteId = computeSiteId(row, col);
            if (row - 1 == 0) { //Up
                weightedQuickUnionUF.union(siteId, virtualStart);
            } else if ( isOpen(row - 1, col)){
                weightedQuickUnionUF.union(siteId, computeSiteId(row - 1, col));
            }

            if (col + 1 <= n && isOpen(row, col + 1)) { // Right
                weightedQuickUnionUF.union(siteId, computeSiteId(row, col + 1));
            }

            if (row + 1 > n ) { // Down
                weightedQuickUnionUF.union(siteId, virtualEnd);
            } else if (isOpen(row + 1, col)){
                weightedQuickUnionUF.union(siteId, computeSiteId(row + 1, col));
            }
            if (col - 1 > 0 && isOpen(row, col - 1)) { // Left
                weightedQuickUnionUF.union(siteId, computeSiteId(row, col - 1));
            }

            openSites[siteId] = true;
            numberOfOpenSites++;
        }
    }

    public boolean isOpen(int row, int col){
        checkIndices(row, col);
        return openSites[computeSiteId(row, col)];
    }


    public boolean isFull(int row, int col){
        checkIndices(row, col);
        return isOpen(row, col) && weightedQuickUnionUF.connected(virtualStart, computeSiteId(row, col));
    }


    public int numberOfOpenSites(){
        return numberOfOpenSites;
    }

    public boolean percolates(){
        return weightedQuickUnionUF.connected(virtualStart, virtualEnd);
    }

    private int computeSiteId(int row, int col){
        return (row - 1) * n  + col;
    }

    private void checkIndices(int row, int col){
        if ( row <= 0 || row > n) {
            throw new IllegalArgumentException("Row is out of bounds");
        }

        if (col <= 0 || col > n) {
            throw new IllegalArgumentException("Col is out of bounds");
        }
    }



    public static void main(String[] args){
        Percolation p = new Percolation(3);
        p.open(1,2);
        System.out.println(p.percolates());
        p.open(2, 2);
        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());
        p.open(3, 2);
        System.out.println(p.percolates());
        System.out.println(p.numberOfOpenSites());
    }


}
