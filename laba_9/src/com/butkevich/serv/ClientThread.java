package com.butkevich.serv;


import java.io.*;
import java.net.*;
import java.util.Scanner;


public class ClientThread
{
    public static void main(String[] args) throws InterruptedException
    {
        Socket s;
        try
        {
            s =(args.length==1)?new Socket(args[0], Protocol.PORT ):new Socket(InetAddress.getLocalHost(), Protocol.PORT );
            Scanner in = new Scanner(System.in);
            PrintStream ps = new PrintStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(
                    new InputStreamReader( s.getInputStream() ));
            String message="";
            String input = Byte.toString(Protocol.CMD_INPUT);
            String inputLog = Byte.toString(Protocol.CMD_INPUT_NICKNAME);
            while(true)
            {
                String serverM = br.readLine();
                if(serverM.equals(input))
                {
                    ps.println(new ClientMessage(message = in.nextLine()).getID());
                }
                else if(serverM.equals(inputLog))
                {
                    ps.println(message = in.nextLine());
                }
                else
                    System.out.println(serverM);
                if(message.equals("-disconnect"))
                    break;
            }
            in.close();
            ps.close();
            br.close();
            s.close();
        }
        catch (UnknownHostException e)
        {
            System.out.println("Address is unaccessable");
            e.printStackTrace();
            Thread.sleep(4000);
        }
        catch (IOException e)
        {
            System.out.println("Error at I\\O thread");
            e.printStackTrace();
            Thread.sleep(4000);
        }
    }
}

