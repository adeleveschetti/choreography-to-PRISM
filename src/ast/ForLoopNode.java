package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class ForLoopNode implements Node{

	private String message ;
	private String roleA; // role which does the action
	private String roleB; // first role of the for condition
	private String roleC; // second role of the for condition    
	
	/*
	 * foreach( roleB != roleC ) message @ roleA
	 */


	public ForLoopNode(String mex, String rolA, String rolB, String rolC){
		message = mex;
		roleA = rolA;
		roleB = rolB;
		roleC = rolC;

	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String, ArrayList<Integer>> mapStates,
			HashMap<String, ArrayList<Integer>> mapStatesBranches, ArrayList<String> roles, ArrayList<String> allRoles) {
		String ret = "";
		for(String el : allRoles) {
			if(el.contains(roleB.substring(0,roleB.length()-2))) {
				for(String el2 : roles) {
					if(el2.contains(roleB.substring(0,roleB.length()-2)) && !el2.equals(el)) {
						String messageTmp = message;

						if(el.matches(".*\\d.*")|| messageTmp.contains("_k")) {
							int indexDigit = -1;
							for(int k=0; k<el.length(); k++) {
								if(Character.isDigit(el.charAt(k))) {
									indexDigit = k;
								}
							}
							String toReplaceA = "_"+el.charAt(indexDigit);
							messageTmp = messageTmp.replaceAll("_k",toReplaceA);
							
						}
						if(el2.matches(".*\\d.*")|| messageTmp.contains("_i")) {
							int indexDigit = -1;
							for(int k=0; k<el2.length(); k++) {
								if(Character.isDigit(el2.charAt(k))) {
									indexDigit = k;
								}
							}
							String toReplaceA = "_"+el2.charAt(indexDigit);
							messageTmp = messageTmp.replaceAll("_i",toReplaceA);
							
						}
						ret = ret+"("+messageTmp+")&";
					}
				}
			}
		}
		return ret;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoleA() {
		return roleA;
	}

	@Override
	public String getRoleB() {
		// TODO Auto-generated method stub
		return null;
	}

}
