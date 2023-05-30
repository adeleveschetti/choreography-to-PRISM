package ast;

import java.util.ArrayList;

import lib.Functions;

public class LoopNode implements Node{

	private String message ;
	private String indexIteration; // index of the iteration
	private String upperBound; // upperbound
	private String op ; // operation (!= ; = ; < ; > ; <= ; >=)
	private String role ;

	public LoopNode(String mex, String index, String bound, String operation, String r){
		message = mex;
		indexIteration = index;
		upperBound = bound;
		op = operation;
		role = r;
	}

	@Override
	public String toPrint() {
		return null;
	}

	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels) {
		String toRet = "";
		Functions funs = new Functions();
		if(op.equals("!=")) {
			for(int i=1; i<=totIndex; i++) {
				if(i!=index) {
					toRet = toRet + Functions.returnStringNewIndex(message,i,totIndex) + "&"; 
				}
			}
		}
		return toRet.substring(0,toRet.length()-1);
	}
}
