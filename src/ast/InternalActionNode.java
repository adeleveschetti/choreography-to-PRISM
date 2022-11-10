package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class InternalActionNode implements Node{

	private String message = null;
	private String role = null;
	private Node statement = null;

	public InternalActionNode(String mex, String n, Node stat) {
		message = mex;
		role = n;
		statement = stat ;
	}

	@Override
	public String toPrint() {
		return message + "@" + role + " " + statement.toPrint();
	}

	@Override 
	public String getRoleA() {
		return role;
	}

	@Override 
	public String getRoleB() {
		return "";
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles) {
		
		int state = 0;

		String roleTmp = role;

		for(String el : roles) {
			if(el.contains(role.substring(0,role.length()-2))) {
				role = el;
			}

		}
		String ret = message;

		if(role.matches(".*\\d.*")) {
			int indexDigit = -1;
			for(int k=0; k<role.length(); k++) {
				if(Character.isDigit(role.charAt(k))) {
					indexDigit = k;
				}
			}
			String toReplace = "_"+role.charAt(indexDigit);
			ret = message.replaceAll("_i",toReplace);
		}

		if(statement instanceof ProtocolIDNode) {
			ret = ret + "&("+role+"_STATE'=0)";
		}
		else {
			String toRet2 = statement.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles);
			if(mapStates.get(role).size()==0) {
				state = 0;
				mapStates.get(role).add(state);
			}
			else {
				state = mapStates.get(role).get(mapStates.get(role).size()-1);
				mapStates.get(role).remove(mapStates.get(role).size()-1);
			}
			ret = ret + "&("+role+"_STATE'="+state+")";
		}

		role = roleTmp;
		return ret ;
	}

	@Override
	public Node getStatement() {
		return statement;
	}

}
