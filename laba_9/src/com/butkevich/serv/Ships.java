package com.butkevich.serv;

public enum Ships
{
    little(1,1),medium1(1,2),medium2(2,1),big1(1,3),big2(3,1),large1(1,4),large2(4,1);

    private int rows;
    private int cols;
    private int maxValue;
    public int getCols()
    {
        return cols;
    }
    public int getRows()
    {
        return rows;
    }
    public int getMaxValue()
    {
        return maxValue;
    }
    Ships(int row,int col)
    {
        rows=row;
        cols=col;
        maxValue = 6-row-col;
    }

    public static Ships getShip(int row,int col)
    {
        for(Ships s:Ships.values()) {
            if(s.getCols() == col && s.getRows() == row)
                return s;
        }
        return null;
    }

    // 0 - ���������; 1 - ������� ������ �������; 2 - �������; 3 - ��������(������) �������; 4 - ������; 5 - ������� ������ ������� �������;
    public static void placeAround(byte[][] arr,ShipLocale loc,byte value)
    {
        if(loc.getPos2()-1 >=0)
            for(int i=0;i<loc.getShip().getRows();i++)
                arr[loc.getPos1()+i][loc.getPos2()-1]=value;

        if(loc.getPos2()+loc.getShip().getCols() < arr.length)
            for(int i=0;i<loc.getShip().getRows();i++)
                arr[loc.getPos1()+i][loc.getPos2()+loc.getShip().getCols()]=value;


        if(loc.getPos1()-1 >=0)
            for(int i=0;i<loc.getShip().getCols();i++)
                arr[loc.getPos1()-1][loc.getPos2()+i]=value;

        if(loc.getPos1()+loc.getShip().getRows() <arr.length)
            for(int i=0;i<loc.getShip().getCols();i++)
                arr[loc.getPos1()+loc.getShip().getRows()][loc.getPos2()+i]=value;

        if(loc.getPos1() - 1 >= 0 && loc.getPos2()-1 >= 0)
            arr[loc.getPos1()-1][loc.getPos2()-1]=value;
        if(loc.getPos1()+loc.getShip().getRows() < arr.length && loc.getPos2()+loc.getShip().getCols() < arr.length)
            arr[loc.getPos1()+loc.getShip().getRows()][loc.getPos2() + loc.getShip().getCols()]=value;
        if(loc.getPos1()-1 >= 0 && loc.getPos2()+loc.getShip().getCols() < arr.length)
            arr[loc.getPos1()-1][loc.getPos2() + loc.getShip().getCols()]=value;
        if(loc.getPos2()-1 >= 0 && loc.getPos1()+loc.getShip().getRows() < arr.length)
            arr[loc.getPos1()+loc.getShip().getRows()][loc.getPos2()-1]=value;

    }

    static boolean place(byte[][] arr,byte num,int pos1,int pos2,Ships ship)
    {
        if(pos1+ship.getRows()-1<arr.length && pos2+ship.getCols()-1 < arr.length && pos1>=0 && pos2 >=0)
        {
            try
            {
                for(int i=0;i<ship.getRows();i++)
                    for(int j=0;j<ship.getCols();j++)
                        if(arr[pos1+i][pos2+j]>0)
                            return false;

                placeAround(arr,new ShipLocale(ship,pos1,pos2),(byte)1);
                for(int i=0;i<ship.getRows();i++)
                    for(int j=0;j<ship.getCols();j++)
                        arr[pos1+i][pos2+j]=(byte)(10+num);
            }
            catch(Exception e)
            {
                return false;
            }
            return true;
        }
        return false;
    }

    public static void main(String... args)
    {
        Ships s = Ships.getShip(1, 3);
        if(s==null)
            System.out.println("Nani?");
        System.out.println(s.getCols());
        System.out.println(s.getRows());
        System.out.println(s.getMaxValue());
    }
}
