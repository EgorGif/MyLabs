package com.butkevich.serv;

public class ClientMessage
{
    private byte msg;
    public byte getID()
    {
        return msg;
    }

    ClientMessage(byte msg1)
    {
        if(Protocol.validID(msg1))
            msg = msg1;
        else
            msg = Protocol.CMD_NONE;
    }

    ClientMessage(String str)
    {
        switch (str)
        {
            case "-list":
                msg = Protocol.CMD_USERS;
                break;
            case "-connect":
                msg = Protocol.CMD_CONNECT;
                break;
            case "-help":
                msg = Protocol.CMD_HELP;
                break;
            case "-disconnect":
                msg = Protocol.CMD_DISCONNECT;
                break;
            case "-accept":
                msg = Protocol.CMD_ACCEPT;
                break;
            case "none":
                msg = Protocol.CMD_NONE;
                break;
            default:
                msg = Protocol.RESULT_CODE_ERROR;
                break;
        }
    }
}
