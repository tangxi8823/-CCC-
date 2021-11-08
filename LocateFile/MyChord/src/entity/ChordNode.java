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
	private Server server;
	//��̽�㣬�������߼����γɻ�
	private ChordNode Successor;


	
	

	//��ʼ��Node
	public ChordNode(int nodeIdx, Server server) {
		this.nodeIdx = nodeIdx;
		this.server = server;
		this.Successor=null;
	}

	public int getNodeIdx() {
		return nodeIdx;
	}
	public void setNodeIdx(int nodeIdx) {
		this.nodeIdx = nodeIdx;
	}
	public Server getServer() {
		return server;
	}
	public void setServer(Server server) {
		this.server = server;
	}

	public ChordNode getSuccessor() {
		return Successor;
	}

	public void setSuccessor(ChordNode successor) {
		Successor = successor;
	}

}
