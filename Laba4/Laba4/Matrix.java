package Laba4;
import java.util.Random;
import java.util.Date;

public class Matrix {
    static final int MAX_A = 10;

    int N = 0;
    int [][] mas = null;

    public Matrix() {
        N = 0;
        int [][]mas = null;
    }

    public Matrix( int n )
    {
        assert( n > 0 );
        Init( n, MAX_A );
    }

    public Matrix( int n, int max_val )
    {
        assert( n > 0 );
        assert( max_val > 0 );
        Init( n, max_val );
    }

    public int MatrixNumberMax(int k)
    {
        int max=Integer.MIN_VALUE;
        int number=0;

            for(int j=0; j<N; j++)
            {
                if (max<mas[j][k]) {
                    max=mas[j][k];
                    number = j;
                }
            }
         return number;
    }
    public int MatrixNumberMin(int k)
    {
        int min=Integer.MAX_VALUE;
        int number=0;
            for(int j=0; j<N; j++)
            {
                if (min>mas[j][k]) {
                    min=mas[j][k];
                    number = j;
                }
            }
        return number;
    }



    public void MatrixLR(int first, int second)
    {
        int temp=0;
        for(int i = 0; i < N; ++i)
        {
            temp = mas[first][i];
            mas[first][i] = mas[second][i];
            mas[second][i] = temp;
        }

    }

    public void Print() {
        assert (N > 0);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(" " + mas[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    private void Init( int n, int max_val ) {
        assert (n > 0);
        assert (max_val > 0);
        mas = new int[n][n];
        N = n;
        Random rand = new Random((new Date()).getTime());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                mas[i][j] = rand.nextInt(max_val);
            }
        }
    }

}
