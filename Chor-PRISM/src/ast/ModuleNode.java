package ast;

import java.util.ArrayList;

public class ModuleNode implements Node{

	private String name;
	private ArrayList<String> vars ;
	private ArrayList<Node> statements = null;
	private int state = 0;

	public ModuleNode(String _name, ArrayList<String> _vars) {
		name = _name;
		vars = _vars;
	}
	
	public int getState() {
		return state;
	}
	
	public void setState(int _state) {
		state = _state;
	}

	@Override
	public String toPrint() {
		return name;
	}

	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules) {
		String toRet = "module " + name + "\n";
		if(vars!=null) {
			for(String el : vars) {
				toRet = toRet + el + "\n";
			}
		}
		toRet = toRet +  name + " : [0..10] init 0;\n";
		toRet = toRet + "\nendmodule\n\n";
		return code + "\n" + toRet;
	}

}
