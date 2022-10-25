package ast;

import java.util.ArrayList;

public class ProtocolNode implements Node{

	private String protocolID = null;
	private Node statement;
	private ArrayList<String> roles = new ArrayList<String> ();
	
	public ProtocolNode(String id, Node stat, ArrayList<String> rol) {
		protocolID = id;
		statement = stat;
		roles = rol ;
	}
	
	@Override
	public String toPrint() {
		String ret =  protocolID + ", " ;
		for(String el : roles) {
			ret = ret + el + ", ";
		}
		return ret + statement.toPrint();
	}

	@Override
	public String codeGenerator(String toRet) {
		String code = ""; 
		for(String el : roles) {
			code = code + "module " + el + "\n\nendmodule \n\n"; 
		}
		code = statement.codeGenerator(code);
		return code ;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
