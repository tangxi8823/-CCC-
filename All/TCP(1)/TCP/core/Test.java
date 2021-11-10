package core;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Scanner;

import cs.Client;
import cs.Server;

public class Test {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Server s1=new Server(8088);
		Server s2=new Server(8089);
		Server s3=new Server(8090);
		Client c1=new Client(8088);
		c1.run();
		Client c2=new Client(8090);
		c2.run();
		
	}

}
