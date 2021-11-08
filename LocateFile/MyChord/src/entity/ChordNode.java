package entity;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChordNode {

	//这个node在环里的位置
	private int nodeIdx;
	//映射到这个node里的服务器标识
	private Server server;
	//后继结点，用于在逻辑上形成环
	private ChordNode Successor;


	
	

	//初始化Node
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
