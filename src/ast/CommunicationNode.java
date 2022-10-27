package ast;

import java.util.ArrayList;
import java.util.HashMap;
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
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches) {

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

		int state_A, state_B;
		if(mapStates.get(roleA).size()==0) {
			state_A = 0;
			mapStates.get(roleA).add(state_A);
		}
		else {
			state_A = mapStates.get(roleA).get(mapStates.get(roleA).size()-1)+1;
			mapStates.get(roleA).add(state_A+1);
		}

		if(mapStates.get(roleB).size()==0) {
			state_B = 0;
			mapStates.get(roleB).add(state_B);
		}
		else {
			state_B = mapStates.get(roleB).get(mapStates.get(roleB).size()-1)+1;
			mapStates.get(roleB).add(state_B+1);

		}


		int index_A = toRet.indexOf(toFind_A);
		String toFind_B = "module " + roleB + "\n\n";
		int index_B = toRet.indexOf(toFind_B);
		
		int index = message.indexOf("&&");
		int indexBranchA = toRet.indexOf(") -> \n");
		String toInsert_A = "";
		if(indexBranchA==-1 || (indexBranchA>index_A && indexBranchA>index_B)) {
			toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> 1: " + message.substring(0,index) + ";\n";
		}
		else {
			state_A  = Character.getNumericValue(toRet.charAt(indexBranchA-1));
			toRet = toRet.replace("[] ("+ roleA+"_STATE="+state_A+") -> \n","");
			toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> 1: " + message.substring(0,index) + ";\n";
		}
		toRet = new StringBuilder(toRet).insert(index_A+toFind_A.length(),toInsert_A).toString();
		index_B = toRet.indexOf(toFind_B);
		int indexBranchB = toRet.indexOf(") -> \n");
		String toInsert_B = "";
		if(indexBranchB==-1 || (indexBranchB<index_B && indexBranchB>index_A)) {
			toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> 1: " + message.substring(index+2,message.length()) + ";\n";
		}
		else {
			state_B = Character.getNumericValue(toRet.charAt(indexBranchB-1));
			toRet = toRet.replace("[] ("+ roleB+"_STATE="+state_B+") -> \n","");
			toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> 1: " + message.substring(index+2,message.length()) + ";\n";
		}
		toRet = new StringBuilder(toRet).insert(index_B+toFind_B.length(),toInsert_B).toString();

		if(statement!=null) {
			toRet = statement.codeGenerator(toRet,mapStates,mapStatesBranches);
		}

		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
