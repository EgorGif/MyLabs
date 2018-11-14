package com.butkevich.serv;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.TreeMap;

public class ServerThread extends Thread
{
    volatile PrintStream out;
    volatile BufferedReader in;
    private InetAddress addr;

    volatile private boolean invited;
    boolean placement;

    GameThread curGame;
    private ClientInfo info;


    public boolean getStatus()
    {
        return info.isEnable();
    }

    public void setStatus(boolean b)
    {
        info.setEnable(b);
    }

    public String getLogin()
    {
        return info.getLogin();
    }


    public static void printUsers(PrintStream out2, TreeMap<String,ServerThread> cl)
        {
        cl.keySet().forEach((String s)->
        {
            out2.println(cl.get(s).info);
        });
    }

    private void printHelp(PrintStream out2) throws IOException
    {
        out2.println("List of accessable commands");
        out2.println("-list ->print list of users");
        out2.println("-connect (login_of_opponent) -> invite user with current login(if he's enable)");
        out2.println("-help -> print this help list");
        out2.println("-disconnect -> leave from this server\n");
    }

    public ServerThread(Socket inc) throws IOException
    {
        out = new PrintStream(inc.getOutputStream());
        in = new BufferedReader(
                new InputStreamReader(inc.getInputStream())
        );
        addr = inc.getInetAddress();
        info = new ClientInfo(true);
    }

    public void disconnect()
    {
        try
        {
            System.out.println(addr.getHostName() + " disconnected");
            synchronized(Server.socks)
            {
                if(Server.socks.get(info.getLogin())!=null)
                    Server.socks.remove(info.getLogin());
            }
            out.close();
            in.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            this.interrupt();
        }
    }

    public static String getAnswer(ServerThread s,String str) throws IOException
    {
        s.out.println(str);
        s.out.println(Protocol.CMD_INPUT_NICKNAME);
        return s.in.readLine();
    }

    public void session() throws IOException, InterruptedException
    {
        out.println(Protocol.CMD_INPUT);
        String message = in.readLine();
        ClientMessage msg = new ClientMessage(Byte.parseByte(message));
        switch(msg.getID())
        {
            case Protocol.CMD_CONNECT:
                String opponent = getAnswer(this,"Enter nickname of your opponent:");
                if(!opponent.equals(info.getLogin()))
                {
                    out.println("Wait for your opponent");
                    synchronized(Server.socks)
                    {
                        ServerThread cl = Server.socks.get(opponent);
                        if(cl!=null && cl.getStatus() && !invited)
                        {
                            cl.setInvited(true);
                            String s = ServerThread.getAnswer(cl, "Invite: " + info.getLogin() +" want to play with you. Write -accept to start.");
                            if(new ClientMessage(s).getID() == Protocol.CMD_ACCEPT)
                            {
                                curGame = new GameThread(this,cl);
                                cl.curGame = curGame;
                                cl.out.println("Game hosted");
                                curGame.setPriority(MAX_PRIORITY);
                                curGame.start();
                            }
                            else
                            {
                                out.println("Your invite was declared");
                                cl.setInvited(false);
                            }
                        }
                        else
                            out.println("This user is offline(in game) now or this command is unacceptable");
                    }
                }
                break;
            case Protocol.CMD_DISCONNECT:
                disconnect();
                break;
            case Protocol.CMD_HELP:
                printHelp(out);
                break;
            case Protocol.CMD_USERS:
                synchronized(Server.socks)
                {
                    printUsers(out, Server.socks);
                }
                break;
            case Protocol.CMD_ACCEPT:
                if(invited)
                    info.setEnable(false);
                break;
            default:
                out.println("Unknown command");
                break;
        }
    }

    private void create() throws IOException
    {
        out.println("Enter your login:");
        while(true)
        {
            out.println(Protocol.CMD_INPUT_NICKNAME);
            String log = in.readLine();
            log = log.trim();
            synchronized (Server.socks)
            {
                if(Server.socks.get(log)==null)
                {
                    info.setLogin(log);
                    Server.socks.put(info.getLogin(), this);
                    break;
                }
                else
                    out.println("This login is engaged");
            }
        }

        out.println("Welcome to the WarShips, dear "+info.getLogin()+"\n");
        printHelp(out);
    }

    private void gameSession() throws IOException, InterruptedException
    {
        if(placement && curGame!=null)
        {
            byte[][] arr = (this == curGame.first.getSThread())
                    ?curGame.first.getMap1()
                    :curGame.second.getMap1();
            if(!curGame.placement(this, arr))
            {
                curGame.interrupt();
                info.setEnable(true);
                curGame =null;
                placement = false;
                out.println("Your opponent leave the game");
                printHelp(out);
                return;
            }

            if(this == curGame.first.getSThread())
                curGame.first.setMap1(arr);
            else
                curGame.second.setMap1(arr);
        }
    }

    @Override
    public void run()
    {
        try
        {
            create();
            while(true)
            {
                if(!info.isEnable())
                {
                    out.println("##############################\nStart game");
                    gameSession();
                }
                else
                    session();
            }
        }
        catch (IOException | InterruptedException e)
        {
            System.out.println("Disconnect");
        }
        finally
        {
            disconnect();
        }
    }

    public boolean isInvited()
    {
        return invited;
    }

    public void setInvited(boolean invited)
    {
        this.invited = invited;
    }
}
