package com.chord;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.trans.Server;


public class ChordLoop {

	//û��ͷ�ڵ�
	private ChordNode firstnode=null;
	//int m=4;������16��λ��
	private int chordMaxLen=16;
	//byte�����¼��Щ����Ѿ���ռ�ˣ�����Ĭ��Ϊ0--δ��ռ��
	private byte[] isFixed=new byte[16];
	//nodeNum��¼��ӳ������Ľ����
	private int nodeNum=0;
	
	//������ӳ�䵽��ϣ����
	public void addNode(Server s) {
		
		//���ݶ˿ںŵõ�hashֵ�����ڹ�ϣ���е�idx
		int initIdx=ChordUtils.computeHash(Integer.toString(s.getPort()));
		//����ͻ
		int finalIdx=ChordUtils.checkCollision(initIdx,isFixed);
		isFixed[finalIdx]=1;
		System.out.println("new server comes! it is "+s.getPort()+"-indx: "+finalIdx);
		//��¼�˸�node��idx��server
		ChordNode newNode=new ChordNode(finalIdx,s.getPort());
		if(nodeNum>=16) {
			System.out.println("���������޷���ӣ�");
			return;
		}else if(nodeNum==0) {
			//��ӵ�һ�����
			newNode.setSuccessor(newNode);
			firstnode=newNode;
		}else {
			//��cnӦ�ò����һ����㣬����Ҫ�޸Ļ��ϵ�β���
			if(newNode.getNodeIdx()<firstnode.getNodeIdx()) {
				getLastNode().setSuccessor(newNode);
				newNode.setSuccessor(firstnode);
				firstnode=newNode;
			}else {
				ChordNode prenode=firstnode;
				ChordNode curnode=firstnode.getSuccessor();
				while(newNode.getNodeIdx()>curnode.getNodeIdx()&&curnode!=firstnode) {
					prenode=curnode;
					curnode=curnode.getSuccessor();
				}
				prenode.setSuccessor(newNode);
				newNode.setSuccessor(curnode);
			}
		}
		nodeNum++;
		printChordLoop();
	}
	
	private ChordNode getLastNode() {
		ChordNode curnode=firstnode;
		while(curnode.getSuccessor()!=firstnode) {
			curnode=curnode.getSuccessor();
		}
		return curnode;
	}
	
	private void printChordLoop() {
		ChordNode curnode=firstnode;
		System.out.println("----------now the chordloop:------------");
		System.out.println(curnode.getServerAdr()+" "+curnode.getNodeIdx());
		curnode=curnode.getSuccessor();
		while(curnode!=firstnode) {
			System.out.println(curnode.getServerAdr()+" "+curnode.getNodeIdx());
			curnode=curnode.getSuccessor();
		}
		System.out.println("----------------------------------------");
	}
	
	//�����ļ�ѶϢ�õ����ļ�Ӧ�ô�ŵ�Server
	public int findServerByFile(String f) {
		int fkey=ChordUtils.computeHash(f);
		System.out.println("get the file's key! "+f+"-key: "+fkey);
		ChordNode curnode=firstnode;
		boolean isFirstTurn=true;
		while(curnode.getNodeIdx()<fkey&&isFirstTurn) {
			curnode=curnode.getSuccessor();
			if(curnode==firstnode)
				isFirstTurn=false;
		}
		System.out.println("please find file at server "+curnode.getServerAdr());
		return curnode.getServerAdr();
	}
	

	public ChordNode getFirstnode() {
		return firstnode;
	}

	public int getChordMaxLen() {
		return chordMaxLen;
	}

	public int getChordLen() {
		return chordMaxLen;
	}
	public void setChordLen(int chordLen) {
		this.chordMaxLen = chordLen;
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}
	
	
}
