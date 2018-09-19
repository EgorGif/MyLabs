import java.util.Scanner;
import java.util.Random;
import java.util.Date;
public class lab3_var3 {


        public static void main(String[] args) {

                Scanner in = new Scanner(System.in);
                System.out.print ("Введите n: ");
                int n = in.nextInt();
                in.close();
                if (n<=1)
                {
                    System.err.println ("Размер матрицы должен быть больше 1!");
                    System.exit(1);
                }
                Random rnd = new Random ((new Date()).getTime() );
                int [] [] mas = new int [n] [n];
                System.out.println ("Матрица: ");
                for (int i = 0;i < n; ++i) {
                    for (int j = 0; j < n; ++j) {
                        mas[i] [j] = rnd.nextInt() % (n+1);
                        System.out.print(mas[i][j] + " ");
                    }
                    System.out.println();
                }
                int count = 0;
                int max = -n-1;
                int count_max = 0;
                for (int i = 0;i < n;++i)
                    for (int j = 0;j < n;++j)
                    {
                        if (mas[i] [j] > max)
                            ++count;
                        else
                            count = 1;
                        if (count > count_max)
                            count_max = count;
                        max = mas[i][j];
                    }
                    if (count_max == 1)
                        System.out.println("Нет подряд возрастающих элементов.");
                    else
                    {
                        System.out.println("Наибольшее число подряд возрастающих\n элементов матрицы count_max = " + count_max);
                    }
                    System.exit(0);
    }
}
