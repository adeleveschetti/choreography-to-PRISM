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
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches) {
		String ret = message;
		int state = 0;
		
	
		if(statement instanceof ProtocolIDNode) {
			ret = ret + "&("+role+"_STATE'=0)";
		}
		else {
			String toRet2 = statement.codeGenerator(toRet,mapStates,mapStatesBranches);
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
		return ret ;
	}

	@Override
	public Node getStatement() {
		return statement;
	}

}
