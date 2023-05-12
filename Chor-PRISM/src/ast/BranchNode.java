package ast;

import java.util.ArrayList;
import java.util.Random;

import lib.Functions;
import lib.Pair;

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
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules) {
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
					if(!branch && (statements!=null && !(statements.get(i) instanceof RecNode))) {
						((ModuleNode) el).setState(stateA+1);
					}
				}
			}
			String toRetRoleA = "";
			ArrayList<String> toRetRoleB = new ArrayList<String> ();
			for(int ii=0; ii<outRole.size(); ii++) {
				toRetRoleB.add("");
			}
			String toFind = "-> ;";

			if(code.contains(toFind)) {
				int indexState = code.indexOf(toFind);
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
			int indexUp = updates.get(i).generateCode("",index,totIndex,modules).indexOf("&&");

			if(preconditions.size()==rates.size()) {
				int indexPrec = preconditions.get(i).generateCode("",index,totIndex,modules).indexOf("&&");
				String precA = preconditions.get(i).generateCode("",index,totIndex,modules).substring(0,indexPrec);
				toRetRoleA = toRetRoleA +  Functions.returnStringNewIndex(precA,index,totIndex);
				for(int j=0; j<outRole.size(); j++) {
					String precCode = preconditions.get(i).generateCode("",index,totIndex,modules).substring(indexPrec+2,preconditions.get(i).generateCode("",index,totIndex,modules).length());
					toRetRoleB.set(j,Functions.returnStringNewIndex(precCode,j+1,totIndex));
				}
			}

			String upA = Functions.returnStringNewIndex(updates.get(i).generateCode("",index,totIndex,modules).substring(0,indexUp),index,totIndex);
			toRetRoleA = toRetRoleA + " -> " + Functions.returnStringNewIndex(rates.get(i).substring(0,indexRate),index,totIndex) + " : " + upA;
			if(statements!=null) {
				boolean continues = false;
				if(statements.get(i) instanceof BranchNode) {
					continues = Functions.returnStringNewIndex(((BranchNode) statements.get(i)).getRoleA(),index,totIndex).equals(roleTmp);
				}
				else if(statements.get(i) instanceof IfThenElseNode) {
					continues = Functions.returnStringNewIndex(((IfThenElseNode) statements.get(i)).getRole(),index,totIndex).equals(roleTmp);
				}
				else if(statements.get(i) instanceof InternalActionNode) {
					continues = Functions.returnStringNewIndex(((InternalActionNode) statements.get(i)).getRole(),index,totIndex).equals(roleTmp);
				}

				if(!upA.equals("") && !upA.equals(" ")) {
					toRetRoleA = toRetRoleA + "&";
				}

				if(statements.get(i) instanceof RecNode) {
					toRetRoleA = toRetRoleA + "(" + roleTmp +"'=" + Integer.toString(((RecNode) statements.get(i)).getState()) + "); " ;
				}
				else if(statements.get(i) instanceof EndNode) {
					toRetRoleA = toRetRoleA +";" ;
				}
				else if(!continues) {
					toRetRoleA = toRetRoleA + "(" + roleTmp +"'=" + Integer.toString(0) + "); ";
				}
				else {
					toRetRoleA = toRetRoleA + "(" + roleTmp +"'=" + Integer.toString(stateA+i+1) + "); " ;
				}
			}
			if(!contained) {
				for(int j=0; j<outRole.size(); j++) {
					int stateB = -1;
					for(Node el : modules) {
						boolean continuesB = false;
						if((statements!=null && (statements.get(i) instanceof BranchNode))) {
							continuesB = Functions.returnStringNewIndex(((BranchNode) statements.get(i)).getRoleA(),index+j,totIndex).equals(outRolesTmp.get(j));
						}
						else if((statements!=null && (statements.get(i) instanceof IfThenElseNode))) {
							continuesB = Functions.returnStringNewIndex(((IfThenElseNode) statements.get(i)).getRole(),index+j,totIndex).equals(outRolesTmp.get(j));
						}
						else if((statements!=null && (statements.get(i) instanceof InternalActionNode))) {
							continuesB = Functions.returnStringNewIndex(((InternalActionNode) statements.get(i)).getRole(),index+j,totIndex).equals(outRolesTmp.get(j));
						}
						if(el.toPrint().equals(outRolesTmp.get(j))) {
							stateB = ((ModuleNode) el).getState();
							if(!branch && (statements!=null && !(statements.get(i) instanceof RecNode)) && continuesB) {
								((ModuleNode) el).setState(stateB+1);
							}
						}
					}
					String upCode = "";

					if(!outRole.get(j).contains("[i]") && !outRole.get(j).contains("[i+1]") && (updates.get(i).generateCode("",index,totIndex,modules).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules).length()).contains("[i]") || updates.get(i).generateCode("",index,totIndex,modules).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules).length()).contains("[i+1]"))) {
						upCode = Functions.returnStringNewIndex(updates.get(i).generateCode("",index,totIndex,modules).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules).length()),index,totIndex);
					}
					else {
						upCode = Functions.returnStringNewIndex(updates.get(i).generateCode("",index,totIndex,modules).substring(indexUp+2,updates.get(i).generateCode("",index,totIndex,modules).length()),index+j,totIndex);
					}

					String rateB = Functions.returnStringNewIndex(rates.get(i).substring(indexRate+1,rates.get(i).length()),index+j,totIndex);
					String toCheck="";
					if(!ifThen) {
						toCheck = "["+label+"] (" + outRolesTmp.get(j) +"=" + Integer.toString(stateB) + ") " ;
					}

					toCheck = toCheck + toRetRoleB.get(j) + " -> " + rateB + " : " + upCode ;


					if(!upCode.equals("") && !upCode.equals(" ")) {
						toCheck = toCheck + "&";
					}
					toCheck = toCheck + "(" + outRolesTmp.get(j) +"'=";
					if(statements!=null) {
						boolean continues = false;
						if(statements.get(i) instanceof BranchNode) {
							continues = Functions.returnStringNewIndex(((BranchNode) statements.get(i)).getRoleA(),index+j,totIndex).equals(outRolesTmp.get(j) );
						}
						else if(statements.get(i) instanceof IfThenElseNode) {
							continues = Functions.returnStringNewIndex(((IfThenElseNode) statements.get(i)).getRole(),index+j,totIndex).equals(outRolesTmp.get(j) );
						}
						else if(statements.get(i) instanceof InternalActionNode) {
							continues = Functions.returnStringNewIndex(((InternalActionNode) statements.get(i)).getRole(),index+j,totIndex).equals(outRolesTmp.get(j) );
						}
						
						if(statements.get(i) instanceof RecNode) {
							toCheck = toCheck +  Integer.toString(((RecNode) statements.get(i)).getState()) + "); ";
						}
						else if(statements.get(i) instanceof EndNode) {
							toCheck = toCheck +";" ;
						}
						else if(!continues) {
							toCheck = toCheck +  Integer.toString(0) + "); ";
						}
						else {
							toCheck = toCheck +  Integer.toString(stateB+i+1) + "); ";
						}
					}
					toRetRoleB.set(j,toCheck);
				}
				totB.add(toRetRoleB);
			}

			totA.add(toRetRoleA);

		}

		int indexRoleA = code.indexOf("module "+roleTmp);
		int whereToAdd = code.indexOf("endmodule",indexRoleA);
		String codeToRet = code.substring(0,whereToAdd) ;
		for(String el : totA) {
			codeToRet = codeToRet + "\n" + el; 
		}

		codeToRet = codeToRet + "\n" + code.substring(whereToAdd,code.length());
		if(!contained) {
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
		}
		int stateNext = -1;
		ArrayList<Pair<Node,Integer>> statesNext = new ArrayList<Pair<Node,Integer>>();
		for(Node el : modules) {
			if(el.toPrint().equals(roleTmp)) {
				stateNext = ((ModuleNode) el).getState()+1;
			}

			for(String el2 : outRolesTmp) {
				if(el.toPrint().equals(el2) ) {
					statesNext.add(new Pair(el,((ModuleNode) el).getState()+1));
				}
			}
		}
		if(statements!=null) {

			for(Node stat : statements) {
				boolean continuesA = false;
				if(stat instanceof BranchNode) {
					continuesA = Functions.returnStringNewIndex(((BranchNode) stat).getRoleA(),index,totIndex).equals(roleTmp);
				}
				else if(stat instanceof IfThenElseNode) {
					continuesA = Functions.returnStringNewIndex(((IfThenElseNode) stat).getRole(),index,totIndex).equals(roleTmp);
				}
				else if(stat instanceof InternalActionNode) {
					continuesA = Functions.returnStringNewIndex(((InternalActionNode) stat).getRole(),index,totIndex).equals(roleTmp);
				}
				for(Node el : modules) {
					if(el.toPrint().equals(roleTmp) && continuesA) {						
						((ModuleNode) el).setState(stateNext);
						stateNext++;
					}

					for(int j=0; j<outRolesTmp.size(); j++) {
						boolean continuesB = false;
						if(stat instanceof BranchNode) {
							continuesB = Functions.returnStringNewIndex(((BranchNode) stat).getRoleA(),index+j,totIndex).equals(outRolesTmp.get(j));
						}
						else if(stat instanceof IfThenElseNode) {
							continuesB = Functions.returnStringNewIndex(((IfThenElseNode) stat).getRole(),index+j,totIndex).equals(outRolesTmp.get(j));
						}
						else if(stat instanceof InternalActionNode) {
							continuesB = Functions.returnStringNewIndex(((InternalActionNode) stat).getRole(),index+j,totIndex).equals(outRolesTmp.get(j));
						}
						if(el.toPrint().equals(outRolesTmp.get(j)) && !contained && continuesB) {
							for(Pair<Node,Integer> pair : statesNext) {
								if(pair.getFirst().toPrint().equals(outRolesTmp.get(j))) {
									((ModuleNode) el).setState(pair.getSecond());
									pair.setSecond(pair.getSecond() + 1);
								}
							}


						}
					}
				}

				if(!(stat instanceof RecNode) && !(stat instanceof EndNode)) {
					codeToRet = stat.generateCode(codeToRet,index,totIndex,modules);
				}
			}

		}
		return codeToRet;
	}

}
