package entity;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChordLoop {

	//没有头节点
	private Map<Integer,ChordNode> chordLoop=new TreeMap<Integer,ChordNode>();
	//int m=4;环上有16个位置
	private int chordLen=16;
	//nodeNum记录已映射进来的结点数
	private int nodeNum=0;
	
	//服务器映射到哈希环中
	public void addNode(Server s) {
		//根据端口号得到hash值，即在哈希环中的idx
		int idx=ChordUtils.computeHash(Integer.toString(s.getPort()));
		//记录了该node的idx，server
		ChordNode cn=new ChordNode(idx,s);
		this.chordLoop.put(idx, cn);
		nodeNum++;
	}
	
	//根据文件讯息得到该文件应该存放的Server
	public Server findServerByFile(String f) {
		int fkey=ChordUtils.computeHash(f);
		ChordNode node=null;
		for (Integer i : chordLoop.keySet()) {
			node=chordLoop.get(i);
			if(fkey>=i)
				break;
		}
		return node.getServer();
	}
	
	public Server findFile(String fileName) {
		int fkey=ChordUtils.computeHash(f);
		ChordNode node=null;
		for (Integer i : chordLoop.keySet()) {
			node=chordLoop.get(i);
			if(fkey>=i)
				break;
		}
		return node.getServer();
	}
	
	public Map<Integer, ChordNode> getChordLoop() {
		return chordLoop;
	}
	public void setChordLoop(Map<Integer, ChordNode> chordLoop) {
		chordLoop = chordLoop;
	}
	public int getChordLen() {
		return chordLen;
	}
	public void setChordLen(int chordLen) {
		this.chordLen = chordLen;
	}
	public int getNodeNum() {
		return nodeNum;
	}
	public void setNodeNum(int nodeNum) {
		this.nodeNum = nodeNum;
	}
	
	
}
