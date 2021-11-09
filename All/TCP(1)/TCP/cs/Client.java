package cs;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	private Socket s;
	int port;

	public Client(int p) throws UnknownHostException, IOException {
		super();
		this.port=p;
		this.s = new Socket("fe80::cce4:af6e:b813:43f7%14",this.port);
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
        String str = sc.next();
        String f = sc.next();
        File f;
        if(str=="upload") {
        	//用f创建一个新文件
        	new Thread(new ClientUploadThread(s)).start();
        }else if(str=="download") {
        	new Thread(new ClientDownloadThread(s)).start();
        }
	}
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
	
}
