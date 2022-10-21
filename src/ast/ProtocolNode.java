package ast;

public class ProtocolNode implements Node{

	private String protocolID = null;
	private Node statement;
	
	public ProtocolNode(String id, Node stat) {
		protocolID = id;
		statement = stat;
	}
	
	@Override
	public String toPrint() {
		return protocolID + statement.toPrint();
	}

	@Override
	public String codeGenerator() {
		// TODO Auto-generated method stub
		return statement.codeGenerator();
	}

}
