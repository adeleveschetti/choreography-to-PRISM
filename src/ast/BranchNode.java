package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
	public String getRoleA() {
		return roleA;
	}

	@Override 
	public String getRoleB() {
		return roleB;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles) {
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
		int size = 5; 
		// generating the label
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


		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0; i<rates.size() ; i++) {
			String toFind_A = "module " + roleA + "\n\n";
			int index_A = toRet.indexOf(toFind_A);
			int indexEnd_A = toRet.indexOf("endmodule",index_A);

			int stateTmp_A = mapStates.get(roleA).get(mapStates.get(roleA).size()-1);
			int stateTmp_B = mapStates.get(roleB).get(mapStates.get(roleB).size()-1);


			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < size) { // length of the random string.
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String label = salt.toString();
			int index = messages.get(i).indexOf("&&");
			int indexRole = rates.get(i).toString().indexOf("*");
			String rateA, rateB = "";
			if(indexRole == -1) {
				rateA = rates.get(i).toString().substring(0,rates.get(i).toString().length());
				rateB = Integer.toString(1);
			}
			else {
				rateA = rates.get(i).toString().substring(0,indexRole);
				rateB = rates.get(i).toString().substring(indexRole+1,rates.get(i).toString().length());
			}
			if(rateA.contains("_i")) {
				int indexDigit = -1;
				for(int k=0; k<roleA.length(); k++) {
					if(Character.isDigit(roleA.charAt(k))) {
						indexDigit = k;
					}
				}
				rateA=rateA.replace("_i","_"+roleA.charAt(indexDigit));
			}
			if(rateB.contains("_i")) {
				int indexDigit = -1;
				for(int k=0; k<roleB.length(); k++) {
					if(Character.isDigit(roleB.charAt(k))) {
						indexDigit = k;
					}
				}
				rateB=rateB.replace("_i","_"+roleB.charAt(indexDigit));
			}
			
			int indexBranchA = toRet.indexOf(") -> \n");
			String toInsert_A = "";
			String nextState = "";			

			if(statements.get(i) instanceof ProtocolIDNode || !statements.get(i).getRoleA().contains(roleA.substring(0,roleA.length()-2)) && !statements.get(i).getRoleB().contains(roleA.substring(0,roleA.length()-2))) {
				nextState = "("+roleA +"_STATE'=" + 0 +")";
			}
			else {
				stateTmp_A++;
				nextState = "("+roleA+"_STATE'="+stateTmp_A+")";
			}
			String messageToAdd = messages.get(i).substring(0,index);
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
				messageToAdd = messages.get(i).substring(0,index).replaceAll("_i",toReplaceA);
			}
			if(roleA.equals(roleB)) {label="";}
			if(roleA.equals(roleB) || indexBranchA==-1 || !(index_A<=indexBranchA && indexBranchA<=indexEnd_A)) {
				toInsert_A =  "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> " + rateA + " : " + messageToAdd;// +"&"+nextState+ ";\n";
			}
			else {
				state_A  = Character.getNumericValue(toRet.charAt(indexBranchA-1));
				toRet = toRet.replace("[] ("+ roleA+"_STATE="+state_A+") -> \n","");
				toInsert_A =  "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> " + rateA + " : " + messageToAdd;// +"&"+nextState+ ";\n";
			}
			if(messages.get(i).substring(0,index).length()==0) {
				toInsert_A = toInsert_A + nextState + ";\n";
			}
			else {
				toInsert_A = toInsert_A +"&"+ nextState + ";\n";
			}
			toRet = new StringBuilder(toRet).insert(indexEnd_A-1,toInsert_A).toString();


			if(!roleA.equals(roleB)) {
				String toFind_B = "module " + roleB + "\n\n";
				int index_B = toRet.indexOf(toFind_B);
				int indexEnd_B = toRet.indexOf("endmodule",index_B);


				int indexBranchB = toRet.indexOf(") -> \n");
				if(statements.get(i) instanceof ProtocolIDNode || !statements.get(i).getRoleA().contains(roleB.substring(0,roleB.length()-2)) && !statements.get(i).getRoleB().contains(roleB.substring(0,roleB.length()-2))) {
					nextState = "("+roleB +"_STATE'=" + 0 +")";
					System.out.println(statements.get(i).getRoleA().contains(roleB.substring(0,roleB.length()-2)));
				}
				else {
					stateTmp_B++;
					nextState = "("+roleB+"_STATE'="+stateTmp_B+")";
				}

				messageToAdd = messages.get(i).substring(index+2,messages.get(i).length());
				if(roleB.matches(".*\\d.*")|| messageToAdd.contains("_i")) {
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

					messageToAdd = messages.get(i).substring(index+2,messages.get(i).length()).replaceAll("_i",toReplaceB);

				}
				
				String toInsert_B = "";
				if(indexBranchB==-1 || !(index_B<=indexBranchB && indexBranchB<=indexEnd_B)) {
					toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> " + rateB  + " : " + messageToAdd ;

				}
				else {
					state_B = Character.getNumericValue(toRet.charAt(indexBranchB-1));
					toRet = toRet.replace("[] ("+ roleB+"_STATE="+state_B+") -> \n","");
					toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> " + rateB  + " : " + messageToAdd ;//+ "&"+ nextState +";\n";
				}
				if(messages.get(i).substring(index+2,messages.get(i).length()).length()==0) {
					toInsert_B = toInsert_B + nextState + ";\n";
				}
				else {
					toInsert_B = toInsert_B +"&"+ nextState + ";\n";
				}

				indexEnd_B = toRet.indexOf("endmodule",index_B);
				//String toInsert_B = "["+label+"] ("+ roleB+ "_STATE=" + state_B +") -> " + rates.get(i).toString().substring(indexRole+1,rates.get(i).toString().length())  + " : " + messages.get(i).substring(index+2,messages.get(i).length()) + ";\n";
				toRet = new StringBuilder(toRet).insert(indexEnd_B-1,toInsert_B).toString();
			}
			String roleC = "";
			for(String el : mapStates.keySet()) {
				if(!el.equals(roleA) && !el.equals(roleB)) {
					roleC = el ;
					String toFind_C = "module " + roleC + "\n\n";
					int index_C = toRet.indexOf(toFind_C);
					int indexEnd_C = toRet.indexOf("endmodule",index_C);

					int state_C ;
					if(mapStatesBranches.get(roleC).size()==0) {
						if(mapStates.get(roleC).size()==0) {
							state_C = 0;
							mapStates.get(roleC).add(state_C);
						}
						else {
							state_C = mapStates.get(roleC).get(mapStates.get(roleC).size()-1)+1;
							mapStates.get(roleC).add(state_C);
						}
						mapStatesBranches.get(roleC).add(state_C);
					}
					else {
						state_C = mapStatesBranches.get(roleC).get(mapStatesBranches.get(roleC).size()-1);
					}
					String toInsert_C = "[] ("+ roleC + "_STATE=" + state_C +") -> 1: ;\n" ; 
					toRet = new StringBuilder(toRet).insert(indexEnd_C-1,toInsert_C).toString();
				}
			}


			//mapStates.get(roleA).add(stateTmp_A);
			toRet = statements.get(i).codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles);


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
