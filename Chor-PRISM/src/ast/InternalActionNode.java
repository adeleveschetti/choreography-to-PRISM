package ast;

import java.util.ArrayList;
import java.util.Iterator;

import lib.*;

public class InternalActionNode implements Node {


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
	public ArrayList<String> getRoles() {
		ArrayList<String> roles = new ArrayList<String>();
		roles.add(role);
		return roles;
	}

    @Override
	public Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, ArrayList<Pair<String,Integer>> lastUpdate, ArrayList<Pair<String,String>> consts){
		ArrayList<Pair<String, Integer>> newStates = new ArrayList<Pair<String, Integer>>();
		String[] ups = updates.toPrint().split("&");

		for (int k = 0; k < ups.length; k++) {
			String[] vars = ups[k].split("'="); // TODO: add vars update of the form "t'=t+1"
			if (Character.isDigit(vars[1].substring(0, vars[1].length() - 1).charAt(0))) {
				newStates.add(new Pair(vars[0].substring(1, vars[0].length()), Integer.parseInt(vars[1].substring(0, vars[1].length() - 1))));
			} else {
				if (String.valueOf(vars[1].substring(0, vars[1].length() - 1).charAt(0)).equals("t")) {
					newStates.add(new Pair(vars[0].substring(1, vars[0].length()), 1));
				} else {
					newStates.add(new Pair(vars[0].substring(1, vars[0].length()), 0));
				}
			}
		}
		for (Pair<String, Integer> el : lastUpdate) { // TODO: add parametrization of roles
			Pair<String, Integer> toAdd = null;

			if (el.getFirst().equals(role)) {

				if (statement instanceof RecNode) {

					for (Pair<String, ArrayList<Pair<String, Integer>>> pair : recValues) {
						if (pair.getFirst().equals(statement.toPrint())) {
							for (Pair pair2 : pair.getSecond()) {
								if (pair2.getFirst().equals(role)) {
									toAdd = new Pair(role, pair2.getSecond());
									newStates.add(toAdd);
								}
							}
						}
					}
				} else if (statement instanceof EndNode) { // TODO: to fix
					toAdd = new Pair(role, 100);

					newStates.add(toAdd);
				} else {
					newStates.add(toAdd);
				}


			} else if (el.getFirst().equals(role)) {
				if (statement instanceof RecNode) {
					for (Pair<String, ArrayList<Pair<String, Integer>>> pair : recValues) {
						if (pair.getFirst().equals(statement.toPrint())) {
							for (Pair pair2 : pair.getSecond()) {
								if (pair2.getFirst().equals(role)) {
									toAdd = new Pair(role, pair2.getSecond());
									newStates.add(toAdd);
								}
							}
						}
					}
				} else if (statement instanceof EndNode) { // TODO: to fix
					toAdd = new Pair(role, 100);
					newStates.add(toAdd);
				} else {
					toAdd = new Pair(role, el.getSecond()  + 1);

					newStates.add(toAdd);
				}

			}

		}

		ArrayList<Pair<String, Integer>> ordered = new ArrayList<Pair<String, Integer>>();
		for (int kk = 0; kk < lastUpdate.size(); kk++) {
			ordered.add(null);
		}
		for (int ii = 0; ii < lastUpdate.size(); ii++) {

			boolean found = false;
			for (int jj = 0; jj < newStates.size(); jj++) {
				if (newStates.get(jj).getFirst().equals(lastUpdate.get(ii).getFirst())) {
					ordered.set(ii, newStates.get(jj));
					found = true;
				}
			}
			if (!found) {
				ordered.set(ii, lastUpdate.get(ii));
			}
		}
		Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet(rate, lastUpdate, ordered);

		ListPair newState = new ListPair(toAdd);
		return new Pair(recValues,newState);
	}


    @Override
	public Matrix generateMarkovChain(ArrayList<Node> mods) {
		return null;
	}

	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		Functions funs = new Functions();
		String roleTmp = Functions.changeIndex(role, index, maxIndex);


		String toRet = "";
		int stateRole = 0;
		int iA = -1;
		for (int i = 0; i < mods.size(); i++) {
			if (mods.get(i).toPrint().equals(roleTmp)) {
				iA = i;
			}
		}


		String updatesNew = updates.generateCode(mods, index, maxIndex, isCtmc, labels, prot);
		updatesNew = Functions.returnStringNewIndex(updatesNew, index, maxIndex);
		if (!updatesNew.equals(" ")) {
			updatesNew = updatesNew.substring(0, updatesNew.length() - 3) + "&";
		}

		int upState = ((ModuleNode) mods.get(iA)).getValueRecursion(prot);

		if (upState == -1) {
			upState = ((ModuleNode) mods.get(iA)).getMaxState();
			((ModuleNode) mods.get(iA)).setValueRecursion(prot, upState);
		} else {
			/*if(((ModuleNode) mods.get(iA)).getMaxValueRecursion(prot)<=((ModuleNode) mods.get(iA)).getNewState(prot) && ((ModuleNode) mods.get(iA)).getNewState(prot)!=-1) {
				if(((ModuleNode) mods.get(iA)).getNewStates(prot)!=null) {
					System.out.println("internal ======");

					for(int kk : ((ModuleNode) mods.get(iA)).getNewStates(prot)) {
						System.out.print(kk + " ");
					}
					System.out.println("");
					System.out.println("======");

				}				
				upState = ((ModuleNode) mods.get(iA)).getNewState(prot);
				((ModuleNode) mods.get(iA)).setValueRecursion(prot,upState);
				((ModuleNode) mods.get(iA)).removeNewState(prot);

			}
			else {
				upState = ((ModuleNode) mods.get(iA)).getMaxValueRecursion(prot);
			}*/
			upState = ((ModuleNode) mods.get(iA)).getMaxNewStates(prot);

		}
		upState = upState + 1;
		if (statement instanceof RecNode) {
			int stateRec = ((ModuleNode) mods.get(iA)).getValueRecursion(statement.toPrint());

			if (stateRec == -1) {

				((ModuleNode) mods.get(iA)).setValueRecursion(statement.toPrint(), upState + 1);
				upState = upState + 1;
			} else {
				upState = stateRec;
			}

		}
		updatesNew = updatesNew + "(" + roleTmp + "'=" + upState + "); ";
		String statFin = "";
		for (String el : ((ModuleNode) mods.get(iA)).getCommands()) {
			if (el.contains("IFTE")) {
				statFin = el.substring(0, el.indexOf("IFTE"));
			}
		}
		((ModuleNode) mods.get(iA)).addCommand(statFin + rate + " : " + updatesNew);

		Iterator<String> itr = ((ModuleNode) mods.get(iA)).getCommands().iterator();
		while (itr.hasNext()) {
			String el = itr.next();
			if (el.contains("IFTE") && el.substring(0, el.indexOf("IFTE")).equals(statFin)) {
				itr.remove();
			}
		}
		if (!(statement instanceof EndNode)) {
			((ModuleNode) mods.get(iA)).setNewStateIndex(prot, upState, 0);
		}
		statement.generateCode(mods, index, maxIndex, isCtmc, labels, prot);
		return null;
	}

}
