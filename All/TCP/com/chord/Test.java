package com.chord;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

import com.trans.Server;

public class Test {

	public static void main(String[] args) {
		
		//��ʼ����ϣ����
		ChordLoop cloop=new ChordLoop(); 
		Server s1 = new Server(60088); 
		Server s2 = new Server(60090);
		Server s3 = new Server(60092); 
		//��������ӳ�䵽��ϣ����
		cloop.addNode(s1); 
		cloop.addNode(s2); 
		cloop.addNode(s3);
		//���ˣ��Ѿ��������á�������ӳ�䵽���
		//Ѱ��һ������abc.txt���ļ�(��)�����ĸ�������
		cloop.findServerByFile("abc.txt");
		
		 	
	
		
		
		

	}
	

	


}
