package Laba4;
import java.util.*;

public class test
{
    static final int NUM = 10;

    public static void main(String[] args)
    {
        Matrix [] array = new Matrix[NUM];
        int column = 0;
        //Random rand = new Random();
        for ( int i = 0; i < NUM; i++ )
        {
            System.out.println("[" + (i + 1) + "]" );
            array[i] = new Matrix( 3 );
            array[i].Print();
            int k=array[i].MatrixNumberMax(0);
            int n=array[i].MatrixNumberMin(0);
            System.out.println("Number max  "+k+" in column " + column);
            System.out.println("Number min  "+n+" in column " + column);
            System.out.println();
            array[i].MatrixLR(k, n);
            System.out.println ("new matrix : ");
            array[i].Print();
            System.out.println("---------------------------");
        }


    }
}
