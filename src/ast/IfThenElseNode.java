package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class IfThenElseNode implements Node{

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
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches) {

		String toFind_A = "module " + role + "\n\n";
		int index_A = toRet.indexOf(toFind_A);

		int state;

		if(mapStates.get(role).size()==0) {
			state = 0;
			mapStates.get(role).add(state);
		}
		else {
			state = mapStates.get(role).get(mapStates.get(role).size()-1);
			mapStates.get(role).add(state+1);
		}

		String toInsert_A = "[] ("+ cond + ")&("+ role +"_STATE=" + state +") -> 1:";

		if(thenStat instanceof ProtocolIDNode) {
			toInsert_A = "[] ("+ cond + ")&("+ role +"_STATE=" + state +") -> 1: (" + role +"_STATE'=0);\n" ;
		}
		else {
			toInsert_A = toInsert_A + thenStat.codeGenerator(toRet,mapStates,mapStatesBranches) + ";\n";
		}

		toInsert_A = toInsert_A + "[] !("+ cond + ")&("+ role +"_STATE=" + state +") -> 1:" + role + "_STATE'="+(state+1)+";\n";

		toRet = new StringBuilder(toRet).insert(index_A+toFind_A.length(),toInsert_A).toString();
		if(elseStat!=null) {
			toRet = elseStat.codeGenerator(toRet,mapStates,mapStatesBranches);
		}
		if(thenStat instanceof InternalActionNode ) {
			
			toRet = thenStat.getStatement().codeGenerator(toRet,mapStates,mapStatesBranches);
		}

		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
