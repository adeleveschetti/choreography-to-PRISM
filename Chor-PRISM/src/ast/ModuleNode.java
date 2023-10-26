package ast;

import java.util.ArrayList;

import lib.Pair;

public class ModuleNode implements Node{

	private String name;
	private ArrayList<String> vars ;
	private int state = 0;
	private ArrayList<String> commands = new ArrayList<String>();
	private ArrayList<Pair<String,Integer>> recursions = new ArrayList<Pair<String,Integer>>();
	
	public ModuleNode(String _name, ArrayList<String> _vars) {
		name = _name;
		vars = _vars;
	}
	

	public int getValueRecursion(String rec) {
		for(Pair<String,Integer> el : recursions) {
			if(el.getFirst().equals(rec)) {
				return el.getSecond();
			}
		}
		return -1;
	}
	
	public void setValueRecursion(String rec, int state) {
		recursions.add(new Pair<String,Integer>(rec,state));
	}
	
	@Override
	public String toPrint() {
		return name;
	}
	
	public int getState() {
		return state;
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

	
}
