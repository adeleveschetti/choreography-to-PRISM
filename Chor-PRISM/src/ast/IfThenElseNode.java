package ast;

import java.util.ArrayList;
import java.util.function.Function;

import lib.Functions;

public class IfThenElseNode implements Node {

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
		return null;
	}

	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, int state) {
		Functions funs = new Functions();
		String roleTmp = funs.changeIndex(role,index,totIndex);
		int stateModule = -1;
		for(Node el : modules) {
			if(el.toPrint().equals(roleTmp)) {
				stateModule = ((ModuleNode) el).getState();
			}
		}
		String toRet = "[] (" + roleTmp +"=" + Integer.toString(stateModule) + ")&" + Functions.returnStringNewIndex(cond,index,totIndex) + " -> ;" ;
		if(thenStat instanceof RecNode) {
			toRet = toRet.substring(0,toRet.length()-1) + "(" + roleTmp +"'=" + Integer.toString(((RecNode) thenStat).getState()) + ");";
		}
		int indexRoleA = code.indexOf("module "+roleTmp);
		int whereToAdd = code.indexOf("endmodule",indexRoleA);
		String codeToRet = code.substring(0,whereToAdd) ;
		codeToRet = codeToRet + "\n" + toRet;
		codeToRet = codeToRet + "\n" + code.substring(whereToAdd,code.length());
		if(!(thenStat instanceof RecNode)) {
			codeToRet = thenStat.generateCode(codeToRet,index,totIndex,modules,state);
		}

		toRet = "[] (" + roleTmp +"=" + Integer.toString(stateModule) + ")&!(" + Functions.returnStringNewIndex(cond,index,totIndex) + ") -> ;" ;
		if(elseStat instanceof RecNode) {
			toRet = toRet.substring(0,toRet.length()-1) + "(" + roleTmp +"'=" + Integer.toString(((RecNode) elseStat).getState()) + ");";
		}
		indexRoleA = codeToRet.indexOf("module "+roleTmp);
		whereToAdd = codeToRet.indexOf("endmodule",indexRoleA);
		String codeToRetElse = codeToRet.substring(0,whereToAdd) ;
		codeToRetElse = codeToRetElse + "\n" + toRet;
		codeToRetElse = codeToRetElse + "\n" + codeToRet.substring(whereToAdd,codeToRet.length());
		if(!(elseStat instanceof RecNode)) {
			codeToRetElse = elseStat.generateCode(codeToRetElse,index,totIndex,modules,state);
		}
		return codeToRetElse;
	}

}
