package cs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int port;

    public Server(int port) {
        this.port = port;
    }
    
    public void ServerRun() throws IOException {
        //创建服务器套接字
        ServerSocket ss = new ServerSocket(this.port);
        //为每一个客户端开启一个线程
        while (true) {
            //监听客户端连接
            Socket s = ss.accept();
            new Thread(new ServerUploadThread(s)).start();
        }
    }
}
