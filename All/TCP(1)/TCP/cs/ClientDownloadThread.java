package cs;

import java.net.Socket;

public class ClientDownloadThread implements Runnable{

	private Socket socket;
	
    public ClientDownloadThread(Socket socket) {
        this.socket = socket;

    }
   
	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	

}
