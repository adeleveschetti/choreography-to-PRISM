package ast;

import java.util.ArrayList;

public class CommunicationNode implements Node {

	private String roleA = null;
	private String roleB = null;
	private String message;
	private Node statement;
	
	public CommunicationNode(String A, String B, String mex, Node stat) {
		roleA = A;
		roleB = B;
		message = mex;
		statement = stat;
	}
	
	@Override
	public String toPrint() {
		return roleA + " " + roleB + " ----> " + message + " \n " + statement.toPrint();
	}

	@Override
	public String codeGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

}
