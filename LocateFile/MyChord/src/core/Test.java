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
		
		//��ʼ����ϣ����
		ChordLoop cloop=new ChordLoop(); 
		Server s1 = new Server(8088); 
		Server s2 = new Server(8090);
		Server s3 = new Server(8092); 
		//��������ӳ�䵽��ϣ����
		cloop.addNode(s1); 
		cloop.addNode(s2); 
		cloop.addNode(s3);
		//���ˣ��Ѿ��������á�������ӳ�䵽���
		//Ѱ��һ������abc.txt���ļ�(��)�����ĸ�������
		System.out.println(cloop.findServerByFile("abc.txt"));
		
		 	
	
		
		
		

	}
	

	


}
