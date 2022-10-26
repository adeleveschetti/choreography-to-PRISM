package ast;

public class InternalActionNode implements Node{

	private String message = null;
	private String role = null;
	private Node statement = null;
	
	public InternalActionNode(String mex, String n, Node stat) {
		message = mex;
		role = n;
		statement = stat ;
	}
	
	@Override
	public String toPrint() {
		return message + "@" + role + " " + statement.toPrint();
	}

	@Override
	public String codeGenerator(String toRet, int state, int howMany) {
		return message ;
	}

	@Override
	public Node getStatement() {
		return statement;
	}

}
