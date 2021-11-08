package com.transfer;

import java.io.IOException;

public class StartRun {
    public static void main(String[] args) throws IOException {
        Server server1 = new Server(8088);
        server1.ServerRun(server1.port);

    }
}
