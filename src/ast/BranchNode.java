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
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches) {


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

			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < size) { // length of the random string.
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String label = salt.toString();
			int index = messages.get(i).indexOf("&&");
			int indexRole = rates.get(i).toString().indexOf("*");
			String toInsert_A = "["+label+"] ("+ roleA+"_STATE=" + state_A + ") -> " + rates.get(i).toString().substring(0,indexRole) + " : " + messages.get(i).substring(0,index) + ";\n";
			toRet = new StringBuilder(toRet).insert(index_A+toFind_A.length(),toInsert_A).toString();

			String toFind_B = "module " + roleB + "\n\n";
			int index_B = toRet.indexOf(toFind_B);

			String toInsert_B = "["+label+"] ("+ roleB+ "_STATE=" + state_B +") -> " + rates.get(i).toString().substring(indexRole+1,rates.get(i).toString().length())  + " : " + messages.get(i).substring(index+2,messages.get(i).length()) + ";\n";
			toRet = new StringBuilder(toRet).insert(index_B+toFind_B.length(),toInsert_B).toString();

			String roleC = "";
			for(String el : mapStates.keySet()) {
				if(!el.equals(roleA) && !el.equals(roleB)) {
					roleC = el ;
					String toFind_C = "module " + roleC + "\n\n";
					int index_C = toRet.indexOf(toFind_C);
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
					String toInsert_C = "[] ("+ roleC + "_STATE=" + state_C +") -> \n" ; 
					toRet = new StringBuilder(toRet).insert(index_C+toFind_C.length(),toInsert_C).toString();
				}
			}
			toRet = statements.get(i).codeGenerator(toRet,mapStates,mapStatesBranches);

		}



		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
