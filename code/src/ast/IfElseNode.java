package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class IfElseNode implements Node{

	private String op;
	private String index;
	private String par = null;
	private int value ;
	private Node stat;
	private Node stat2;

	public IfElseNode(String i, String o, int v, Node s, Node s2) {
		op = o ;
		index = i;
		stat = s;
		stat2 = s2;
		value = v;
	}

	public IfElseNode(String o, String i, String v, Node s, Node s2) {
		op = o ;
		index = i;
		stat = s;
		stat2 = s2;
		par = v;
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String, ArrayList<Integer>> mapStates,
			HashMap<String, ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles,
			int currIndex, int totIndex) {

		if(op.equals("==") && currIndex==value) {
			toRet = stat.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,currIndex,totIndex);
		}
		else if(op.equals("!=") && currIndex!=value) {
			toRet = stat.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,currIndex,totIndex);
		}
		else {
			toRet = stat2.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,currIndex,totIndex);
		}
		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoleA() {

		return null;
	}

	@Override
	public String getRoleB() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getRate() {
		// TODO Auto-generated method stub
		return null;
	}



}
