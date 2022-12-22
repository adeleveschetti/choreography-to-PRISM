package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class CommunicationNode implements Node {

	static final int lenIndex = 3;

	private String roleA = null;
	private String roleB = null;
	private ArrayList<String> message = null;
	private Node statement;
	private ArrayList<Node> forLoop = null;
	private String rate ;
	private ArrayList<String> actions = null;

	public CommunicationNode(String A, String B, ArrayList<String> mex, Node stat, String r, ArrayList<String> act) {
		roleA = A;
		roleB = B;
		message = new ArrayList<String>();
		message = mex;
		statement = stat;
		rate = r;
		actions = new ArrayList<String>();
		actions = act;
	}

	public CommunicationNode(String A, String B, ArrayList<String> mex, Node stat, ArrayList<Node> loop, String r, ArrayList<String> act) {
		roleA = A;
		roleB = B;
		message = new ArrayList<String>();
		message = mex;
		statement = stat;
		forLoop = loop ;
		rate = r;
		actions = new ArrayList<String>();
		actions = act;
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
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles, int currIndex, int totIndex) {
		String roleAtmp = roleA;
		String roleBtmp = roleB;
		String rateToPrintA = "";
		String rateToPrintB = "";
		if(!rate.equals("")) {
			int indexRole = rate.toString().indexOf("*");
			if(indexRole!=-1) {
				rateToPrintA = rate.substring(0,indexRole);
				rateToPrintB = rate.substring(indexRole+1,rate.length());
			}
			else {
				rateToPrintA = rate ;
				rateToPrintB = "1";
			}

		}
		else {
			rateToPrintA = "1" ;
			rateToPrintB = "1" ;
		}

		if(rateToPrintA.contains("[i]")) {
			rateToPrintA=rateToPrintA.replace("[i]",""+Integer.toString(currIndex+1));
		}
		if(rateToPrintA.contains("[i+1]")) {
			int indexToPrint = -1;
			if(currIndex+2>totIndex) {
				indexToPrint = 1;
			}
			else {
				indexToPrint = currIndex+2;
			}
			rateToPrintA=rateToPrintA.replace("[i+1]",""+Integer.toString(indexToPrint));
		}
		if(rateToPrintB.contains("[i]")) {
			rateToPrintB=rateToPrintB.replace("[i]",""+Integer.toString(currIndex+1));
		}
		if(rateToPrintB.contains("[i+1]")) {
			int indexToPrint = -1;
			if(currIndex+2>totIndex) {
				indexToPrint = 1;
			}
			else {
				indexToPrint = currIndex+2;
			}
			rateToPrintB=rateToPrintB.replace("[i+1]",""+Integer.toString(indexToPrint));
		}

		boolean flag = false;

		if(roleA.charAt(roleA.length()-lenIndex) == '+' || roleB.charAt(roleB.length()-lenIndex) == '+'){
			roleA = roleA.replace("[i]",Integer.toString(currIndex+1));
			if(currIndex+2>totIndex) {
				roleB = roleB.replace("[i+1]",Integer.toString(1));
			}
			else {
				roleB = roleB.replace("[i+1]",Integer.toString(currIndex+2));
			}
			flag = true;
		}
		if(!flag) {
			for(String el : roles) {
				if(el.contains(roleA.substring(0,roleA.length()-lenIndex))) {
					roleA = el;
				}
				if(el.contains(roleB.substring(0,roleB.length()-lenIndex))) {
					roleB = el;
				}
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
		if(mapStates.get(roleA)==null || mapStates.get(roleA).size()==0) {
			state_A = 0;
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(state_A);
			mapStates.put(roleA,tmp);
		}
		else {
			state_A = mapStates.get(roleA).get(mapStates.get(roleA).size()-1)+1;
			mapStates.get(roleA).add(state_A);
		}

		if(mapStates.get(roleB)==null ||mapStates.get(roleB).size()==0) {
			state_B = 0;
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(state_B);
			mapStates.put(roleB,tmp);
		}
		else {
			state_B = mapStates.get(roleB).get(mapStates.get(roleB).size()-1)+1;
			mapStates.get(roleB).add(state_B);

		}

		int index_A = toRet.indexOf(toFind_A);
		int indexEnd_A = toRet.indexOf("endmodule",index_A);
		String toFind_B = "module " + roleB + "\n\n";

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
		String messageForLoop_1 = "";
		String messageForLoop_2 = "";

		if(forLoop!=null) {
			if(forLoop.size()>0 && forLoop.get(0)!=null) {
				messageForLoop_1 = forLoop.get(0).codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
			}
			if(forLoop.size()>1 && forLoop.get(1)!=null) {
				messageForLoop_2 = forLoop.get(1).codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
			}
		}

		String messageToAdd = message.get(0);


		if(roleA.matches(".*\\d.*")|| messageToAdd.contains("[i]") || messageToAdd.contains("[i+1]")) {

			if(flag) {
				messageToAdd = message.get(0).replace("[i]",Integer.toString(currIndex+1));
				if(currIndex+2>totIndex) {
					messageToAdd = messageToAdd.replace("[i+1]",Integer.toString(1));
				}
				else {
					messageToAdd = messageToAdd.replace("[i+1]",Integer.toString(currIndex+2));
				}
			}
			else {
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
				String toReplaceA = ""+role.charAt(indexDigit);
				messageToAdd =  message.get(0).replaceAll("\\[i\\]",toReplaceA);
				messageToAdd =  messageToAdd.replaceAll("\\[i+1\\]",toReplaceA);
			}



		}

		if(forLoop!=null && forLoop.size()>0 && forLoop.get(0).getRoleA().contains(roleA.substring(0,roleA.length()-2))) {
			if(messageToAdd.equals(" ")) {
				messageToAdd = messageToAdd+messageForLoop_1;
			}
			else {
				messageToAdd = messageToAdd+"&"+messageForLoop_1;
			}
		}
		if(forLoop!=null && forLoop.size()>1 && forLoop.get(1).getRoleA().contains(roleA.substring(0,roleA.length()-2))) {
			if(messageToAdd.equals(" ")) {
				messageToAdd = messageToAdd+messageForLoop_2;
			}
			else {
				messageToAdd = messageToAdd+"&"+messageForLoop_2;
			}
		}
		String precond = null;
		if(actions!=null && actions.size()>0) {
			precond = actions.get(0);
			if(actions.get(0).contains("[i]")){
				if(currIndex+1>totIndex) {
					precond = precond.replace("[i]",Integer.toString(1));
				}
				else {
					precond = precond.replace("[i]",Integer.toString(currIndex+1));
				}			
			}
			if(actions.get(0).contains("[i+1]")){
				if(currIndex+2>totIndex) {
					precond = precond.replace("[i+1]",Integer.toString(1));
				}
				else {
					precond = precond.replace("[i+1]",Integer.toString(currIndex+2));
				}			
			}
		}

		if(indexBranchA==-1 || !(index_A<=indexBranchA && indexBranchA<=indexEnd_A)) {
			if(precond!=null) {
				toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ")&"+precond+"-> " + rateToPrintA + ": " + messageToAdd;
			}
			else {
				toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> " + rateToPrintA + ": " + messageToAdd;
			}
		}
		else {
			state_A  = Character.getNumericValue(toRet.charAt(indexBranchA-1));
			toRet = toRet.replace("[] ("+ roleA+"_STATE="+state_A+") -> 1: ;\n","");
			if(precond!=null) {
				toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ")&"+precond+"-> " + rateToPrintA + ": " + messageToAdd;
			}
			else {
				toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> " + rateToPrintA + ": " + messageToAdd;
			}
		}
		if(message.get(0).length()==1) {
			toInsert_A = toInsert_A + nextState + ";\n";
		}
		else {
			toInsert_A = toInsert_A +"&"+ nextState + ";\n";
		}
		indexEnd_A = toRet.indexOf("endmodule",index_A);
		toRet = new StringBuilder(toRet).insert(indexEnd_A-1,toInsert_A).toString();

		int index_B = toRet.indexOf(toFind_B);
		int indexBranchB = toRet.indexOf(") -> 1: ;\n");
		int indexEnd_B = toRet.indexOf("endmodule",index_B);
		int stateTmp_B = state_B;
		if(message.size()>1) {
			messageToAdd = message.get(1);
		}
		else {
			messageToAdd = " ";
		}


		if(roleB.matches(".*\\d.*") || messageToAdd.contains("[i]") || messageToAdd.contains("[i+1]")) {
			if(flag) {
				messageToAdd = message.get(1).replace("[i]",Integer.toString(currIndex+1));
				if(currIndex+2>totIndex) {
					messageToAdd = messageToAdd.replace("[i+1]",Integer.toString(1));
				}
				else {
					messageToAdd = messageToAdd.replace("[i+1]",Integer.toString(currIndex+2));
				}
			}
			else {
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
				String toReplaceA = ""+role.charAt(indexDigit);
				messageToAdd =  message.get(1).replaceAll("\\[i\\]",toReplaceA);
				messageToAdd =  messageToAdd.replaceAll("\\[i+1\\]",toReplaceA);
			}
		}
		if(forLoop!=null && forLoop.size()>0 && forLoop.get(0).getRoleA().contains(roleB.substring(0,roleB.length()-1))) {
			if(messageToAdd.equals(" ")) {
				messageToAdd = messageToAdd+messageForLoop_1;
			}
			else {
				messageToAdd = messageToAdd+"&"+messageForLoop_1;
			}
		}
		if(forLoop!=null && forLoop.size()>1 && forLoop.get(1).getRoleA().contains(roleB.substring(0,roleB.length()-1))) {
			if(messageToAdd.equals(" ")) {
				messageToAdd = messageToAdd+messageForLoop_2;
			}
			else {
				messageToAdd = messageToAdd+"&"+messageForLoop_2;
			}
		}
		int alreadyDef = toRet.indexOf(messageToAdd);

		if(alreadyDef!=-1 && messageToAdd.length()>1) {
			String toFind = "("+roleB+"_STATE'=";
			String toFind2 = "("+roleB+"_STATE=";
			int stateInit_B = -1;
			for(int k=index_B; k<alreadyDef; k++) {
				if(toRet.indexOf(toFind2,k)!=-1 && toRet.indexOf(messageToAdd,k)!=-1) {
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

		String precond_B = null;
		if(actions!=null && actions.size()>1) {
			precond_B = actions.get(1);
			if(actions.get(1).contains("[i]")){
				if(currIndex+1>totIndex) {
					precond_B = precond_B.replace("[i]",Integer.toString(1));
				}
				else {
					precond_B = precond_B.replace("[i]",Integer.toString(currIndex+1));
				}			
			}
			if(actions.get(1).contains("[i+1]")){
				if(currIndex+2>totIndex) {
					precond_B = precond_B.replace("[i+1]",Integer.toString(1));
				}
				else {
					precond_B = precond_B.replace("[i+1]",Integer.toString(currIndex+2));
				}			
			}
		}

		String toInsert_B = "";
		if(indexBranchB==-1 || !(index_B<=indexBranchB && indexBranchB<=indexEnd_B)) {
			if(precond_B!=null) {
				toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ")&"+precond_B+" -> " + rateToPrintB + ": " + messageToAdd; 
			}
			else {
				toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> " + rateToPrintB + ": " + messageToAdd; 
			}
		}
		else {
			state_B = Character.getNumericValue(toRet.charAt(indexBranchB-1));
			toRet = toRet.replace("[] ("+ roleB+"_STATE="+state_B+") -> 1: ;\n","");
			if(precond_B!=null) {
				toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ")&"+precond_B+" -> " + rateToPrintB + ": " + messageToAdd; 
			}
			else {
				toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> " + rateToPrintB + ": " + messageToAdd; 
			}
		}
		if(messageToAdd.length()<=1) {
			toInsert_B = toInsert_B + nextState + ";\n";
		}
		else {
			toInsert_B = toInsert_B +"&"+ nextState + ";\n";
		}
		indexEnd_B = toRet.indexOf("endmodule",index_B);

		toRet = new StringBuilder(toRet).insert(indexEnd_B-1,toInsert_B).toString();
		//}
		if(statement!=null) {
			toRet = statement.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
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
