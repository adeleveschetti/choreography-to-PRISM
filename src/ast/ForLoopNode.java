package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class ForLoopNode implements Node{
	static final int lenIndex = 3;

	private String message ;
	private String indexIteration; // index of the iteration
	private String upperBound; // upperbound
	private String op ; // operation (!= ; = ; < ; > ; <= ; >=)
	private String role ;
	/*
	 * foreach( roleB != roleC ) message @ roleA
	 */


	public ForLoopNode(String mex, String index, String bound, String operation, String r){
		message = mex;
		indexIteration = index;
		upperBound = bound;
		op = operation;
		role = r;
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String, ArrayList<Integer>> mapStates,
			HashMap<String, ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles, int currIndex, int totIndex) { 
		// TODO : fare altri operatori

		String ret = "";
		switch(op) {
		case "=":
			;
		case "!=":
			for(int i=1; i<=totIndex; i++) {
				String messageTmp = message;
				if(i!=(currIndex+1)) {
					if(messageTmp.contains("["+indexIteration+"]")) {
						String toReplaceA = ""+Integer.toString(i);
						messageTmp = messageTmp.replaceAll("\\["+indexIteration+"\\]",toReplaceA);
					}
					ret = ret+"("+messageTmp+")";
					if(currIndex+1==totIndex) {
						if(i!=currIndex) {
							ret = ret + "&";
						}
					}
					else {
						if(i!=totIndex ) {
							ret = ret + "&";
						}
					}
				}	
			}
			if(ret.contains("["+upperBound+"]")) {
				String toReplaceA = ""+Integer.toString(currIndex+1);
				ret = ret.replaceAll("\\["+upperBound+"\\]",toReplaceA);
			}
		case "<":
			;
		case "<=":
			;
		case ">":
			;
		case ">=":
			;
		}

		return ret;
	}


	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoleA() {
		return role;
	}

	@Override
	public String getRoleB() {
		// TODO Auto-generated method stub
		return null;
	}

}
