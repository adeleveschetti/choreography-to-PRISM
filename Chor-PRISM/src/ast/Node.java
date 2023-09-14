package ast;

import java.util.ArrayList;
import java.util.HashMap;

public interface Node {

	String toPrint();
	String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName, int counter);
	String projection(int index, int totIndex, ArrayList<Node> modules);
	
}
