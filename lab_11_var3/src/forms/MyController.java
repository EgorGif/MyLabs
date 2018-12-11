package forms;

import javax.swing.*;
import java.awt.*;
import java.text.ParseException;

public class MyController {


        public static void main(String[] args) throws ParseException {
            MainWindow mainWindow = new MainWindow();
            mainWindow.pack();
            mainWindow.setSize(new Dimension(1450, 600));
            mainWindow.setVisible(true);
        }
    }
