package com.butkevich.serv;

import java.net.*;
import java.util.TreeMap;
import java.io.*;


public class Server
{
    static TreeMap<String,ServerThread> socks = new TreeMap<>();

    public static void main(String[] args) throws IOException
    {
        try(ServerSocket s = new ServerSocket(Protocol.PORT))
        {
            System.out.println("Server initialized");
            while(true)
            {
                Socket incoming = s.accept();
                System.out.println(s.getInetAddress() + " connected");
                Runnable client = new ServerThread(incoming);
                Thread t = new Thread(client);
                t.start();
            }
        }
    }
}
