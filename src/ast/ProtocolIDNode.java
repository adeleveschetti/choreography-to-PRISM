package ast;

public class ProtocolIDNode implements Node{

	String id = null;
	
	public ProtocolIDNode(String name) {
		id = name;
	}
	
	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return " " + id + " " ;
	}

	@Override
	public String codeGenerator(String toRet, int state) {
		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
