package ast;

public class IfThenElseNode implements Node{

	private String role;
	private String cond; 
	private Node thenStat;
	private Node elseStat;

	public IfThenElseNode(String r, String c, Node then, Node elseS) {
		role = r;
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
	public String codeGenerator(String toRet) {
		String toFind_A = "module " + role + "\n";
		int index_A = toRet.indexOf(toFind_A);

		String toInsert_A = "[] ("+ cond + ") -> 1:";

		if(thenStat instanceof ProtocolIDNode) {
			toInsert_A = "[] ("+ cond + ") -> 1: ;\n" ;
		}
		else {
			toInsert_A = toInsert_A + thenStat.codeGenerator(toRet) + ";\n";
		}

		toRet = new StringBuilder(toRet).insert(index_A+toFind_A.length(),toInsert_A).toString();

		if(elseStat instanceof IfThenElseNode) {
			toRet = elseStat.codeGenerator(toRet);
		}
		if(thenStat instanceof InternalActionNode) {
			toRet = thenStat.getStatement().codeGenerator(toRet);
		}
		
		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
