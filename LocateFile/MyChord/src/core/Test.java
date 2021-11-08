package core;

import entity.ChordLoop;
import entity.ChordNode;
import entity.ChordUtils;
import entity.Server;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Test {

	public static void main(String[] args) {
		
		//初始化哈希环，将服务器映射到哈希环里
		ChordLoop cloop=new ChordLoop(); 
		Server s1 = new Server(8801); 
		Server s2 = new Server(8802);
		Server s3 = new Server(8803); 
		cloop.addNode(s1); 
		cloop.addNode(s2); 
		cloop.addNode(s3);
		//至此，已经将环建好。服务器映射到环里，fingerTable也弄好了。
		 	
	
		
		
		

	}
	

	


}
