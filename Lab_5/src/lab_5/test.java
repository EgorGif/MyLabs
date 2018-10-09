package lab_5;

import java.util.Arrays;

public class test {
    public static void main (String[] args) {
        int a = 0;
        assert(a<0);
        System.out.println(a);
        Parallepiped[] parallepiped = {
                new Parallepiped(1,1,1),
                new Parallepiped(2,5,23),
                new Parallepiped(2,6,20),
                new Parallepiped(5,4,10),
                new Parallepiped(6,2,4),
                new Parallepiped(4,4,2)
        };
        System.out.println("Исходный массив parallepiped:");
        for (Parallepiped p : parallepiped) {
            System.out.println(p.toString());
        }
        System.out.println("Сортировка parallepiped по объёму (volume):");
        Arrays.sort(parallepiped);
        for(Parallepiped p: parallepiped) {
            for(Object object: p) {     // используем Iterator
                System.out.print(object + " ");
            }
            System.out.println();
        }
        System.out.println("Сортировка parallepiped по length (длина):");
        Arrays.sort(parallepiped,new Parallepiped.CompareToLength());
        for(Parallepiped p: parallepiped) {
            System.out.println(p.toString());
        }
        System.out.println("Сортировка parallepiped по width (ширина):");
        Arrays.sort(parallepiped,new Parallepiped.CompareToWidth());
        for(Parallepiped p: parallepiped) {
            System.out.println(p.toString());
        }
        System.out.println("Сортировка parallepiped по height(высота):");
        Arrays.sort(parallepiped,new Parallepiped.CompareToHeight());
        for(Parallepiped p: parallepiped) {
            System.out.println(p.toString());
        }
        System.out.println("Демонстрация подсчёта площади поверхности и объёма:");
        for (Parallepiped p : parallepiped)
        {
            System.out.println(p.toString()+" area = "+p.area()+" volume = "+p.volume());
        }
        String str = "5 4 43";
        System.out.println("Покажем работу конструктора на примере строки: " + str);
        Parallepiped ParStr = new Parallepiped (str);
        System.out.println(ParStr.toString());


    }

}
