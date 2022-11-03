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
	public String getRoleA() {
		return roleA;
	}

	@Override 
	public String getRoleB() {
		return roleB;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles) {
		String roleAtmp = roleA;
		String roleBtmp = roleB;

		for(String el : roles) {
			if(el.contains(roleA.substring(0,roleA.length()-2))) {
				roleA = el;
			}
			if(el.contains(roleB.substring(0,roleB.length()-2))) {
				roleB = el;
			}
		}
		

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
			mapStates.get(roleA).add(state_A);
		}

		if(mapStates.get(roleB).size()==0) {
			state_B = 0;
			mapStates.get(roleB).add(state_B);
		}
		else {
			state_B = mapStates.get(roleB).get(mapStates.get(roleB).size()-1)+1;
			mapStates.get(roleB).add(state_B);

		}

		int index_A = toRet.indexOf(toFind_A);
		int indexEnd_A = toRet.indexOf("endmodule",index_A);
		String toFind_B = "module " + roleB + "\n\n";

		int index = message.indexOf("&&");
		int indexBranchA = toRet.indexOf(") -> 1: ;\n");
		String toInsert_A = "";
		String nextState = "";
		int stateTmp_A = state_A;
		if(statement instanceof ProtocolIDNode ) {
			nextState = "("+roleA+"_STATE'=0)";
		}
		else {
			stateTmp_A++;
			nextState = "("+roleA+"_STATE'="+stateTmp_A+")";
		}
		
		String messageToAdd = message.substring(0,index);
		if(roleA.matches(".*\\d.*")|| messageToAdd.contains("_i")) {
			int indexDigit = -1;
			String role ;
			if(roleB.matches(".*\\d.*")) {
				role = roleB;
			}
			else {
				role = roleA;
			}
			for(int k=0; k<role.length(); k++) {
				if(Character.isDigit(role.charAt(k))) {
					indexDigit = k;
				}
			}
			String toReplaceA = "_"+role.charAt(indexDigit);
			messageToAdd = message.substring(0,index).substring(0,index).replaceAll("_i",toReplaceA);
		}
		
		if(indexBranchA==-1 || !(index_A<=indexBranchA && indexBranchA<=indexEnd_A)) {
			toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> 1: " + messageToAdd;
		}
		else {
			state_A  = Character.getNumericValue(toRet.charAt(indexBranchA-1));
			toRet = toRet.replace("[] ("+ roleA+"_STATE="+state_A+") -> 1: ;\n","");
			toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> 1: " + messageToAdd ;
		}
		if(message.substring(0,index).length()==0) {
			toInsert_A = toInsert_A + nextState + ";\n";
		}
		else {
			toInsert_A = toInsert_A +"&"+ nextState + ";\n";
		}
		toRet = new StringBuilder(toRet).insert(indexEnd_A-1,toInsert_A).toString();

		int index_B = toRet.indexOf(toFind_B);
		int indexBranchB = toRet.indexOf(") -> 1: ;\n");
		int indexEnd_B = toRet.indexOf("endmodule",index_B);
		int stateTmp_B = state_B;
		int alreadyDef =  toRet.indexOf(message.substring(index+2,message.length()));
		if(alreadyDef!=-1) {
			String toFind = "("+roleB+"_STATE'=";
			String toFind2 = "("+roleB+"_STATE=";
			int stateInit_B = -1;
			for(int k=index_B; k<alreadyDef; k++) {
				if(toRet.indexOf(toFind2,k)!=-1 && toRet.indexOf(message.substring(index+2,message.length()),k)!=-1) {
					stateInit_B = k;
				}
			}
			int indexB_def = toRet.indexOf(toFind,alreadyDef);
			stateTmp_B = Character.getNumericValue(toRet.charAt(indexB_def+toFind.length()));
			nextState = "("+roleB +"_STATE'=" + stateTmp_B +")";
			state_B = Character.getNumericValue(toRet.charAt(stateInit_B+toFind2.length()));
		}
		else if(statement instanceof ProtocolIDNode) {
			nextState = "("+roleB +"_STATE'=" + 0 +")";
		}
		else {
			stateTmp_B++;
			nextState = "("+roleB+"_STATE'="+stateTmp_B+")";
		}
		
		messageToAdd = message.substring(index+2,message.length());
		if(roleB.matches(".*\\d.*") || messageToAdd.contains("_i")) {
			int indexDigit = -1;
			String role ;
			if(roleB.matches(".*\\d.*")) {
				role = roleB;
			}
			else {
				role = roleA;
			}
			for(int k=0; k<role.length(); k++) {
				if(Character.isDigit(role.charAt(k))) {
					indexDigit = k;
				}
			}
			String toReplaceB = "_"+role.charAt(indexDigit);
			messageToAdd = message.substring(index+2,message.length()).replaceAll("_i",toReplaceB);
		}
		
		String toInsert_B = "";
		if(indexBranchB==-1 || !(index_B<=indexBranchB && indexBranchB<=indexEnd_B)) {
			toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> 1: " + messageToAdd; 
		}
		else {
			state_B = Character.getNumericValue(toRet.charAt(indexBranchB-1));
			toRet = toRet.replace("[] ("+ roleB+"_STATE="+state_B+") -> 1: ;\n","");
			toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> 1: " + messageToAdd; 
		}
		if(message.substring(index+2,message.length()).length()==0) {
			toInsert_B = toInsert_B + nextState + ";\n";
		}
		else {
			toInsert_B = toInsert_B +"&"+ nextState + ";\n";
		}
		indexEnd_B = toRet.indexOf("endmodule",index_B);

		toRet = new StringBuilder(toRet).insert(indexEnd_B-1,toInsert_B).toString();
		//}
		if(statement!=null) {
			toRet = statement.codeGenerator(toRet,mapStates,mapStatesBranches,roles);
		}
		
		roleA = roleAtmp;
		roleB = roleBtmp;

		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
