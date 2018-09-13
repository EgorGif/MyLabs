import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class laba2_var3 {

    public static void main(String[] args) {

        File file = new File("hi.txt");
        try
        {
            Scanner in = new Scanner(file);
            while(in.hasNextLine())
            {
                String str = in.nextLine();
                char[] mas = str.toCharArray();
                int count = 0;
                int beg;
                boolean flag = true;
                for (int i = 0 ;i < str.length();++i)
                {
                    beg = i;
                    if (i==0) {
                        while ( i!=str.length() && Character.isDigit(mas[i])) {
                            ++count;
                            ++i;
                        }
                    }
                    while ( i!=str.length() && Character.isDigit(mas[i]) )
                    {
                        if (mas[i-1]=='.')
                            flag = false;
                        ++count;
                        ++i;
                    }
                    if (count>1) {
                        if (flag)
                            System.out.print(Integer.parseInt(str.substring(beg, beg + count)));
                        else
                            System.out.print(str.substring(beg, beg + count));
                    }
                    if (count == 1)
                        System.out.print(mas[beg]);
                    count = 0;
                    flag = true;
                    if (i!=str.length())
                        System.out.print(mas[i]);
                }
                System.out.println();
            }
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}