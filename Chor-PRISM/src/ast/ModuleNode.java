package ast;

import java.util.ArrayList;

public class ModuleNode implements Node{

	private String name;
	private ArrayList<String> vars ;
	private int state = 0;
	private ArrayList<String> commands = new ArrayList<String>();
	
	public ModuleNode(String _name, ArrayList<String> _vars) {
		name = _name;
		vars = _vars;
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
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels) {
		return null;
	}


	@Override
	public ArrayList<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
