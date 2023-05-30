package ast;

import java.util.ArrayList;

public class RecNode implements Node{

	String name;
	int state = 0 ;
	
	public RecNode(String _name) {
		name = _name ;
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
		return code + name;
	}

}
