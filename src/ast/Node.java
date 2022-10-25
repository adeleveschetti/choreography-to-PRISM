package ast;

public interface Node {

	String toPrint();
	
	String codeGenerator(String toRet);
	
	Node getStatement();
	
}