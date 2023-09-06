package ast;

import java.util.ArrayList;

public class EndNode implements Node {

	ArrayList<Node> roles ;
	
	public EndNode(ArrayList<Node> _roles) {roles = _roles;}
	
	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public ArrayList<Node> getRoles(){
		return roles;
	}
	
	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}

	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName) {
		return "";
	}

}
