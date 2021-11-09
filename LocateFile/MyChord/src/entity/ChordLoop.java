package entity;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChordLoop {

	//没有头节点
	private ChordNode firstnode=null;
	//int m=4;环上有16个位置
	private int chordMaxLen=16;
	//nodeNum记录已映射进来的结点数
	private int nodeNum=0;
	
	//服务器映射到哈希环中
	public void addNode(Server s) {
		
		//根据端口号得到hash值，即在哈希环中的idx
		int idx=ChordUtils.computeHash(Integer.toString(s.getPort()));
		//记录了该node的idx，server
		ChordNode cn=new ChordNode(idx,s.getPort());
		if(nodeNum>=16) {
			System.out.println("环已满，无法添加！");
			return;
		}else if(nodeNum==0) {
			//添加第一个结点
			cn.setSuccessor(cn);
			firstnode=cn;
		}else {
			//若cn应该插入第一个结点，则需要修改环上的尾结点
			if(cn.getNodeIdx()<firstnode.getNodeIdx()) {
				getLastNode().setSuccessor(cn);
				cn.setSuccessor(firstnode);
				firstnode=cn;
			}else {
				ChordNode prenode=firstnode;
				ChordNode curnode=firstnode.getSuccessor();
				while(cn.getNodeIdx()>curnode.getNodeIdx()&&curnode!=firstnode) {
					prenode=cn;
					cn=cn.getSuccessor();
				}
				prenode.setSuccessor(cn);
				cn.setSuccessor(curnode);
			}
		}
		nodeNum++;
	}
	
	private ChordNode getLastNode() {
		ChordNode curnode=firstnode;
		while(curnode.getSuccessor()!=firstnode) {
			curnode=curnode.getSuccessor();
		}
		return curnode;
	}
	
	//根据文件讯息得到该文件应该存放的Server
	public int findServerByFile(String f) {
		int fkey=ChordUtils.computeHash(f);
		ChordNode curnode=firstnode;
		while(curnode.getNodeIdx()<fkey) {
			curnode=curnode.getSuccessor();
		}
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
