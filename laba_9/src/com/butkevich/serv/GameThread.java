package com.butkevich.serv;

import java.io.IOException;
import java.util.StringTokenizer;
public class GameThread extends Thread
{
    Gamer first;
    Gamer second;

    public void drawShip(ServerThread s, byte[][] arr,int i)
    {
        for(int j=0;j<10;j++)
        {
            if(arr[i][j]>9 && arr[i][j] < 20)
                s.out.print("O");
            else if(arr[i][j] >= 20)
                s.out.print("X");
            else if(arr[i][j]==4)
                s.out.print("M");
            else if(arr[i][j]==5)
                s.out.print("#");
            else
                s.out.print(" ");
        }
    }

    public void drawMap(ServerThread s,byte[][] arr) throws IOException
    {
        s.out.println("  0123456789 ");
        s.out.println(" ------------");
        for(int i=0;i<10;i++)
        {
            s.out.print(Integer.toString(i)+"|");
            drawShip(s,arr,i);
            s.out.println("|");
        }
        s.out.println(" ------------");
    }

    public void drawGameMap(ServerThread s,byte[][] arr,byte[][] arr1) throws IOException
    {
        s.out.println("  0123456789                 0123456789 ");
        s.out.println(" ------------               ------------");
        for(int i=0;i<10;i++)
        {
            s.out.print(Integer.toString(i)+"|");
            drawShip(s,arr,i);
            s.out.print("|");
            s.out.print("              ");
            s.out.print(Integer.toString(i)+"|");
            drawShip(s,arr1,i);
            s.out.println("|");
        }
        s.out.println(" ------------               ------------");
    }



    public boolean  placement(ServerThread s,byte[][] arr) throws IOException, InterruptedException
    {
        byte counter =0;
        byte[][] a = new byte[4][2];//������� ���-�� ������� ���������

        ShipLocale[] shiploc = new ShipLocale[10];

        while(counter<10)
        {
            synchronized(Server.socks)
            {
                if(Server.socks.get(first.getSThread().getLogin())==null || Server.socks.get(second.getSThread().getLogin())==null)
                    return false;
            }
            StringBuffer str = new StringBuffer(ServerThread.getAnswer(s,"Enter the ship's coordinate, height and width"));
            StringTokenizer s1 = new StringTokenizer(str.toString()," ");
            if(s1.countTokens() > 3)
            {
                try
                {
                    int pos1 = Integer.parseInt(s1.nextToken());
                    int pos2 = Integer.parseInt(s1.nextToken());
                    int row = Integer.parseInt(s1.nextToken());
                    int col = Integer.parseInt(s1.nextToken());
                    Ships ship =Ships.getShip(row,col);
                    if(ship!=null)
                    {
                        if(a[(ship.getCols()*ship.getRows())-1][1]<ship.getMaxValue())
                        {
                            if(Ships.place(arr,(byte)counter,pos1, pos2, ship))
                            {
                                a[ship.getCols()*ship.getRows()-1][1]++;
                                shiploc[counter++] = new ShipLocale(ship,pos1,pos2);
                                drawMap(s,arr);
                            }
                            else
                                s.out.println("It is impossible to place such a ship");
                        }
                        else
                            s.out.println("Ships of this type are already over");

                    }
                    else
                        s.out.println("It is impossible to build such a ship");
                }
                catch(Exception e)
                {
                    s.out.println("It is impossible to build such a ship");
                }
            }
            else
                s.out.println("Incorrect input coordinates(Example: 3 7 2 1, where 3 7 - coordinates, and 2 1 - type of ship)");
        }
        if(s == first.getSThread())
            first.setLocale(shiploc);
        else
            second.setLocale(shiploc);
        s.out.println("Waiting your opponent...");
        s.placement = false;
        Thread.sleep(2000);
        return true;
    }

    GameThread(ServerThread user1, ServerThread user2)
    {
        first = new Gamer(user1);
        second = new Gamer(user2);
        first.getSThread().setStatus(false);
        second.getSThread().setStatus(false);
        first.getSThread().placement=true;
        second.getSThread().placement=true;
    }



    private boolean fire(byte[][] arr,int pos1,int pos2) {
        if(pos1>arr.length || pos2 > arr.length || arr[pos1][pos2] >20 || arr[pos1][pos2] ==5)
            return false;
        if(arr[pos1][pos2] ==0 || arr[pos1][pos2] ==1 )
            arr[pos1][pos2] = 4;
        if(arr[pos1][pos2] >=10)
        {
            arr[pos1][pos2] += 10;
        }
        return true;

    }

    private boolean move(Gamer s,byte[][] arr1,byte[][] arr) throws IOException
    {
        while(true)
        {
            String str = ServerThread.getAnswer(s.getSThread(),"Enter the ship's coordinate");
            try
            {
                String[] s1 = str.split(" ");
                if(s1.length > 1)
                {
                    int pos1 = Integer.parseInt(s1[0]);
                    int pos2 = Integer.parseInt(s1[1]);
                    if(fire(arr,pos1,pos2))
                    {
                        arr1[pos1][pos2] = arr[pos1][pos2];
                        if(arr[pos1][pos2]>=20)
                        {
                            s.getLocale()[arr[pos1][pos2]-20].checkAlive(arr);
                            if(!s.getLocale()[arr[pos1][pos2]-20].getAlive())
                            {
                                s.getSThread().out.println("Kill!");
                                Ships.placeAround(arr,s.getLocale()[arr[pos1][pos2]-20] ,(byte) 5);
                                Ships.placeAround(arr1,s.getLocale()[arr[pos1][pos2]-20] , (byte)5);
                            }
                            else
                                s.getSThread().out.println("Hit!");
                            return true;
                        }
                        return false;
                    }
                }
                else
                    s.getSThread().out.println("Invalid coordinate setting");
            }
            catch(Exception e)
            {
            }
        }
    }

    public void run()
    {
        try
        {
            first.getSThread().out.println("##############################\nStart game");
            second.getSThread().out.println("##############################\nStart game");
            while(first.getSThread().placement || second.getSThread().placement);
            first.getSThread().out.println("Start game:");
            second.getSThread().out.println("Start game:");
            drawGameMap(first.getSThread(),first.getMap1(),first.getMap2());
            drawGameMap(second.getSThread(),second.getMap1(),second.getMap2());
            while(first.getCounter()!=0 && second.getCounter()!=0)
            {
                second.getSThread().out.println("Your opponent move...");
                while(move(first,first.getMap2(),second.getMap1()))
                {
                    second.decCounter();
                    drawGameMap(first.getSThread(),first.getMap1(),first.getMap2());
                    drawGameMap(second.getSThread(),second.getMap1(),second.getMap2());
                }
                drawGameMap(first.getSThread(),first.getMap1(),first.getMap2());
                drawGameMap(second.getSThread(),second.getMap1(),second.getMap2());
                first.getSThread().out.println("Your opponent move...");
                while(move(second,second.getMap2(),first.getMap1()))
                {
                    first.decCounter();
                    drawGameMap(first.getSThread(),first.getMap1(),first.getMap2());
                    drawGameMap(second.getSThread(),second.getMap1(),second.getMap2());
                }
                drawGameMap(first.getSThread(),first.getMap1(),first.getMap2());
                drawGameMap(second.getSThread(),second.getMap1(),second.getMap2());
            }
            first.getSThread().out.println("Thank you for game!");
            second.getSThread().out.println("Thank you for game!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
            if(first.getSThread() == null)
                second.getSThread().out.println("Your opponent leave game");
            if(second.getSThread() == null)
                first.getSThread().out.println("Your opponent leave game");
        }
        finally
        {
            first.getSThread().setStatus(true);
            second.getSThread().setStatus(true);
            first.getSThread().curGame =null;
            second.getSThread().curGame =null;
        }
    }
}
