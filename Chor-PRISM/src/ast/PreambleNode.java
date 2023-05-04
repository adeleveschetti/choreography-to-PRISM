package ast;

import java.util.ArrayList;

public class PreambleNode implements Node {

	private ArrayList<String> vars ;
	
	public PreambleNode(ArrayList<String> _vars) {
		vars = new ArrayList<String>();
		for(String el : _vars) {
			vars.add(el);
		}
	}
	
	@Override
	public String toPrint() {
		String toRet = "";
		for(String el : vars) {
			toRet = toRet + el.substring(1,el.length()-1) + "\n";
		}
		return toRet;
	}

	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules) {
		for(String el : vars) {
			code = code + el.substring(1,el.length()-1) + "\n";
		}
		return code;
	}

}
