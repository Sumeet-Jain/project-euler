// How many, not necessarily distinct, values of  nCr, for 1  n  100, are greater than one-million?

import java.lang.Math;
public class p53{

    // Generates a pascals triangle. 
    // An empty array returns 0L.
    // Begins at row 0, column 0;
    // A row column decides the choose
    // eg. row 3 column 2 = 3 Choose 2
    public static long[][] pascalsTriangle(int row){
        long[][] tri = new long[row + 1][row+1];
        for(int i = 0; i < tri.length; i++)
            for(int j = 0; j < tri[0].length; j++)
                tri[i][j] = 0L;
        tri[0][0] = 1L;
        for(int r = 1; r < tri.length; r++)
            for(int c = 0; c <= r ; c++){
                if(c == 0)
                    tri[r][c] = 1L;
                else if(c == r)
                    tri[r][c] = 1L;
                else
                    tri[r][c] = tri[r-1][c] + tri[r-1][c-1];
            }
        return tri;
    }

    public static void main(String[] args){
        long start = System.currentTimeMillis();
        long[][] tri = pascalsTriangle(100);
        int count = 0;
        for(long[] r : tri)
            for(long n : r)
                if(Math.abs(n) > 1000000)
                    count++;
        long end = System.currentTimeMillis();
        System.out.println(tri[100][50]);
        System.out.println(count);
        System.out.println(end-start);
    }
}
            
// Answer : 4075
//
// A few things about this problem:
//      I decided to use Pascals Triangle, since the rows and columns are choose
//      This generates the answer really really really fast, in 1 millisecond
//      The huge issue i had though was checking if they were bigger than 1 million
//      because the numbers became too big for int, and then even long to hold
//      So, after testing different numbers, I found out the overflow made the numbers
//      negative, so a simple abs value check sufficed.
//
//      What I learned:
//      How binary is mulitiplied/represented in a way. `
