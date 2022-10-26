package ast;

public interface Node {

	String toPrint();
	
	String codeGenerator(String toRet, int state, int howMany);
	
	Node getStatement();
	
}
