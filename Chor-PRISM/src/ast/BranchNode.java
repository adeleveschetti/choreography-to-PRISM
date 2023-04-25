package ast;

import java.util.ArrayList;
import java.util.Random;

import lib.Functions;

public class BranchNode implements Node{

	private String role;
	private ArrayList<String> outRole;
	private boolean branch;
	private ArrayList<String> rates;
	private ArrayList<Node> updates;
	private ArrayList<Node> preconditions;
	private ArrayList<Node> statements;

	public BranchNode(String _role, ArrayList<String> _outRole, boolean _branch, ArrayList<String> _rates, ArrayList<Node> _preconditions, ArrayList<Node> _updates, ArrayList<Node> _statements) {
		role = _role;
		outRole = _outRole;
		branch = _branch;
		rates = _rates;
		preconditions = _preconditions;
		updates = _updates;
		statements = _statements;
	}

	public String getRoleA() {
		return role;
	}

	@Override
	public String toPrint() {

		String toRet = role + " to ";

		for(String el : outRole) {
			toRet = toRet + el + " ";
		}
		if(branch) {
			toRet = toRet + "+";
		}
		for(int i=0; i<rates.size(); i++) {
			if(preconditions.size()==rates.size()) {
				toRet = toRet +  preconditions.get(i).toPrint();
			}

			toRet = toRet + " -> [" + rates.get(i) + "] " + updates.get(i).toPrint() + "\n";
		}
		for(Node el : statements) {
			toRet  = toRet+el.toPrint();
		}
		return toRet;
	}


	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, int state) {
		Functions funs = new Functions();
		String roleTmp = funs.changeIndex(role,index,totIndex);
		ArrayList<String> outRolesTmp = new ArrayList<String>();
		for(String el : outRole) {
			outRolesTmp.add(Functions.changeIndex(el,index,totIndex));
		}
		ArrayList<String> totA = new ArrayList<String> ();
		ArrayList<ArrayList<String>> totB = new ArrayList<ArrayList<String>> ();
		int stateA = -1;
		boolean ifThen = false;
		String label = "";
		boolean contained = false;
		for(String el : outRolesTmp) {
			if(el.equals(roleTmp)) {
				contained = true;
			}
		}

		for(int i=0; i<rates.size(); i++) {

			if(!contained) {

				String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
				StringBuilder salt = new StringBuilder();
				Random rnd = new Random();
				int size = 5;
				while (salt.length() < size) { // length of the random string.
					int indexLabel = (int) (rnd.nextFloat() * SALTCHARS.length());
					salt.append(SALTCHARS.charAt(indexLabel));
				}
				label = salt.toString();

			}
			for(Node el : modules) {
				if(el.toPrint().equals(roleTmp)) {
					stateA = ((ModuleNode) el).getState();
					if(!branch && (statements!=null && !(statements.get(0) instanceof RecNode))) {
						((ModuleNode) el).setState(stateA+1);
					}
				}
			}
			String toRetRoleA = "";
			ArrayList<String> toRetRoleB = new ArrayList<String> ();
			for(int ii=0; ii<outRole.size(); ii++) {
				toRetRoleB.add("");
			}

			if(code.contains("-> ;")) {

				int indexState = code.indexOf("-> ;");
				int indexStart = code.indexOf("module " + roleTmp);
				int whereToAdd = -1;
				whereToAdd = code.indexOf("endmodule",indexStart);

				if(indexState<=whereToAdd && indexState>=indexStart) {
					String tmpCode = code.substring(indexStart,indexState);
					int indexBracks = tmpCode.lastIndexOf("[");
					toRetRoleA = tmpCode.charAt(indexBracks)+label+tmpCode.substring(indexBracks+1,tmpCode.length());
					String toReplace = tmpCode.substring(indexBracks,tmpCode.length()) + "-> ;";
					code = code.replace(toReplace,"");

				}
				else {

					toRetRoleA = "["+label+"] (" + roleTmp +"=" + Integer.toString(stateA) + ") " ;
					for(int j = 0; j<outRolesTmp.size(); j++) {
						indexStart = code.indexOf("module "+outRolesTmp.get(j));
						whereToAdd = code.indexOf("endmodule",indexStart);
						if(indexState<=whereToAdd && indexState>=indexStart) {
							ifThen = true;
							String tmpCode = code.substring(indexStart,indexState);
							int indexBracks = tmpCode.lastIndexOf("[");
							String tmpRetB = tmpCode.charAt(indexBracks)+label+tmpCode.substring(indexBracks+1,tmpCode.length());
							String toReplace = tmpCode.substring(indexBracks,tmpCode.length()) + "-> ;";
							code = code.replace(toReplace,"");

							toRetRoleB.set(j,tmpRetB);
						}
					}
				}


			}
			else {
				toRetRoleA = "["+label+"] (" + roleTmp +"=" + Integer.toString(stateA) + ") " ;
			}



			int indexRate = rates.get(i).indexOf("*");
			int indexUp = updates.get(i).generateCode("",index,totIndex,modules, state).indexOf("&&");
			if(preconditions.size()==rates.size()) {
				int indexPrec = preconditions.get(i).generateCode("",index,totIndex,modules, state).indexOf("&&");
				String precA = preconditions.get(i).generateCode("",index,totIndex,modules, state).substring(0,indexPrec);
				toRetRoleA = toRetRoleA +  Functions.returnStringNewIndex(precA,index,totIndex);
				for(int j=0; j<outRole.size(); j++) {
					String precCode = preconditions.get(i).generateCode("",index,totIndex,modules, state).substring(indexPrec+2,preconditions.get(i).generateCode("",index,totIndex,modules, state).length());
					toRetRoleB.set(j,Functions.returnStringNewIndex(precCode,j+1,totIndex));
				}
			}
			String upA = Functions.returnStringNewIndex(updates.get(i).generateCode("",index,totIndex,modules, state).substring(0,indexUp-1),index,totIndex);
			toRetRoleA = toRetRoleA + " -> " + Functions.returnStringNewIndex(rates.get(i).substring(0,indexRate),index,totIndex) + " : " + upA;

			if(statements!=null) {
				if(statements.get(0) instanceof RecNode) {
					toRetRoleA = toRetRoleA + "&(" + roleTmp +"'=" + Integer.toString(state) + "); " ;
				}
				else {
					toRetRoleA = toRetRoleA + "&(" + roleTmp +"'=" + Integer.toString(stateA+i+1) + "); " ;
				}
			}

			for(int j=0; j<outRole.size(); j++) {
				int stateB = -1;
				for(Node el : modules) {
					if(el.toPrint().equals(outRolesTmp.get(j))) {
						stateB = ((ModuleNode) el).getState();
						if(!branch && (statements!=null && !(statements.get(0) instanceof RecNode))) {
							((ModuleNode) el).setState(stateB+1);
						}
					}
				}
				String upCode = "";
				
				if(!outRole.get(j).contains("[i]") && !outRole.get(j).contains("[i+1]") && (updates.get(i).generateCode("",index,totIndex,modules, state).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules, state).length()).contains("[i]") || updates.get(i).generateCode("",index,totIndex,modules, state).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules, state).length()).contains("[i]"))) {
					upCode = Functions.returnStringNewIndex(updates.get(i).generateCode("",index,totIndex,modules, state).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules, state).length()),index,totIndex);
				}
				else {
					upCode = Functions.returnStringNewIndex(updates.get(i).generateCode("",index,totIndex,modules, state).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules, state).length()),j+1,totIndex);
				}
				String rateB = Functions.returnStringNewIndex(rates.get(i).substring(indexRate+1,rates.get(i).length()),j+1,totIndex);
				String toCheck="";
				if(!ifThen) {
					toCheck = "["+label+"] (" + outRolesTmp.get(j) +"=" + Integer.toString(stateB) + ") " ;
				}
				toCheck = toCheck + toRetRoleB.get(j) + " -> " + rateB + " : " + upCode + "&(" + outRolesTmp.get(j) +"'=" ;
				if(statements!=null) {
					if(statements.get(0) instanceof RecNode) {
						toCheck = toCheck +  Integer.toString(state) + "); ";
					}
					else {
						toCheck = toCheck +  Integer.toString(stateB+i+1) + "); ";
					}
				}
				toRetRoleB.set(j,toCheck);
			}
			totB.add(toRetRoleB);
			totA.add(toRetRoleA);
		}

		int indexRoleA = code.indexOf("module "+roleTmp);
		int whereToAdd = code.indexOf("endmodule",indexRoleA);
		String codeToRet = code.substring(0,whereToAdd) ;
		for(String el : totA) {
			codeToRet = codeToRet + "\n" + el;
		}
		codeToRet = codeToRet + "\n" + code.substring(whereToAdd,code.length());
		for(String el : outRolesTmp) {
			String codeTmp = codeToRet;
			int indexRoleB = codeTmp.indexOf("module "+el);
			whereToAdd = codeTmp.indexOf("endmodule",indexRoleB);
			codeToRet = codeTmp.substring(0,whereToAdd) ;
			for(ArrayList<String> list : totB) {
				for(String el2 : list) {
					codeToRet = codeToRet + "\n" + el2;
				}
			}
			codeToRet = codeToRet + "\n" + codeTmp.substring(whereToAdd,codeTmp.length());

		}

		if(branch) {
			for(Node el : modules) {
				if(el.toPrint().equals(roleTmp)) {
					((ModuleNode) el).setState(((ModuleNode) el).getState()+1);
				}
				for(String el2 : outRolesTmp) {
					if(el.toPrint().equals(el2)) {
						((ModuleNode) el).setState(((ModuleNode) el).getState()+1);
					}
				}
			}
		}

		/*
		for(String el : totA) {
			System.out.println(el);
			System.out.println("========");
		}

		for(ArrayList<String> list : totB) {
			for(String el : list) {
				System.out.println(el);
				System.out.println("========");
			}
		}*/
		if(statements!=null) {
			for(Node stat : statements) {
				if(!(stat instanceof RecNode)) {
					codeToRet = stat.generateCode(codeToRet,index,totIndex,modules, state);
				}
			}
		}
		//System.out.println("sono qua! " + codeToRet);
		return codeToRet;
	}

}
