package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class PreambleNode implements Node{
	
	ArrayList<String> variables = null;
	
	public PreambleNode(ArrayList<String> vars) {
		variables = new ArrayList<String>();
		for(String el : vars) {
			variables.add(el);
		}
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override 
	public ArrayList<String> getRate() {
		return null;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String, ArrayList<Integer>> mapStates,
			HashMap<String, ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles, int currIndex, int totIndex) {
		String code = "";
		for(String el : variables) {
			code = code + el.substring(1, el.length()-1) + "\n";
		}
		code = code + "\n";
		return code;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoleA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoleB() {
		// TODO Auto-generated method stub
		return null;
	}

}
