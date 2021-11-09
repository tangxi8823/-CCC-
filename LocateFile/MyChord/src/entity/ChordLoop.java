package entity;

import java.io.File;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ChordLoop {

	//û��ͷ�ڵ�
	private ChordNode firstnode=null;
	//int m=4;������16��λ��
	private int chordMaxLen=16;
	//nodeNum��¼��ӳ������Ľ����
	private int nodeNum=0;
	
	//������ӳ�䵽��ϣ����
	public void addNode(Server s) {
		
		//���ݶ˿ںŵõ�hashֵ�����ڹ�ϣ���е�idx
		int idx=ChordUtils.computeHash(Integer.toString(s.getPort()));
		//��¼�˸�node��idx��server
		ChordNode cn=new ChordNode(idx,s.getPort());
		if(nodeNum>=16) {
			System.out.println("���������޷���ӣ�");
			return;
		}else if(nodeNum==0) {
			//��ӵ�һ�����
			cn.setSuccessor(cn);
			firstnode=cn;
		}else {
			//��cnӦ�ò����һ����㣬����Ҫ�޸Ļ��ϵ�β���
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
	
	//�����ļ�ѶϢ�õ����ļ�Ӧ�ô�ŵ�Server
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
