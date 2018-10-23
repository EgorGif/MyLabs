package lab;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;

public class Record implements Serializable {
    String number;
    String name;
    Date dataStart;
    String returnPeriod;
    String author;
    String title;
    String year;
    String publishingHouse;
    String price;

    public Record() {
    }

    public Record(String number, String name, Date dataStart, String returnPeriod, String author,String title,String year,String publishingHouse,String price) {
        this.number = number;
        this.name = name;
        this.dataStart = dataStart;
        this.returnPeriod = returnPeriod;
        this.author = author;
        this.title=title;
        this.year=year;
        this.publishingHouse=publishingHouse;
        this.price=price;
    }
    public Record(String string) throws ParseException {
        StringTokenizer stringTokenizer = new StringTokenizer(string,",");
        if(stringTokenizer.hasMoreTokens()) {

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
            this.number = stringTokenizer.nextToken();
            this.name = stringTokenizer.nextToken();
            this.dataStart = simpleDateFormat.parse(stringTokenizer.nextToken());
            this.returnPeriod = stringTokenizer.nextToken();
            this.author =  stringTokenizer.nextToken();
            this.title = stringTokenizer.nextToken();
            this.year = stringTokenizer.nextToken();
            this.publishingHouse = stringTokenizer.nextToken();
            this.price=stringTokenizer.nextToken();
        }
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDataStart() {
        return dataStart;
    }

    public void setDataStart(Date dataStart) {
        this.dataStart = dataStart;
    }

    public String getReturnPeriod() {
        return returnPeriod;
    }

    public void setReturnPeriod(String returnPeriod) {
        this.returnPeriod = returnPeriod;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Record{" +
                "number='" + number + '\'' +
                ", name='" + name + '\'' +
                ", dataStart=" + dataStart +
                ", returnPeriod=" + returnPeriod +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}