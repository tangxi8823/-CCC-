package com.chord;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import com.trans.Server;

public class Test {

	public static void main(String[] args) {
		
		//初始化哈希环，
		ChordLoop cloop=new ChordLoop(); 
		Server s1 = new Server(60088); 
		Server s2 = new Server(60090);
		Server s3 = new Server(60092); 
		//将服务器映射到哈希环里
		cloop.addNode(s1); 
		cloop.addNode(s2); 
		cloop.addNode(s3);
		//至此，已经将环建好。服务器映射到环里。
		//寻找一个叫做abc.txt的文件(该)放在哪个服务器
		cloop.findServerByFile("abc.txt");
		
		 	
	
		
		
		

	}
	

	


}
