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
	public String codeGenerator(String toRet,int state, int howMany) {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		int size = 5;
		while (salt.length() < size) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String label = salt.toString();

		String toFind_A = "module " + roleA + "\n\n";

		String toFindState_A = roleA+"_STATE=";
		int indexState_A = toRet.indexOf(toFindState_A);
		int state_A = state;
		if(indexState_A!=-1) {
			state_A = Character.getNumericValue(toRet.charAt(indexState_A+toFindState_A.length()));
			if(state_A!=state) {
				state_A = state;
			}
			else {
				state_A = state_A+1;
			}
		}
		
		int index_A = toRet.indexOf(toFind_A);
		int index = message.indexOf("&&");
		int stateNew = state + howMany;
		String toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> 1: " + message.substring(0,index) + ";\n";
		toRet = new StringBuilder(toRet).insert(index_A+toFind_A.length(),toInsert_A).toString();

		String toFindState_B = roleB+"_STATE=";
		int indexState_B = toRet.indexOf(toFindState_B);
		int state_B = state;
		if(indexState_B!=-1) {
			state_A = Character.getNumericValue(toRet.charAt(indexState_B+toFindState_B.length()));
			if(state_B!=state) {
				state_B = state;
			}
			else {
				state_B = state_B+1;
			}
		}
		
		String toFind_B = "module " + roleB + "\n\n";
		int index_B = toRet.indexOf(toFind_B);
		String toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> 1: " + message.substring(index+2,message.length()) + ";\n";
		toRet = new StringBuilder(toRet).insert(index_B+toFind_B.length(),toInsert_B).toString();

		if(statement!=null) {
			toRet = statement.codeGenerator(toRet,state+1,howMany);
		}
		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
