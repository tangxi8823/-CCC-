package entity;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChordLoop {

	//û��ͷ�ڵ�
	private Map<Integer,ChordNode> chordLoop=new TreeMap<Integer,ChordNode>();
	//int m=4;������16��λ��
	private int chordLen=16;
	//nodeNum��¼��ӳ������Ľ����
	private int nodeNum=0;
	
	//������ӳ�䵽��ϣ����
	public void addNode(Server s) {
		//���ݶ˿ںŵõ�hashֵ�����ڹ�ϣ���е�idx
		int idx=ChordUtils.computeHash(Integer.toString(s.getPort()));
		//��¼�˸�node��idx��server
		ChordNode cn=new ChordNode(idx,s);
		this.chordLoop.put(idx, cn);
		nodeNum++;
	}
	
	//�����ļ�ѶϢ�õ����ļ�Ӧ�ô�ŵ�Server
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
