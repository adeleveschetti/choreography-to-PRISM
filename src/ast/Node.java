package ast;

import java.util.ArrayList;
import java.util.HashMap;

public interface Node {

	String toPrint();
	
	String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles, int currIndex, int totIndex);
	
	Node getStatement();
	
	String getRoleA();
	
	String getRoleB();

	ArrayList<String> getRate();
	
}
