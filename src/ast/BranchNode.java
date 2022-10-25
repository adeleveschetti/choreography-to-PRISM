package ast;

import java.util.ArrayList;
import java.util.Random;

public class BranchNode implements Node{

	private String roleA = null;
	private String roleB = null;
	private ArrayList<String> rates = null;
	private ArrayList<String> messages = null;
	private ArrayList<Node> statements = null;

	public BranchNode(String A, String B, ArrayList<String> listRates, ArrayList<String> listMessages, ArrayList<Node> listStatements) {
		roleA = A;
		roleB = B;
		if(listRates!=null) {
			rates = new ArrayList<String>();
			for(String el : listRates) {
				rates.add(el);
			}
		}
		if(listMessages!=null) {
			messages = new ArrayList<String>();
			for(String el : listMessages) {
				messages.add(el);
			}
		}
		if(listStatements!=null) {
			statements = new ArrayList<Node>();
			for(Node el : listStatements) {
				statements.add(el);
			}
		}

	}

	@Override
	public String toPrint() {
		String ret = roleA + " " + roleB ;
		for(String el : rates) {
			ret = ret + " " + el; 
		}

		if(statements!=null) {
			for(Node el : statements) {
				ret = ret + el.toPrint();
			}
		}
		ret = ret + "\n";
		return  ret;
	}

	@Override
	public String codeGenerator(String toRet) {


		int size = 5; 
		// generating the label

		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for(int i = 0; i<rates.size() ; i++) {

			String toFind_A = "module " + roleA + "\n";
			int index_A = toRet.indexOf(toFind_A);

			StringBuilder salt = new StringBuilder();
			Random rnd = new Random();
			while (salt.length() < size) { // length of the random string.
				int index = (int) (rnd.nextFloat() * SALTCHARS.length());
				salt.append(SALTCHARS.charAt(index));
			}
			String label = salt.toString();
			int index = messages.get(i).indexOf("@");
			int indexRole = rates.get(i).toString().indexOf("*");
			String toInsert_A = "["+label+"] () -> " + rates.get(i).toString().substring(0,indexRole) + " : " + messages.get(i).substring(0,index) + ";\n";
			toRet = new StringBuilder(toRet).insert(index_A+toFind_A.length(),toInsert_A).toString();

			String toFind_B = "module " + roleB + "\n";
			int index_B = toRet.indexOf(toFind_B);

			String toInsert_B = "["+label+"] () -> " + rates.get(i).toString().substring(indexRole+1,rates.get(i).toString().length())  + " : " + messages.get(i).substring(index+1,messages.get(i).length()) + ";\n";
			toRet = new StringBuilder(toRet).insert(index_B+toFind_B.length(),toInsert_B).toString();
			toRet = statements.get(i).codeGenerator(toRet);
		}



		return toRet;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
