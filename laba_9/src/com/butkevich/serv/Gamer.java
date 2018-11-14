package com.butkevich.serv;


public class Gamer
{
    private ServerThread thread;

    private ShipLocale[] locale = new ShipLocale[10];

    private byte[][] map1= {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}};
    private byte[][] map2= {{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0}};

    private int counter = 20;

    public ServerThread getSThread()
    {
        return thread;
    }

    public int getCounter()
    {
        return counter;
    }

    public void decCounter()
    {
        counter--;
    }

    public byte[][] getMap1() {
        return map1;
    }

    public void setMap1(byte[][] map1) {
        this.map1 = map1;
    }

    public byte[][] getMap2() {
        return map2;
    }

    public void setMap2(byte[][] map1) {
        this.map2 = map1;
    }

    Gamer(ServerThread s)
    {
        thread = s;
    }

    public ShipLocale[] getLocale() {
        return locale;
    }

    public void setLocale(ShipLocale[] locale) {
        this.locale = locale;
    }

    public ShipLocale getShipPos(int pos1,int pos2)
    {
        for(ShipLocale sl : getLocale())
            if(sl.getPos1() == pos1 && sl.getPos2()==pos2)
                return sl;
        return null;
    }
}
