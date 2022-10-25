package ast;

import java.util.ArrayList;
import java.util.Random;

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
	public String codeGenerator(String toRet,int state) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		int size = 5;
		while (salt.length() < size) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String label = salt.toString();
		
		String toFind_A = "module " + roleA + "\n";
		int index_A = toRet.indexOf(toFind_A);
		int index = message.indexOf("&&");
		String toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state + ") -> 1: " + message.substring(0,index) + ";\n";
		toRet = new StringBuilder(toRet).insert(index_A+toFind_A.length(),toInsert_A).toString();

		String toFind_B = "module " + roleB + "\n";
		int index_B = toRet.indexOf(toFind_B);
		String toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state + ") -> 1: " + message.substring(index+2,message.length()) + ";\n";
		toRet = new StringBuilder(toRet).insert(index_B+toFind_B.length(),toInsert_B).toString();

		if(statement!=null) {
			toRet = statement.codeGenerator(toRet,state+1);
		}
		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
