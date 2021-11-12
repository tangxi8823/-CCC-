package com.chord;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.trans.Server;


public class ChordLoop {

	//没有头节点
	private ChordNode firstnode=null;
	//int m=4;环上有16个位置
	private int chordMaxLen=16;
	//byte数组记录哪些结点已经被占了，数组默认为0--未被占据
	private byte[] isFixed=new byte[16];
	//nodeNum记录已映射进来的结点数
	private int nodeNum=0;
	
	//服务器映射到哈希环中
	public void addNode(Server s) {
		
		//根据端口号得到hash值，即在哈希环中的idx
		int initIdx=ChordUtils.computeHash(Integer.toString(s.getPort()));
		//防冲突
		int finalIdx=ChordUtils.checkCollision(initIdx,isFixed);
		isFixed[finalIdx]=1;
		System.out.println("new server comes! it is "+s.getPort()+"-indx: "+finalIdx);
		//记录了该node的idx，server
		ChordNode newNode=new ChordNode(finalIdx,s.getPort());
		if(nodeNum>=16) {
			System.out.println("环已满，无法添加！");
			return;
		}else if(nodeNum==0) {
			//添加第一个结点
			newNode.setSuccessor(newNode);
			firstnode=newNode;
		}else {
			//若cn应该插入第一个结点，则需要修改环上的尾结点
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
	
	//根据文件讯息得到该文件应该存放的Server
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
