package com.butkevich.serv;

public class ShipLocale {
    private Ships ship;
    private int pos1;
    private int pos2;
    private boolean isAlive;
    public Ships getShip() {
        return ship;
    }
    public void setShip(Ships ship) {
        this.ship = ship;
    }
    public int getPos1() {
        return pos1;
    }
    public void setPos1(int pos1) {
        this.pos1 = pos1;
    }
    public int getPos2() {
        return pos2;
    }
    public void setPos2(int pos2) {
        this.pos2 = pos2;
    }

    ShipLocale(Ships ship,int pos1,int pos2)
    {
        this.ship = ship;
        this.pos1= pos1;
        this.pos2 = pos2;
        isAlive = true;
    }

    public void checkAlive(byte[][] arr)
    {
        isAlive = false;
        for(int i=0;i<ship.getRows();i++)
            for(int j=0;j<ship.getCols();j++)
                if(arr[pos1+i][pos2+j]<20)
                    isAlive = true;
    }
    public boolean getAlive() {
        return isAlive;
    }

    @Override
    public String toString()
    {
        String alive = (isAlive)?"Alive":"Dead";
        return new String(pos1 + " "+ pos2 + " " + ship.name() + " Status: " + alive);
    }
}
