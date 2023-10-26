package ast;

import java.util.ArrayList;
import java.util.Iterator;

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
	public ArrayList<String> getRoles(){
		
		return roles;
	}

	@Override
	public String toPrint() {
		return "if-then-else";
	}

	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels) {
		ArrayList<String> rolesTmp = new ArrayList<String>();
		ArrayList<String> condsTmp = new ArrayList<String>();

		for(String el : roles) {
			rolesTmp.add(Functions.changeIndex(el,index,maxIndex));
		}
		for(String el : conds) {
			condsTmp.add(Functions.returnStringNewIndex(el,index,maxIndex));
		}
		for(int i=0; i<roles.size(); i++) {
			for(Node el2 : mods) {
				if(el2.toPrint().equals(rolesTmp.get(i))) {
					int state = ((ModuleNode) el2).getState();
					String stat = "";
					boolean ifte = false;
					for(String comms : ((ModuleNode) el2).getCommands()) {
						if(comms.contains("IFTE")) {
							stat = comms.substring(0,comms.indexOf(" -> IFTE"));
							ifte = true;
						}
					}
					String stat2 = "";
					boolean ifte2 = false;
					for(String comms : ((ModuleNode) el2).getCommands()) {
						if(comms.contains("IFTE")) {
							stat2 = comms.substring(0,comms.indexOf(" -> IFTE"));
							ifte2 = true;
						}
					}

					if(!ifte) {
						stat = "[] (" + rolesTmp.get(i) + "=" +  state + ")" ;
					}
					String statTmp = stat;

					stat = stat + "&"+condsTmp.get(i) + " -> " ;

					if(thenStat instanceof RecNode) {
						stat = stat + "(" + rolesTmp.get(i) + "'=" + 0 + ");"; //TOBECHANGED
					}
					else if(thenStat instanceof EndNode) {
						stat = stat + "(" + rolesTmp.get(i) + "'=" + state + ");";
					}
					else {
						stat = stat + "IFTE";
					}

					Iterator<String> itr = ((ModuleNode) el2).getCommands().iterator();
					while (itr.hasNext()) {
						String comms = itr.next();
						if(comms.contains("IFTE") && ifte && comms.substring(0, comms.indexOf(" -> IFTE")).equals(statTmp)) {
							itr.remove();
						}
					}

					((ModuleNode) el2).addCommand(stat);
					thenStat.generateCode(mods,index,maxIndex,isCtmc,labels);
					
					if(!ifte2) {
						stat2 = "[] (" + rolesTmp.get(i) + "=" +  state + ")" ;
					}
					String statTmp2 = stat2;
					stat2 = stat2 + "&!"+condsTmp.get(i) + " -> " ;

					if(elseStat instanceof RecNode) {
						stat2 = stat2 + "1 : (" + rolesTmp.get(i) + "'=" + 0 + ");"; //TOBECHANGED
					}
					else if(elseStat instanceof EndNode) {
						stat2 = stat2 + "1 : (" + rolesTmp.get(i) + "'=" + state + ");";
					}
					else {
						stat2 = stat2 + "IFTE";
					}
										
					Iterator<String> itr2 = ((ModuleNode) el2).getCommands().iterator();
					while (itr2.hasNext()) {
						String comms = itr2.next();
						if(comms.contains("IFTE") && ifte2 && comms.substring(0, comms.indexOf(" -> IFTE")).equals(statTmp2)) {
							itr.remove();
						}
					}
					((ModuleNode) el2).addCommand(stat2);
					elseStat.generateCode(mods,index,maxIndex,isCtmc,labels);

				}
			}
		}

		return null;
	}

}
