package ast;

import java.util.ArrayList;
import java.util.Iterator;

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
		return "internal";
	}

	public String getRole() {
		return role;
	}
	
	@Override
	public ArrayList<String> getRoles(){
		ArrayList<String> roles = new ArrayList<String>();
		roles.add(role);
		return roles;
	}
	
	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels) {
		Functions funs = new Functions();
		String roleTmp = Functions.changeIndex(role,index,maxIndex);
		
		
		String toRet = "";
		int stateRole = 0;
		int iA = -1;
		for(int i=0; i<mods.size(); i++) {
			if(mods.get(i).toPrint().equals(roleTmp)) {
				iA = i;
			}
		}
		
		
		String updatesNew = updates.generateCode(mods,index,maxIndex,isCtmc,labels);
		updatesNew = Functions.returnStringNewIndex(updatesNew,index,maxIndex);
		if(!updatesNew.equals(" ")) {
			updatesNew = updatesNew.substring(0,updatesNew.length()-3) + "&";
		}
		int upState = -1;
		if(statement instanceof RecNode) {
			upState = 0; //TOBECHANGED
		}
		else if(statement instanceof EndNode) {
			upState = ((ModuleNode) mods.get(iA)).getState();
		}
		else {
			upState = ((ModuleNode) mods.get(iA)).getState()+1;
		}
		updatesNew = updatesNew + "(" + roleTmp +"'=" + upState + "); ";
		
		String statFin = "";
		for(String el : ((ModuleNode) mods.get(iA)).getCommands()) {
			if(el.contains("IFTE")) {
				statFin = el.substring(0,el.indexOf("IFTE"));
			}
		}
		((ModuleNode) mods.get(iA)).addCommand(statFin + rate + " : " + updatesNew);
		
		Iterator<String> itr = ((ModuleNode) mods.get(iA)).getCommands().iterator();
		while (itr.hasNext()) {
			String el = itr.next();
			if(el.contains("IFTE") && el.substring(0,el.indexOf("IFTE")).equals(statFin)) {
				itr.remove();
			}
		}

		if(!(statement instanceof EndNode)) {
			((ModuleNode) mods.get(iA)).setState();
		}
		statement.generateCode(mods,index,maxIndex,isCtmc,labels);
		
		return null;
	}

}
