package ast;

import java.util.ArrayList;
import java.util.function.Function;

import lib.Functions;

public class IfThenElseNode implements Node {

	private ArrayList<String> roles;
	private ArrayList<String> conds; 
	private Node thenStat;
	private Node elseStat;

	public IfThenElseNode(ArrayList<String> r, ArrayList<String> c, Node then, Node elseS) {
		roles = r;
		conds = c;
		thenStat = then;
		elseStat = elseS;
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRole() {
		return roles.get(0);
	}

	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}

	public Node getThenStatement() {
		return thenStat;
	}

	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName, int counter) {
		Functions funs = new Functions();
		String codeToRet = "" ;
		for(int i=0; i<roles.size(); i++) {
			String roleTmp = funs.changeIndex(roles.get(i),index,totIndex);

			int stateModule = -1;
			for(Node el : modules) {
				if(el.toPrint().equals(roleTmp)) {
					stateModule = ((ModuleNode) el).getState();
				}
			}
			String toRet = "[] (" + roleTmp +"=" + Integer.toString(stateModule) + ")&" + Functions.returnStringNewIndex(conds.get(i),index,totIndex) + " -> ;" ;
			if(thenStat instanceof RecNode) {
				if(((RecNode)thenStat).getState()!=-1) {
					toRet = toRet.substring(0,toRet.length()-1) + "(" + roleTmp +"'=" + Integer.toString(((RecNode) thenStat).getState()) + ");";
				}
				else {
					toRet = toRet.substring(0,toRet.length()-1) + "(" + roleTmp +"'=" + ((RecNode) thenStat).getName() + ");";			}
			}
			else if(thenStat instanceof EndNode) {
				toRet = toRet.substring(0,toRet.length()-1) + "1 : (" + roleTmp +"'=" + Integer.toString(stateModule) + ");";
			}
			else if(thenStat instanceof IfThenElseNode) {
				toRet = toRet.substring(0,toRet.length()-1) + "(" + roleTmp +"'=" + Integer.toString(stateModule+1) + ");";
			}
			else if(thenStat instanceof BranchNode ) {
				toRet = toRet + "\n[] (" + roleTmp +"=" + Integer.toString(stateModule) + ")&" + Functions.returnStringNewIndex(conds.get(i),index,totIndex) + " -> ;";
			}

			for(Node el : modules) {
				if(el.toPrint().equals(roleTmp) && !(elseStat instanceof EndNode)) {
					((ModuleNode) el).setState(stateModule+1);
				}
			}

			int indexRoleA = code.indexOf("module "+roleTmp);
			int whereToAdd = code.indexOf("endmodule",indexRoleA);

			codeToRet = code.substring(0,whereToAdd) ;
			codeToRet = codeToRet + "\n" + toRet;
			codeToRet = codeToRet + "\n" + code.substring(whereToAdd,code.length());

			if(!(thenStat instanceof RecNode) && !(thenStat instanceof EndNode)  ) {
				codeToRet = thenStat.generateCode(codeToRet,index,totIndex,modules,labels,protocolName,counter);
			}
			/*if(thenStat instanceof EndNode && ((EndNode) thenStat).getRoles()!=null) {
			for(Node el : ((EndNode) thenStat).getRoles()) {
				String name = el.toPrint();
				name = funs.changeIndex(name,index,totIndex);
				if(!name.equals(roleTmp)) {
					for(Node el2 : modules) {
						if(el2.toPrint().equals(name)) {
							stateModule = ((ModuleNode) el2).getState();
						}
					}
					System.out.println(roleTmp + " " + name);
					String toRetB = "[] (" + name +"=" + Integer.toString(stateModule) + ") -> 1 : (" + name +"'=" + Integer.toString(stateModule) + ");" ;
					System.out.println(toRetB);
					int indexRoleB = codeToRet.indexOf("module "+name);
					int whereToAddB = codeToRet.indexOf("endmodule",indexRoleB);
					String codeToRetB = codeToRet.substring(0,whereToAddB) ;
					codeToRetB = codeToRetB + "\n" + toRetB;
					codeToRetB = codeToRetB + "\n" + codeToRet.substring(whereToAdd,codeToRet.length());
					codeToRet = codeToRetB;
				}

			}
		}*/
			if(elseStat!=null) {
				int newStateModule = -1;
				for(Node el : modules) {
					if(el.toPrint().equals(roleTmp)) {
						newStateModule = ((ModuleNode) el).getState();
					}
				}
				toRet = "[] (" + roleTmp +"=" + Integer.toString(stateModule) + ")&!(" + Functions.returnStringNewIndex(conds.get(i),index,totIndex) + ") -> ;" ;

				if(elseStat instanceof RecNode) {
					toRet = toRet.substring(0,toRet.length()-1) + "1 : (" + roleTmp +"'=" + ((RecNode) elseStat).getName() + ");";
				}
				else if(elseStat instanceof EndNode) {
					toRet = toRet.substring(0,toRet.length()-1) + "1 : (" + roleTmp +"'=" + Integer.toString(newStateModule) + ");";
				}
				else if(!(elseStat instanceof InternalActionNode)) {
					toRet = toRet.substring(0,toRet.length()-1) + "1 : (" + roleTmp +"'=" + Integer.toString(newStateModule+1) + "); ";
					for(Node el : modules) {
						if(el.toPrint().equals(roleTmp)) {
							((ModuleNode) el).setState(((ModuleNode) el).getState()+1);
						}
					}
				}

				indexRoleA = codeToRet.indexOf("module "+roleTmp);
				whereToAdd = codeToRet.indexOf("endmodule",indexRoleA);
				String codeToRetElse = codeToRet.substring(0,whereToAdd) ;
				codeToRetElse = codeToRetElse + "\n" + toRet;
				codeToRetElse = codeToRetElse + "\n" + codeToRet.substring(whereToAdd,codeToRet.length());
				if(!(elseStat instanceof RecNode) && !(elseStat instanceof EndNode)) {
					codeToRetElse = elseStat.generateCode(codeToRetElse,index,totIndex,modules,labels,protocolName,1);
				}
				codeToRet = codeToRetElse;
			}
			code = codeToRet;
		}
		return codeToRet;

	}

}
