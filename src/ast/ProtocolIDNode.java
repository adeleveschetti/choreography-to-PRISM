package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class ProtocolIDNode implements Node{

	String id = null;
	
	public ProtocolIDNode(String name) {
		id = name;
	}
	
	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return " " + id + " " ;
	}
	
	@Override 
	public String getRoleA() {
		return "";
	}
	
	@Override 
	public String getRoleB() {
		return "";
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles) {
		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
