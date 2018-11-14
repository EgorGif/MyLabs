package com.butkevich.serv;

interface CMD {
    static final byte CMD_CONNECT 	= 1;
    static final byte CMD_DISCONNECT= 2;
    static final byte CMD_USERS 	= 3;
    static final byte CMD_ACCEPT= 4;
    static final byte CMD_NONE	= 5;
    static final byte CMD_INVITE= 6;
    static final byte CMD_INPUT = 7;
    static final byte CMD_INPUT_NICKNAME = 8;
    static final byte CMD_HELP	= 9;
}

interface RESULT {
    static final int RESULT_CODE_OK 	= 0;
    static final int RESULT_CODE_ERROR 	= -1;
}

interface PORT {
    static final int PORT = 8180;
}


public class Protocol implements CMD,RESULT,PORT
{
    private static final byte CMD_MIN = CMD_CONNECT;
    private static final byte CMD_MAX = CMD_HELP;
    public static boolean validID( byte id ) {
        return id >= CMD_MIN && id <= CMD_MAX;
    }
}
