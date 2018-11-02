package lab;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Test {
    public static void main(String args[]) throws ParseException {
        ArrayList<Record> magazine = new ArrayList<>();
        createArrayList(magazine);
        Map<Object,Long> map = Buffer.write(magazine);
        SortedMap sortedMap = new TreeMap(map);


        System.out.println("\nNot sorted magazine:");
        printMap(map);
        System.out.println("\nSort by key:(\'name\')");
        printMap(sortedMap);

        System.out.println();
        System.out.println("Вывод объекта,находящегося ниже заданного(заданный = "+ magazine.get(1).getName()+')');
        System.out.println(Buffer.moveLow(magazine.get(1).getName(), map));
        System.out.println("Вывод объекта,находящегося выше заданного(заданный = "+ magazine.get(1).getName()+')');
        System.out.println(Buffer.moveUp(magazine.get(1).getName(), map));

        System.out.println("Remove by index = "+ magazine.get(0).getName()+'\n');
        Buffer.removeByIndex(magazine.get(0).getName(), map);
        for (Map.Entry<Object, Long> entry : map.entrySet()) {
            Record record = Buffer.read(entry.getKey(),map);
            if (record != null) {
                System.out.println("Key: " + entry.getKey());
                System.out.println(Buffer.read(entry.getKey(), map));
            }
        }
    }

    private static void createArrayList(ArrayList<Record> magazine) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Record firstRecord = new Record("321488","Butkevich Egor",simpleDateFormat.parse("22.08.2018 13:37:12"),"40","Y.Kupala","Paulinka","2006","InterMinsk","2$");
        Record secondRecord = new Record("228322","Alexey Big",simpleDateFormat.parse("21.10.2018 12:00:00"),"30","Y.Kolas","Novaya Zyamlya","2000","HrodnaHouse","2$");
        Record thirdRecord = new Record("133723","Sonya Prosonya",simpleDateFormat.parse("23.10.2018 12:00:00"),"32","A.Kormakov","Milya","1999","BrestHouse","20$");
        magazine.add(firstRecord);
        magazine.add(secondRecord);
        magazine.add(thirdRecord);
    }
    private static <K,V> void printMap(Map<K, V> map) {
        System.out.println("______________________________________");
        for(Map.Entry<K,V> entry : map.entrySet()) {
            System.out.println("Key: " + entry.getKey());
            System.out.println(Buffer.read(entry.getKey(), (Map<Object, Long>) map));
        }
    }
}