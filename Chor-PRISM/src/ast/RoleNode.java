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
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules) {
		return code + name;
	}
	
}
