package ast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class BranchNode implements Node{
	static final int lenIndex = 3;

	private String roleA = null;
	private String roleB = null;
	private ArrayList<String> rates = null;
	private ArrayList<ArrayList<String>> messages = null;
	private ArrayList<ArrayList<Node>> forLoops = null;
	private ArrayList<Node> statements = null;
	private ArrayList<String> actions = null;

	public BranchNode(String A, String B, ArrayList<String> listRates, ArrayList<ArrayList<String>> listMessages, ArrayList<Node> listStatements, ArrayList<ArrayList<Node>> loops, ArrayList<String> act) {
		roleA = A;
		roleB = B;
		if(listRates!=null) {
			rates = new ArrayList<String>();
			for(String el : listRates) {
				rates.add(el);
			}
		}
		if(listMessages!=null) {
			messages = new ArrayList<ArrayList<String>>();
			for(ArrayList<String> list : listMessages) {
				ArrayList<String> tmp = new ArrayList<String> ();
				for(String el : list) {
					tmp.add(el);
				}
				messages.add(tmp);
			}
		}
		if(listStatements!=null) {
			statements = new ArrayList<Node>();
			for(Node el : listStatements) {
				statements.add(el);
			}
		}

		if(loops!=null) {
			forLoops = new ArrayList<ArrayList<Node>>();
			for(ArrayList el : loops) {
				forLoops.add(el);
			}
		}
		if(act!=null) {
			actions = new ArrayList<String>();
			actions = act;
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
	public ArrayList<String> getRate() {
		return rates;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles, int currIndex, int totIndex) {
		String roleAtmp = roleA;
		String roleBtmp = roleB;
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
		int size = 5; 
		// generating the label
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

		if(mapStates.get(roleB)==null || mapStates.get(roleB).size()==0) {
			state_B = 0;
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(state_B);
			mapStates.put(roleB,tmp);
		}
		else {
			state_B = mapStates.get(roleB).get(mapStates.get(roleB).size()-1)+1;
			mapStates.get(roleB).add(state_B);

		}


		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i = 0; i<rates.size() ; i++) {
			ArrayList<String> tmpMessage = messages.get(i);

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
			if(rateA.contains("[i]")) {
				rateA=rateA.replace("[i]",""+Integer.toString(currIndex+1));
			}
			if(rateA.contains("[i+1]")) {
				int indexToPrint = -1;
				if(currIndex+2>totIndex) {
					indexToPrint = 1;
				}
				else {
					indexToPrint = currIndex+2;
				}
				rateA=rateA.replace("[i+1]",""+Integer.toString(indexToPrint));
			}

			if(rateB.contains("[i]")) {
				rateB=rateB.replace("[i]",""+Integer.toString(currIndex+1));
			}
			if(rateB.contains("[i+1]")) {
				int indexToPrint = -1;
				if(currIndex+2>totIndex) {
					indexToPrint = 1;
				}
				else {
					indexToPrint = currIndex+2;
				}
				rateB=rateB.replace("[i+1]",""+Integer.toString(indexToPrint));
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

			String messageForLoop_1 = "";
			String messageForLoop_2 = "";

			if(forLoops.get(i).size()>0 && forLoops.get(i).get(0)!=null) {
				messageForLoop_1 = forLoops.get(i).get(0).codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
			}
			if(forLoops.get(i).size()>0 && forLoops.get(i).get(1)!=null) {
				messageForLoop_2 = forLoops.get(i).get(1).codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
			}
			String messageToAdd = tmpMessage.get(0);

			if(roleA.matches(".*\\d.*")|| messageToAdd.contains("[i]") || messageToAdd.contains("[i+1]")) {
				if(flag) {
					messageToAdd = tmpMessage.get(0).replace("[i]",Integer.toString(currIndex+1));
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
					messageToAdd =  tmpMessage.get(0).replaceAll("\\[i\\]",toReplaceA);
					messageToAdd =  messageToAdd.replaceAll("\\[i+1\\]",toReplaceA);
				}
			}

			if(forLoops.get(i).size()>0  && forLoops.get(i).get(0).getRoleA().contains(roleA.substring(0,roleA.length()-1))) {
				if(messageToAdd.equals(" ")) {
					messageToAdd = messageToAdd+messageForLoop_1;
				}
				else {
					messageToAdd = messageToAdd+"&"+messageForLoop_1;
				}
			}
			if(forLoops.get(i).size()>0  && forLoops.get(i).get(1).getRoleA().contains(roleA.substring(0,roleA.length()-1))) {
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

			if(roleA.equals(roleB)) {label="";}
			if(roleA.equals(roleB) || indexBranchA==-1 || !(index_A<=indexBranchA && indexBranchA<=indexEnd_A)) {
				if(precond!=null && precond.length()>1) {
					toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ")&"+precond+"-> " + rateA + ": " + messageToAdd;
				}
				else {
					toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> " + rateA + ": " + messageToAdd;
				}
			}
			else {
				state_A  = Character.getNumericValue(toRet.charAt(indexBranchA-1));
				toRet = toRet.replace("[] ("+ roleA+"_STATE="+state_A+") -> \n","");
				if(precond!=null && precond.length()>1) {
					toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ")&"+precond+"-> " + rateA + ": " + messageToAdd;
				}
				else {
					toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> " + rateA + ": " + messageToAdd;
				}			
			}
			if(tmpMessage.get(0).length()<=1) {
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
				}
				else {
					stateTmp_B++;
					nextState = "("+roleB+"_STATE'="+stateTmp_B+")";
				}
				if(tmpMessage.size()>1) {
					messageToAdd = tmpMessage.get(1);
				}
				else {
					messageToAdd = "";
				}

				if(roleB.matches(".*\\d.*")|| messageToAdd.contains("[i]") || messageToAdd.contains("[i+1]")) {
					if(flag) {
						messageToAdd = messageToAdd.replace("[i]",Integer.toString(currIndex+1));
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
						messageToAdd =  messageToAdd.replaceAll("\\[i\\]",toReplaceA);
						messageToAdd =  messageToAdd.replaceAll("\\[i+1\\]",toReplaceA);
					}

				}
				if(forLoops.get(i).size()>0  && forLoops.get(i).get(0).getRoleA().contains(roleB.substring(0,roleB.length()-1))) {
					if(messageToAdd.equals(" ")) {
						messageToAdd = messageToAdd+messageForLoop_1;
					}
					else {
						messageToAdd = messageToAdd+"&"+messageForLoop_1;
					}
				}
				if(forLoops.get(i).size()>0  && forLoops.get(i).get(1).getRoleA().contains(roleB.substring(0,roleB.length()-1))) {
					if(messageToAdd.equals(" ")) {
						messageToAdd = messageToAdd+messageForLoop_2;
					}
					else {
						messageToAdd = messageToAdd+"&"+messageForLoop_2;
					}
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
					if(precond_B!=null && precond_B.length()>1) {
						toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ")&"+precond_B+" -> " + rateB + ": " + messageToAdd; 
					}
					else {
						toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> " + rateB + ": " + messageToAdd; 
					}
				}
				else {
					state_B = Character.getNumericValue(toRet.charAt(indexBranchB-1));
					toRet = toRet.replace("[] ("+ roleB+"_STATE="+state_B+") -> \n","");
					if(precond_B!=null && precond_B.length()>1) {
						toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ")&"+precond_B+" -> " + rateB + ": " + messageToAdd; 
					}
					else {
						toInsert_B = "["+label+"] ("+ roleB+"_STATE=" + state_B + ") -> " + rateB + ": " + messageToAdd; 
					}				}
				if(tmpMessage.size()==1 || tmpMessage.get(1).length()<=1) {
					toInsert_B = toInsert_B + nextState + ";\n";
				}
				else {
					toInsert_B = toInsert_B +"&"+ nextState + ";\n";
				}

				indexEnd_B = toRet.indexOf("endmodule",index_B);
				toRet = new StringBuilder(toRet).insert(indexEnd_B-1,toInsert_B).toString();
			}
			String roleC = "";
			for(String el : mapStates.keySet()) {
				if(el.matches(".*\\d.*")) {
					el = el.replace("[i]",Integer.toString(currIndex+1));
					if(currIndex+2>totIndex) {
						el = el.replace("[i+1]",Integer.toString(1));
					}
					else {
						el = el.replace("[i+1]",Integer.toString(currIndex+2));
					}
				}
				if(!el.equals(roleA) && !el.equals(roleB)) {
					roleC = el ;
					String toFind_C = "module " + roleC + "\n\n";
					int index_C = toRet.indexOf(toFind_C);
					int indexEnd_C = toRet.indexOf("endmodule",index_C);

					int state_C = -1;
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
			toRet = statements.get(i).codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);


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
