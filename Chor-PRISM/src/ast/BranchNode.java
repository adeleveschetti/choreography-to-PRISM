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

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return "branch";
	}

	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels) {

		Functions funs = new Functions();
		String roleTmp = funs.changeIndex(role,index,maxIndex);
		ArrayList<String> outRolesTmp = new ArrayList<String>();
		int varAdd = 0;
		for(String el : outRole) {
			outRolesTmp.add(Functions.changeIndex(el,index,maxIndex));
		}

		int iA = -1;
		for(int i=0; i<mods.size(); i++) {
			if(mods.get(i).toPrint().equals(roleTmp)) {
				iA = i;
			}
		}
		String statementA = null;
		String label = "";
		ArrayList<String> stats = new ArrayList<String>();
		for(String el : outRolesTmp) {
			stats.add(null);
		}
		for(int k=0; k<rates.size(); k++) {
			boolean firstLabel = false;
			boolean sameRole = false;
			for(String el : outRolesTmp) {
				if(el.equals(roleTmp)) {
					sameRole = true;
				}
			}

			if(!sameRole && (!isCtmc && k==0) || isCtmc) {
				while(!firstLabel) {
					String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
					StringBuilder salt = new StringBuilder();
					Random rnd = new Random();
					int size = 5;
					while (salt.length() < size) { // length of the random string.
						int indexLabel = (int) (rnd.nextFloat() * SALTCHARS.length());
						salt.append(SALTCHARS.charAt(indexLabel));
					}
					label = salt.toString();					
					if(labels==null) {
						labels = new ArrayList<String>();
						labels.add(label);
						firstLabel = true;
					}
					else {
						if(!labels.contains(label)) {
							firstLabel = true;
							labels.add(label);
						}
					}
				}
			}
			int stateA = ((ModuleNode) mods.get(iA)).getState();
			if(isCtmc) {
				statementA = null;
			}
			int indexRate = rates.get(k).indexOf("*");
			String rateA = funs.changeIndex(rates.get(k).substring(0,indexRate),index,maxIndex);
			boolean ifte = false;
			for(String el : ((ModuleNode) mods.get(iA)).getCommands()) {
				if(el.contains("IFTE")) {
					statementA = el.substring(0,el.indexOf("IFTE"));
					ifte = true;
				}
			}

			if(statementA == null) {
				statementA = "[" + label + "] (" + roleTmp + "=" + stateA + ") -> " ;
			}
			else if(!ifte){ 
				statementA = statementA  + " + ";
			}

			statementA = statementA + rateA + " : ";

			String upA = "";
			String genUpdates = updates.get(k).generateCode(mods,index,maxIndex,isCtmc,labels);
			if(!genUpdates.equals(" ")) {
				int indexUp = genUpdates.indexOf("&&");
				if(!genUpdates.substring(0,indexUp).equals(" ")) {
					upA = Functions.returnStringNewIndex(genUpdates.substring(0,indexUp),index,maxIndex)+"&";

				}
			}
			boolean roleAContained = false;
			for(String tmp : statements.get(k).getRoles()) {
				if(roleTmp.equals(funs.changeIndex(tmp,index,maxIndex))) {
					roleAContained = true;
				}
			}
			if(statements.get(k) instanceof RecNode){
				stateA = 0; // TO BE CHANGED
			}
			else if( !(statements.get(k) instanceof EndNode) && roleAContained) {
				stateA = stateA + k + 1;
			}
			statementA = statementA + upA + "(" + roleTmp + "'=" + stateA +")";

		
			
			for(int kk=0; kk<outRolesTmp.size(); kk++) {
				if(!sameRole) {
					int iB = -1;
					for(int i=0; i<mods.size(); i++) {
						if(mods.get(i).toPrint().equals(outRolesTmp.get(kk))) {
							iB = i;
						}
					}

					int stateB = ((ModuleNode) mods.get(iB)).getState();

					int indexRateB = rates.get(0).indexOf("*");
					String rateB = funs.changeIndex(rates.get(k).substring(indexRate+1,rates.get(k).length()),index,maxIndex);
					
					String statementB = stats.get(kk);
					boolean ifteB = false;
					for(String el2 : ((ModuleNode) mods.get(iA)).getCommands()) {
						if(el2.contains("IFTE")) {
							statementB = el2.substring(0,el2.indexOf("IFTE"));
							ifteB = true;
						}
					}

					if(statementB == null || isCtmc) {
						statementB = "[" + label + "] (" + outRolesTmp.get(kk) + "=" + ((ModuleNode) mods.get(iB)).getState() + ") -> ";
					}
					else { 
						statementB = statementB  + " + ";
					}
					statementB = statementB + rateB + " : ";
					String upB = "";

					if(!genUpdates.equals(" ")) {
						int indexUp = genUpdates.indexOf("&&");
						if(!genUpdates.substring(indexUp+2,genUpdates.length()).equals(" ")) {
							upB = Functions.returnStringNewIndex(genUpdates.substring(indexUp+2,genUpdates.length()),index,maxIndex)+"&";
						}
					} 
					boolean roleBContained = false;
					for(String tmp : statements.get(k).getRoles()) {
						if(outRolesTmp.get(kk).equals(funs.changeIndex(tmp,index,maxIndex))) {
							roleBContained = true;
						}
					}
					if(statements.get(k) instanceof RecNode){
						stateB = 0; // TO BE CHANGED
					}
					else if(!(statements.get(k) instanceof EndNode) && roleBContained) {
						stateB = stateB + k + 1;
					}

					statementB = statementB + upB + "(" + outRolesTmp.get(kk) + "'=" + stateB +")";

					stats.set(kk,statementB);
					
					if(isCtmc || k==rates.size()-1) {
						((ModuleNode) mods.get(iB)).addCommand(statementB+";");
					}
					for(String el2 : ((ModuleNode) mods.get(iB)).getCommands()) {
						if(el2.contains("IFTE")) {
							((ModuleNode) mods.get(iB)).getCommands().remove(el2);
							break;
						}
					}
					
				}
			}
			if(isCtmc || k==rates.size()-1) {
				((ModuleNode) mods.get(iA)).addCommand(statementA+";");
			}
			for(String el : ((ModuleNode) mods.get(iA)).getCommands()) {
				if(el.contains("IFTE")) {
					((ModuleNode) mods.get(iA)).getCommands().remove(el);
					break;
				}
			}
		}
		for(int k=0; k<statements.size(); k++) {
			if(!(statements.get(k) instanceof EndNode) && statements.get(k).getRoles().contains(role)){
				((ModuleNode) mods.get(iA)).setState();
			}
			
			for(String el : outRole) {
				if(!role.equals(el) && !(statements.get(k) instanceof EndNode) && statements.get(k).getRoles().contains(el)) {
					int iB = -1;
					for(int i=0; i<mods.size(); i++) {
						if(mods.get(i).toPrint().equals(funs.changeIndex(el,index,maxIndex))) {
							iB = i;
						}
					}
					((ModuleNode) mods.get(iB)).setState();
				}
			}
			statements.get(k).generateCode(mods,index,maxIndex,isCtmc,labels);
		}

		return null;
	}

	@Override
	public ArrayList<String> getRoles() {
		ArrayList<String> roles = outRole;
		roles.add(role);
		return roles;
	}
}