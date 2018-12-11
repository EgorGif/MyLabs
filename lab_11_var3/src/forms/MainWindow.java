package forms;
import Data_from_lab8.Buffer;
import Data_from_lab8.Record;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MainWindow extends JFrame {
    private JPanel formPanel;
    private JTextField libNumber;
    private JTextField name;
    private JTextField dataStart;
    private JTextField returnPeriod;
    private JPanel listPanel;
    private JPanel dataPanel;
    private JTextField author;
    private JPanel JListPanel;
    private JList listView;
    private JButton submitButton;
    private JButton cleanButton;
    private JButton findUp;
    private JButton findLow;
    private JButton remove;
    private JTextField Index;
    private JTextField status;
    private JToolBar statusBar;
    private JTextField title;
    private JTextField year;
    private JTextField publishingHouse;
    private JTextField price;


    public MainWindow() throws ParseException {
        JFrame self = this;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().add(formPanel);
        DefaultListModel<Record> listModel = new DefaultListModel<>();
        listView.setModel(listModel);
        ArrayList<Record> magazine = new ArrayList<>();
        createArrayList(magazine);
        Map<Object, Long> map = Buffer.write(magazine);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

 /*       for (Record arrayList : magazine) {
            listModel.addElement(arrayList);
            status.setText("Data added");
        }
*/
        for (Map.Entry<Object, Long> entrys : map.entrySet()) {
            Record record = Buffer.read(entrys.getKey(), map);
            if (record != null) {
                listModel.addElement(Buffer.read(entrys.getKey(), map));
            }
        }

        submitButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String libNumberText = libNumber.getText();
                String nameText = name.getText();
                String authorText = author.getText();
                String titleText = title.getText();
                String publishingHouseText = publishingHouse.getText();
                String priceText = price.getText();
                String yearText = year.getText();
                String returnPeriodText = returnPeriod.getText();

                Date dataStartText = null;
                try {
                    dataStartText = simpleDateFormat.parse(dataStart.getText());
                } catch (ParseException e1) {
                    e1.printStackTrace();
                }
                Record newRecord = new Record(libNumberText, nameText, dataStartText, returnPeriodText, authorText, titleText, yearText, publishingHouseText, priceText);
                listModel.addElement(newRecord);


            }

        });
        cleanButton.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listModel.removeAllElements();
            }
        });
        findLow.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(Index.getText());
                Record record = Buffer.moveLow(magazine.get(index).getName(), map);
                listModel.addElement(record);
                status.setText("Find Low");

            }
        });
        findUp.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(Index.getText());
                Record record = Buffer.moveUp(magazine.get(index).getName(), map);
                listModel.addElement(record);
                status.setText("Find top");
            }
        });
        remove.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = Integer.parseInt(Index.getText());
                Buffer.removeByIndex(magazine.get(index).getName(), map);

                for (Map.Entry<Object, Long> entry : map.entrySet()) {
                    Record record = Buffer.read(entry.getKey(), map);
                    if (record != null) {
                        listModel.addElement(Buffer.read(entry.getKey(), map));
                    }
                }
                status.setText("Remove by index");
            }
        });
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");

        fileMenu.add(load);
        fileMenu.add(save);
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        load.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showOpenDialog(self);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                File file = fileChooser.getSelectedFile();

                try {
                    FileInputStream fis = new FileInputStream(file);
                    ObjectInputStream ois = new ObjectInputStream(fis);
                    ArrayList<Record> loadedInvoices = (ArrayList) ois.readObject();
                    ois.close();
                    fis.close();

                    listModel.clear();
                    for (Record record : loadedInvoices) {
                        listModel.addElement(record);
                    }
                } catch (IOException | ClassNotFoundException e1) {
                    e1.printStackTrace();
                }
                status.setText("File load!");
            }
        });

        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                int result = fileChooser.showSaveDialog(self);
                if (result != JFileChooser.APPROVE_OPTION) {
                    return;
                }

                File file = fileChooser.getSelectedFile();


                try {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);

                    ArrayList<Record> records = new ArrayList<>();

                    for (int i = 0; i < listModel.size(); ++i) {
                        records.add(listModel.get(i));
                    }

                    oos.writeObject(records);
                    oos.close();
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                status.setText("File save");
            }
        });
    }

    private static void createArrayList(ArrayList<Record> magazine) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Record firstRecord = new Record("321","Butkevich Egor",simpleDateFormat.parse("22.08.2018 13:37:12"),"40","Y.Kupala","Paulinka","2006","InterMinsk","2$");
        Record secondRecord = new Record("228","Alexey Big",simpleDateFormat.parse("21.10.2018 12:00:00"),"30","Y.Kolas","Novaya Zyamlya","2000","HrodnaHouse","2$");
        Record thirdRecord = new Record("133","Sonya Prosonya",simpleDateFormat.parse("23.10.2018 12:00:00"),"32","A.Kormakov","Milya","1999","BrestHouse","20$");
        Record fourthRecord = new Record("133","Petr Chill",simpleDateFormat.parse("22.11.2018 12:00:00"),"23","A.Murzik","Kot","2000","BrestHouse","25$");
        magazine.add(firstRecord);
        magazine.add(secondRecord);
        magazine.add(thirdRecord);
        magazine.add(fourthRecord);
    }
}