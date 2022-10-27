package ast;

import java.util.ArrayList;
import java.util.HashMap;

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
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches) {
		
		mapStates = new HashMap<String,ArrayList<Integer>>();
		mapStatesBranches = new HashMap<String,ArrayList<Integer>>();

		String code = ""; 
		for(String el : roles) {
			mapStates.put(el,new ArrayList<Integer>());
			mapStatesBranches.put(el,new ArrayList<Integer>());

			code = code + "module " + el + "\n\nendmodule \n\n"; 
		}
		code = statement.codeGenerator(code,mapStates,mapStatesBranches);
		return code ;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
