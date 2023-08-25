package ast;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class RecNode implements Node{

	String name;
	int state = 0 ;
	boolean generated ;
	
	public RecNode(String _name) {
		name = _name ;
	}
	
	public RecNode(String _name, boolean _gen) {
		name = _name ;
		generated = _gen;
	}
	
	public int getState() {
		if(generated==false) {
			return -1;
		}
		return state;
	}
	
	public String getName() {
		return name;
	}
	
	
	public void setState(int _state) {
		state = _state;
	}
	
	public void setGenerated(boolean _gen) {
		generated = _gen;
	}
	
	public String changeState(String code) {
		long occ = Pattern.compile(name).matcher(code).results().count();
		for(int i=0; i<occ; i++) {
			int index = code.indexOf(name);
			String toRet = code.substring(0,index) + state + code.substring(index+name.length(),code.length());
			code = toRet;
		}
		return code;
	}
	
	@Override
	public String toPrint() {
		return name;
	}

	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}
	
	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName) {
		return code + name;
	}

}
