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
	public String codeGenerator() {
		// TODO Auto-generated method stub
		return null;
	}

}
