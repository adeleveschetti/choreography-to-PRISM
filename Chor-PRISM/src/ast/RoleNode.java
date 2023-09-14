package ast;

import java.util.ArrayList;

public class RoleNode implements Node{

	private String name;
	
	public RoleNode(String _name) {
		name = _name;
	}

	@Override
	public String toPrint() {
		return name;
	}

	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName, int counter) {
		return code + name;
	}
	
	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}
	
}
