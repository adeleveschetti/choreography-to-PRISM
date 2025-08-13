package ast;

import java.util.ArrayList;

import lib.*;

public class ModuleNode implements Node{

	private String name;
	private ArrayList<String> vars ;
	private int state = 0;
	private ArrayList<String> commands = new ArrayList<String>();
	private ArrayList<Pair<String,ArrayList<Integer>>> recursions = new ArrayList<Pair<String,ArrayList<Integer>>>();
	private ArrayList<Pair<String,ArrayList<Integer>>> notUsedRecursions = new ArrayList<Pair<String,ArrayList<Integer>>>();
	private int lastState = -1;
	private ArrayList<Pair<String,Integer>> varsValues = new ArrayList<Pair<String,Integer>>();
	private ArrayList<String> varsNames = new ArrayList<String>();
	public ArrayList<Pair<String,Integer>> intVars = new ArrayList<>();


	public ModuleNode(String _name, ArrayList<String> _vars) {
		name = _name;
		vars = _vars;
		String str = "init";
		if(vars!=null) {

			for (String el : vars) { // TODO: here we can add the check for unbounded variables
				String[] split = el.split(" ");
				String varName = split[0];
				int index = el.indexOf(str) + str.length() + 1;
				char init = el.charAt(index);
				if (Character.isLetter(init)) {
					if (String.valueOf(init).equals("t")) {
						init = '1';
					} else {
						init = '0';
					}
				}
				if (Character.isDigit(el.charAt(index + 1))) {
					String newStr = new StringBuilder().append(init).append(el.charAt(index + 1)).toString();
					varsValues.add(new Pair(varName, Integer.parseInt(String.valueOf(newStr))));
				}
				else if(!el.contains("block") && !el.contains("blockchain") && !el.contains("list") && !el.contains("bool")) {
					varsValues.add(new Pair(varName, Integer.parseInt(String.valueOf(init))));
				}

				//varsValues.add(new Pair(varName,0));
			}

		}
	}


	public ArrayList<Pair<String,Integer>> getIntVars(){
		return varsValues;
	}
	public int getMaxState() {
		int maxState = 0;
		for(Pair<String,ArrayList<Integer>> el : recursions) {
			if(el.getSecond().get(el.getSecond().size()-1)>=maxState) {
				maxState = el.getSecond().get(el.getSecond().size()-1);
			}
		}
		if(state>=maxState) {
			maxState = state;
		}
		return maxState;
	}

	public int getLastState() {
		return lastState;
	}
	
	public void setLastState(int val) {
		lastState = val;
	}

	public int getValueRecursion(String rec) {

		for(Pair<String,ArrayList<Integer>> el : recursions) {
			if(el.getFirst().equals(rec)) {
				return el.getSecond().get(0);
			}
		}
		return -1;
	}

	public int getMaxValueRecursion(String rec) {
		int max = -1;
		for(Pair<String,ArrayList<Integer>> el : recursions) {
			if(el.getFirst().equals(rec)) {
				for(int val : el.getSecond()) {
					if(val>=max) {
						max = val;
					}
				}
			}
		}
		return max;
	}

	public ArrayList<Integer> getNewStates(String rec){
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec)) {
				return el.getSecond();
			}
		}
		return null;
	}

	public int getMaxNewStates(String rec) {
		int max = -1;
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec)) {
				for(int val : el.getSecond()) {
					if(val>=max) {
						max = val;
					}
				}
			}
		}
		return max;
	}

	public int getMinNewStates(String rec) {
		int min = -1;
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec)) {
				min = el.getSecond().get(0);
				for(int val : el.getSecond()) {
					if(val<=min) {
						min = val;
					}
				}
			}
		}
		return min;
	}


	public int getNewState(String rec) {
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec) ) {
				if(el.getSecond().size()>0) {
					return el.getSecond().get(0);
				}
				else {
					return -1;
				}
			}
		}
		return -1;
	}

	public void removeNewState(String rec) {
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec)) {
				el.getSecond().remove(0);
			}
		}
	}

	public void removeState(String rec, int _state) {
		int indexState = -1;
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec)) {
				for(int i=0; i<el.getSecond().size(); i++) {
					if(el.getSecond().get(i)==_state) {
						indexState = i;
					}
				}
			}
		}
		if(indexState!=-1) {
			for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
				if(el.getFirst().equals(rec)) {
					el.getSecond().remove(indexState);
				}
			}
		}
	}

	public void setNewState(String rec, int state) {
		boolean found = false;
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec)) {
				found = true;
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				if(el.getSecond()==null) {
					tmp.add(state);
					el.setSecond(tmp) ;
				}
				else {

					if(!el.getSecond().contains(state)) {
						el.getSecond().add(state);
					}
				}
			}
		}
		if(!found) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(state);
			Pair<String,ArrayList<Integer>> pair = new Pair<String,ArrayList<Integer>>(rec,tmp);
			notUsedRecursions.add(pair);
		}
	}
	public void setNewStateIndex(String rec, int state, int index) {
		boolean found = false;
		for(Pair<String,ArrayList<Integer>> el : notUsedRecursions) {
			if(el.getFirst().equals(rec)) {
				found = true;
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				if(el.getSecond()==null) {
					tmp.add(index,state);
					el.setSecond(tmp) ;
				}
				else {
					if(!el.getSecond().contains(state)) {
						el.getSecond().add(index,state);
					}
				}
			}
		}
		if(!found) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(index,state);
			Pair<String,ArrayList<Integer>> pair = new Pair<String,ArrayList<Integer>>(rec,tmp);
			notUsedRecursions.add(pair);
		}
	}

	public void setValueRecursion(String rec, int state) {
		boolean found = false;
		for(Pair<String,ArrayList<Integer>> el : recursions) {
			if(el.getFirst().equals(rec)) {
				found = true;
				ArrayList<Integer> tmp = new ArrayList<Integer>();
				if(el.getSecond()==null) {
					tmp.add(state);
					el.setSecond(tmp) ;
				}
				else {
					el.getSecond().add(state);
				}
			}
		}
		if(!found) {
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(state);
			Pair<String,ArrayList<Integer>> pair = new Pair<String,ArrayList<Integer>>(rec,tmp);
			recursions.add(pair);
		}
	}

	@Override
	public String toPrint() {
		return name;
	}

	public int getMaxFinState() {
		int maxState = 0;
		for(Pair<String,ArrayList<Integer>> pair : recursions) {
			for(int i : pair.getSecond()) {
				if(i>=maxState) {
					maxState = i;
				}
			}
		}
		for(Pair<String,ArrayList<Integer>> pair : notUsedRecursions) {
			for(int i : pair.getSecond()) {
				if(i>=maxState) {
					maxState = i;
				}
			}
		}
		return maxState;
	}

	public void setState() {
		state = state + 1;
	}

	public void addCommand(String stat) {
		commands.add(stat);
	}

	public ArrayList<String> getVars(){
		return vars;
	}

	public ArrayList<String> getCommands(){
		return commands;
	}



	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		return null;
	}


	@Override
	public ArrayList<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
	public Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, ArrayList<Pair<String,Integer>> lastUpdate, ArrayList<Pair<String,String>> consts){

		ArrayList<Pair<String, Integer>> firstArrayInit = states.get(0).getSecond();
		ArrayList<Pair<String, Integer>> firstArray = states.get(0).getThird();
		for(Pair el : varsValues){
			firstArray.add(el);
		}
		states.set(0,new Triplet("0",firstArrayInit,firstArray));

		return new Pair(recValues,states);
    }

    @Override
	public Matrix generateMarkovChain(ArrayList<Node> mods) {
		return null;
	}

	@Override
	public ArrayList<Pair<String,ArrayList<String>>> generatePrismCode(ArrayList<Pair<String,ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String,ArrayList<State>>> states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, Pair<String,State> lastUpdate, ArrayList<Pair<String,String>> consts){
		return code;
	}

}
