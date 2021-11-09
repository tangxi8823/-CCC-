package entity;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChordNode {

	//���node�ڻ����λ��
	private int nodeIdx;
	//ӳ�䵽���node��ķ�������ʶ
	private int serverAdr;
	//��̽�㣬�������߼����γɻ�
	private ChordNode Successor;


	
	

	//��ʼ��Node
	public ChordNode(int nodeIdx, int serverAdr) {
		this.nodeIdx = nodeIdx;
		this.serverAdr = serverAdr;
		this.Successor=null;
	}

	public int getNodeIdx() {
		return nodeIdx;
	}
	public void setNodeIdx(int nodeIdx) {
		this.nodeIdx = nodeIdx;
	}

	public int getServerAdr() {
		return serverAdr;
	}

	public void setServerAdr(int serverAdr) {
		this.serverAdr = serverAdr;
	}

	public ChordNode getSuccessor() {
		return Successor;
	}

	public void setSuccessor(ChordNode successor) {
		Successor = successor;
	}

}
