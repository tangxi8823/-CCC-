package com.ppp.transferNew;/*
package com.transferNew;

import java.io.IOException;
import java.net.Socket;

public class clientDownloadFile implements Runnable{
    private Socket socket;
    private int portReal;
    private String fileName;

    public clientDownloadFile(Socket socket,int portReal, String fileName) {
        this.socket = socket;
        this.portReal = portReal;
        this.fileName = fileName;
    }


    @Override
    public void run() {
            new Thread(new ClientRecvKU(socket,this.portReal)).start();

    }
}
*/
