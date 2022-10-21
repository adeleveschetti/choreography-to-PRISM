package ast;

public class IfThenElseNode implements Node{

	private String cond; 
	private Node thenStat;
	private Node elseStat;
	
	public IfThenElseNode(String c, Node then, Node elseS) {
		cond = c;
		thenStat = then;
		elseStat = elseS;
	}
	
	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return  "if " + cond + thenStat.toPrint() + "\n else " + elseStat.toPrint() + "\n";
	}
	
	@Override
	public String codeGenerator() {
		return null;
	}

}
