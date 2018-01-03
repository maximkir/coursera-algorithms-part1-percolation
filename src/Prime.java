import java.util.ArrayList;
import java.util.Arrays;

public class Prime {


    public int[] primeSum(int k) {
        if (k % 2 != 0) throw new IllegalArgumentException("non even input");
        int a = 1, b = 1;

        while ( a + b != k  || !isPrime(a) || !isPrime(b) ){
            a = nextPrime(a + 1);
            b = k - a;
        }

        return new int[]{a, b};
    }


    public int nextPrime(int k) {
        while ( !isPrime(k) ) k+= 1;
        return k;
    }


    public boolean isPrime(int i) {
        if ( i == 1 ) return false;
        if ( i == 2  || i == 3 ) return true;

        boolean isDiveded = false;
        int end = (int)Math.round(Math.sqrt(i));
        for (int k = 2; k <= end && !isDiveded; k++ ){
            isDiveded = isDiveded || i % k == 0;
        }
        return !isDiveded;
    }

    public static boolean isPower(int a) {
        String binary = Integer.toBinaryString(a);
        int binaryLength = binary.length();
        if (binaryLength < 3) return false;
        int onesCounter = 0;

        for (int i = 0; i < binaryLength && onesCounter < 2; i++ ){
            if (binary.charAt(i) == '1' ) onesCounter += 1;
        }

        return onesCounter == 1;
    }

    public static int coverPoints(ArrayList<Integer> X, ArrayList<Integer> Y) {
        int points = X.get(0);
        int steps = 0;
        for (int i = 1; i <= points -1; i++ ){
            steps += findShortestPath(X.get(i), Y.get(i), X.get(i+1), Y.get(i+1));
        }
        return steps;
    }

    public static int findShortestPath(int ax, int ay, int bx, int by) {
        // same row
        if (ax == bx) return Math.abs(ay - by);
        //same col
        if (ay == by) return Math.abs(ax - bx);

        int stepsToDiag = Math.abs(Math.abs(ax - bx) - Math.abs(ay - by));
        return stepsToDiag + Math.abs(ax - bx);
    }


    public static void main(String[] args) {
//        Prime p = new Prime();
//        int[] result = p.primeSum(160);
//        for (int i = 0; i < result.length; i++)
//            System.out.println(result[i]);

//        System.out.println(isPower(1));


        ArrayList<Integer> a1 = new ArrayList<>();
        a1.add(2);
        a1.add(-7);
        a1.add(-13);

        ArrayList<Integer> a2 = new ArrayList<>();
        a2.add(2);
        a2.add(1);
        a2.add(-5);

        System.out.println(coverPoints(a1, a2));
    }
}
