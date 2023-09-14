package ast;

import java.util.ArrayList;

import lib.Functions;

public class InternalActionNode implements Node{

	private String rate;
	private String role;
	private Node updates;
	private Node statement;

	public InternalActionNode(String _rate, String _role, Node _upds, Node _stat) {
		rate = _rate;
		role = _role;
		updates = _upds;
		statement = _stat;
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRole() {
		return role;
	}
	
	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}
	
	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName, int counter) {
		Functions funs = new Functions();
		String roleTmp = Functions.changeIndex(role,index,totIndex);
		
		
		String toRet = "";
		String toFind = "-> ;";
		int stateRole = 0;
		for(Node el : modules) {
			if(el.toPrint().equals(roleTmp)) {
				stateRole = ((ModuleNode) el).getState();
			}
		}
		
		String updatesNew = updates.generateCode(code,index,totIndex,modules,labels,protocolName,counter);
		updatesNew = Functions.returnStringNewIndex(updatesNew,index,totIndex);
		if(!updatesNew.equals("")) {
			updatesNew = updatesNew + "&";
		}
		if(statement instanceof RecNode) {
			if(((RecNode) statement).getState()!=-1) {
				updatesNew = updatesNew + "(" + roleTmp +"'=" + Integer.toString(((RecNode) statement).getState()) + "); " ;
			}
			else {
				updatesNew = updatesNew + "(" + roleTmp +"'=" + ((RecNode) statement).getName() + "); " ;
			}
		}
		else {
			updatesNew = updatesNew + "(" + roleTmp +"'=" + Integer.toString(stateRole+1) + "); " ;
			for(Node el : modules) {
				if(el.toPrint().equals(roleTmp)) {
					((ModuleNode) el).setState(stateRole+1);
				}
			}
		}
		int indexStart = code.indexOf("module " + roleTmp);
		int indexAdd = code.indexOf("endmodule",indexStart);
		if(code.substring(indexStart,indexAdd).contains(toFind)) {
			indexAdd = code.indexOf(toFind,indexStart);
			
			toRet = code.substring(0,indexAdd+toFind.length()-1) + Functions.returnStringNewIndex(rate,index,totIndex) + " : " + updatesNew + code.substring(indexAdd+toFind.length(),code.length()) ;		

		}
		else {
			toRet = code.substring(0,indexAdd) + Functions.returnStringNewIndex(rate,index,totIndex) + " : " + updatesNew + code.substring(indexAdd,code.length()) ;		

		}
		
		if(!(statement instanceof RecNode)) {
			toRet = statement.generateCode(toRet,index,totIndex,modules,labels,protocolName,counter);
		}
		return toRet;
	}

}
