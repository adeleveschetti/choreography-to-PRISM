package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class IfThenElseNode implements Node{
	static final int lenIndex = 3;

	private String role;
	private String cond; 
	private Node thenStat;
	private Node elseStat;

	public IfThenElseNode(String r, String c, Node then, Node elseS) {
		role = r;
		cond = c;
		thenStat = then;
		elseStat = elseS;
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return  "if " + cond + thenStat.toPrint() + "\n else " + elseStat.toPrint() + "\n";
	}

	@Override 
	public String getRoleA() {
		return role;
	}

	@Override 
	public String getRoleB() {
		return "";
	}
	
	@Override 
	public ArrayList<String> getRate() {
		return null;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles, int currIndex, int totIndex) {


		String roleTmp = role;
		String condTmp = cond;
		boolean flagName = false;

		if(role.charAt(role.length()-lenIndex) == '+'){
			if(currIndex+2>totIndex) {
				role = role.replace("[i+1]",Integer.toString(1));
			}
			else {
				role = role.replace("[i+1]",Integer.toString(currIndex+2));
			}
			flagName = true;
		}
		if(!flagName) {
			for(String el : roles) {
				if(el.contains(role.substring(0,role.length()-lenIndex))) {
					role = el;
				}

			}
		}

		String toFind_A = "module " + role + "\n\n";
		int index_A = toRet.indexOf(toFind_A);

		int state;

		if(mapStates.get(role)==null || mapStates.get(role).size()==0) {
			state = 0;
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(state);
			mapStates.put(role,tmp);
		}
		else {
			state = mapStates.get(role).get(mapStates.get(role).size()-1)+1;
			mapStates.get(role).add(state);
		}
		if(role.matches(".*\\d.*")) {
			if(flagName) {
				condTmp = condTmp.replace("[i]",Integer.toString(currIndex+1));
				if(currIndex+2>totIndex) {
					condTmp = condTmp.replace("[i+1]",Integer.toString(1));
				}
				else {
					condTmp = condTmp.replace("[i+1]",Integer.toString(currIndex+2));
				}
			}
			else {
				int indexDigit = -1;
				for(int k=0; k<role.length(); k++) {
					if(Character.isDigit(role.charAt(k))) {
						indexDigit = k;
					}
				}
				String toReplace = ""+role.charAt(indexDigit);
				condTmp = condTmp.replaceAll("\\[i\\]",toReplace);
			}
		}
		String toInsert_A = "[] ("+ condTmp + ")&("+ role +"_STATE=" + state +") -> 1:";

		boolean flag = false;
		if(thenStat instanceof ProtocolIDNode) {
			toInsert_A = "[] ("+ condTmp + ")&("+ role +"_STATE=" + state +") -> 1: (" + role +"_STATE'=0);\n" ;
		}
		else if(thenStat instanceof CommunicationNode) {
			toInsert_A = toInsert_A +"(" + role +"_STATE'=" + (state+1) +");\n" ;
			flag = true;
			state++;
		}
		else {

			toInsert_A = toInsert_A + thenStat.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex)+";\n" ;
			state++;
			flag = true;
		}

		if(elseStat instanceof ProtocolIDNode) {
			if(flag) {
				toInsert_A = toInsert_A + "[] !("+ condTmp + ")&("+ role +"_STATE=" + (state-1) +") -> 1:(" + role + "_STATE'=0);\n";
			}
			else {
				toInsert_A = toInsert_A + "[] !("+ condTmp + ")&("+ role +"_STATE=" + state +") -> 1:(" + role + "_STATE'=0);\n";
			}
		}
		else {
			if(flag) {
				toInsert_A = toInsert_A + "[] !("+ condTmp + ")&("+ role +"_STATE=" + (state-1) +") -> 1:(" + role + "_STATE'="+(state+1)+");\n";
			}
			else {
				toInsert_A = toInsert_A + "[] !("+ condTmp + ")&("+ role +"_STATE=" + state +") -> 1:(" + role + "_STATE'="+(state+1)+");\n";
			}
		}

		if(elseStat instanceof InternalActionNode) {
			toInsert_A = toInsert_A + elseStat.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex) + ";\n";
		}

		int indexEnd_A = toRet.indexOf("endmodule",index_A);

		toRet = new StringBuilder(toRet).insert(indexEnd_A-1,toInsert_A).toString();

		if(thenStat instanceof InternalActionNode ) {
			toRet = thenStat.getStatement().codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
		}
		if(thenStat instanceof CommunicationNode) {
			toRet = thenStat.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
		}
		if(elseStat!=null) {
			toRet = elseStat.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,  currIndex,  totIndex);
		}

		role = roleTmp;
		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
