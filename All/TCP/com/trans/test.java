package com.trans;

import com.chord.ChordLoop;
import com.trans.Server.serverThread;
import com.trans.Server.serverThread2;

import java.io.IOException;

/**
 * @author 唐溪
 * @description: TODO
 * @date 2021/11/12 15:07
 */
public class test {
    public static void main(String[] args) throws IOException {
        //初始化哈希环，
        TCPproperties.cloop=new ChordLoop();
        Server server1 = new Server(60088);
        Server server2 = new Server(60090);
        Server server3 = new Server(60092);
        //将服务器映射到哈希环里
        TCPproperties.cloop.addNode(server1);
        TCPproperties.cloop.addNode(server2);
        TCPproperties.cloop.addNode(server3);
        //至此，已经将环建好。三个服务器进入监听状态
        new Thread(new serverThread(server1.getPort())).start();

        new Thread(new serverThread2(server2.getPort())).start();
        //
        new Thread(new serverThread(server3.getPort())).start();

    }
}
