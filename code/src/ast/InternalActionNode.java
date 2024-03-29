package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class InternalActionNode implements Node{
	static final int lenIndex = 3;

	private String message = null;
	private String role = null;
	private Node statement = null;
	private String rate = null;

	public InternalActionNode(String mex, String n, Node stat) {
		message = mex;
		role = n;
		statement = stat ;
	}

	public InternalActionNode(String mex, String n, Node stat, String r) {
		message = mex;
		role = n;
		statement = stat ;
		rate = r;
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
	public ArrayList<String> getRate() {
		if(rate!=null) {
			ArrayList<String> rates = new ArrayList<String>();
			rates.add(rate);
			return rates;
		}
		else {
			return null;
		}
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles, int currIndex, int totIndex) {
		int state = 0;

		String roleTmp = role;
		boolean flagName = false;
		if(role.charAt(role.length()-lenIndex) == '+'){
			if(currIndex+2>totIndex) {
				role = role.replace("[i+1]",Integer.toString(1));
			}
			else {
				role = role.replace("[i+1]",Integer.toString(currIndex+2));
			}
			flagName = true;
		}
		if(!flagName) {
			for(String el : roles) {
				if(el.contains(role.substring(0,role.length()-lenIndex))) {
					role = el;
				}

			}
		}

		String ret = message;
		if(rate != null) {
			ret = rate + " : " + ret;
		}
		if(role.matches(".*\\d.*") || ret.contains("[i]") || ret.contains("[i+1]")) {
			if(ret.contains("[i+1]")) {
				ret = ret.replace("[i]",Integer.toString(currIndex+1));
				if(currIndex+2>totIndex) {
					ret = ret.replace("[i+1]",Integer.toString(1));
				}
				else {
					ret = ret.replace("[i+1]",Integer.toString(currIndex+2));
				}
			}
			else {
				int indexDigit = -1;
				for(int k=0; k<role.length(); k++) {
					if(Character.isDigit(role.charAt(k))) {
						indexDigit = k;
					}
				}
				String toReplace = ""+role.charAt(indexDigit);
				ret = ret.replaceAll("\\[i\\]",toReplace);
			}
		}
		if(statement instanceof ProtocolIDNode) {
			ret = ret + "&("+role+"_STATE'=0)";
		}
		else {
			String toRet2 = statement.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles, currIndex,  totIndex);
			if(mapStates.get(role)==null || mapStates.get(role).size()==0) {
				state = 0;
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				tmp.add(state);
				mapStates.put(role,tmp);
			}
			else {
				state = mapStates.get(role).get(mapStates.get(role).size()-1);
				mapStates.get(role).remove(mapStates.get(role).size()-1);
			}
			/*"&("+role+"_STATE'="+state+")";*/
		}

		role = roleTmp;
		System.out.println("==== " + ret);
		return ret ;
	}

	@Override
	public Node getStatement() {
		return statement;
	}

}
