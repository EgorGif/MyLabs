package com.butkevich.serv;

public class ClientInfo
{
    private String login;
    volatile private boolean enable = true;

    public String getLogin()
    {
        return login;
    }

    public void setLogin(String Login)
    {
        login = Login;
    }

    public boolean isEnable()
    {
        return enable;
    }

    void setEnable(boolean b)
    {
        enable = b;
    }

    public ClientInfo(boolean status)
    {
        enable =status;
    }

    public ClientInfo(String log,boolean status)
    {
        login = log;
        enable =status;
    }

    @Override
    public String toString()
    {
        String str = enable?"Ready":"In game";
        return new String(login +": "+str+"\n");
    }
}
