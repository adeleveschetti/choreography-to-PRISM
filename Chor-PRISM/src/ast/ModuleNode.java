package ast;

import java.util.ArrayList;

public class ModuleNode implements Node{

	private String name;
	private ArrayList<String> vars ;
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
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels) {
		String toRet = "module " + name + "\n";
		if(vars!=null) {
			for(String el : vars) {
				toRet = toRet + el + "\n";
			}
		}
		toRet = toRet + "\nendmodule\n\n";
		return code + "\n" + toRet;
	}

	public String addStateVariable(String code) {
		String toFind = "module " + name + "\n";
		int index = code.indexOf(toFind)+toFind.length();
		String toRet = code.substring(0,index) + name + " : [0.."+(state+1)+"] init 0;\n" + code.substring(index+1,code.length());
		return toRet;
	}
}
