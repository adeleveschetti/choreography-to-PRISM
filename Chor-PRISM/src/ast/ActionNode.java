package ast;

import java.util.ArrayList;

public class ActionNode implements Node{
	private String actionA ;
	private String actionB;
	
	public ActionNode(String _actionA, String _actionB) {
		actionA = _actionA;
		actionB = _actionB;
	}

	@Override
	public String toPrint() {
		return actionA+"&&"+actionB;
	}

	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}
	
	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName, int counter) {
		if(actionB==null) {
			return actionA;
		}
		return actionA+"&&"+actionB;
	}
}
