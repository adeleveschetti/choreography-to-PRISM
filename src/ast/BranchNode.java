package ast;

import java.util.ArrayList;

public class BranchNode implements Node{

	private String roleA = null;
	private String roleB = null;
	private ArrayList<String> rates = null;
	private ArrayList<String> messages = null;
	private ArrayList<Node> statements = null;

	public BranchNode(String A, String B, ArrayList<String> listRates, ArrayList<String> listMessages, ArrayList<Node> listStatements) {
		roleA = A;
		roleB = B;
		if(listRates!=null) {
			rates = new ArrayList<String>();
			for(String el : listRates) {
				rates.add(el);
			}
		}
		if(listMessages!=null) {
			messages = new ArrayList<String>();
			for(String el : listMessages) {
				messages.add(el);
			}
		}
		if(listStatements!=null) {
			statements = new ArrayList<Node>();
			for(Node el : listStatements) {
				statements.add(el);
			}
		}

	}

	@Override
	public String toPrint() {
		String ret = roleA + " " + roleB ;
		for(String el : rates) {
			ret = ret + " " + el; 
		}
		
		if(statements!=null) {
			for(Node el : statements) {
				ret = ret + el.toPrint();
			}
		}
		ret = ret + "\n";
		return  ret;
	}

	@Override
	public String codeGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

}
