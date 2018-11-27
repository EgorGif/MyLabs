

import java.applet.*;

import java.awt.*;


public class Test extends Applet {



    public void init() {

        int n = Integer.parseInt(getParameter("listSize"));

        int xCoord[] = new int[n];
        int yCoord[] = new int[n];
        String temp;
        for (int i = 0, j = 75; i < n; ++i, j += 35) {
            temp = String.format("param_%d", i);
            double buff = 500 - Double.parseDouble(getParameter(temp)) * 4;
            yCoord[i] = (int) buff;
            xCoord[i] = j;
        }

    }
    // рисование апплета
    public void paint (Graphics g)

    {
        this.setSize(1200, 600);

        int n = Integer.parseInt(getParameter("listSize"));

        int xCoord[] = new int[n];
        int yCoord[] = new int[n];


        String temp;
        for (int i = 0, j = 75; i < n; ++i, j += 35) {
            // temp = "param_";
            temp = String.format("param_%d", i);
            //temp+=i;
            double buff = 500 - Double.parseDouble(getParameter(temp)) * 4;
            yCoord[i] = (int) buff;
            xCoord[i] = j;
        }


        g.drawLine(40, 40, 40, 500);

        g.drawLine(40, 500, 1150, 500);

        g.drawString("days", 1165, 510);

        g.drawString("%", 20, 35);

        for (int i = 0, j = 40; i < 32; ++i, j += 35) {
            g.drawString(Integer.toString(i), j, 520);
        }

        for (int i = 0, j = 500; i < 11; ++i, j -= 40) {
            g.drawString(Integer.toString(i * 10), 10, j);
        }
        g.drawPolyline(xCoord, yCoord, n);


    }

}