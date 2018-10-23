package lab;

import java.io.*;
import java.text.ParseException;
import java.util.Scanner;

public class WorkWithFile {
    private String fileNameDat;

    public WorkWithFile(String fileNameDat) {
        this.fileNameDat = fileNameDat;
    }

    public String getFileNameDat() {
        return fileNameDat;
    }

    public void setFileNameDat(String fileNameDat) {
        this.fileNameDat = fileNameDat;
    }
    public void deleteFile() {
        File file = new File(fileNameDat);
        file.delete();
    }
    public void appendToFile(Serializable object) throws FileNotFoundException, IOException {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(this.fileNameDat,"rw")) {
            Buffer.writeObject(randomAccessFile,object);
        }
    }
    public void printFile() throws IOException,ClassNotFoundException {
        try(RandomAccessFile randomAccessFile = new RandomAccessFile(this.fileNameDat,"r")) {
            long position = 0;
            int i = 1;
            while((position = randomAccessFile.getFilePointer()) < randomAccessFile.length()) {
                // System.out.println(position);
                Record record = (Record) Buffer.readObject(randomAccessFile,position);
                System.out.println(i + ")" + record.toString());
                i++;
            }
            if(position == 0){
                System.out.println("The file is empty! ");
            }
        }
        System.out.println("***************************************");
    }

    public void appendToFileFromTxtFile(String TxtFileName) throws IOException,FileNotFoundException, ParseException {
        Scanner in = new Scanner(new File(TxtFileName));
        while(in.hasNextLine()) {
            String string = in.nextLine();
            appendToFile(new Record(string));
        }
    }
}