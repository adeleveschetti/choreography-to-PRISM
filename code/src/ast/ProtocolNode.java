package ast;

import java.util.ArrayList;
import java.util.HashMap;

import lib.Pair;

public class ProtocolNode implements Node{

	private String protocolID = "";
	private Node statement;
	private int varValue ;
	private HashMap<String,ArrayList<String>> rolesVars = new HashMap<String,ArrayList<String>> ();
	private ArrayList<Pair<String,String>> roles = new ArrayList<Pair<String,String>> ();
	private Node preamble ;
	private Node statementIF = null;
	private String cond ;

	public ProtocolNode(String id, Node stat, ArrayList<Pair<String,String>>  rol, Node pream, HashMap<String,ArrayList<String>> roleVars, int var) {
		protocolID = id;
		statement = stat;
		roles = rol ;
		preamble = pream ;
		rolesVars = roleVars;
		varValue = var;
	}
	

	@Override
	public String toPrint() {
		return null;
	}
	
	@Override 
	public ArrayList<String> getRate() {
		return null;
	}

	@Override 
	public String getRoleA() {
		return "";
	}

	@Override 
	public String getRoleB() {
		return "";
	}

	@Override
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> rolesTot, ArrayList<String> allRoles, int currIndex, int totIndex) {



		mapStates = new HashMap<String,ArrayList<Integer>>();
		mapStatesBranches = new HashMap<String,ArrayList<Integer>>();

		String code = ""; 

		if(preamble!=null) {
			code = preamble.codeGenerator(code,mapStates,mapStatesBranches,null,allRoles,0,varValue);
		}

		ArrayList<ArrayList<Pair<String,ArrayList<String>>>> variablesRoles = new ArrayList<ArrayList<Pair<String,ArrayList<String>>>>();

		for(Pair<String,String> el : roles) {
			ArrayList<Pair<String,ArrayList<String>>> tmpRoles = new ArrayList<Pair<String,ArrayList<String>>>();

			ArrayList<String> varRole = null;
			if(rolesVars.get(el.getFirst())!=null) {
				varRole = rolesVars.get(el.getFirst());
			}
			if(!el.getSecond().equals("1")) {

				for(int i=0; i<varValue; i++) {
					ArrayList<String> tmp = null;
					if(varRole!=null) {
						tmp = new ArrayList<String>();
						for(String var : varRole) {
							tmp.add(var.replace("[i]",Integer.toString(i+1)));
						}
					}
					tmpRoles.add(new Pair(el.getFirst().replace("[i]",Integer.toString(i+1)),tmp));
				}


			}
			else {
				tmpRoles.add(new Pair(el.getFirst(),varRole));
			}

			variablesRoles.add(tmpRoles);
		}
	


		for(ArrayList<Pair<String,ArrayList<String>>> list : variablesRoles) {
			for(Pair<String,ArrayList<String>> pair : list) {
				code = code + "module " + pair.getFirst() + "\n\n"+ pair.getFirst() + "_STATE: [0..10] init 0;\n\n\nendmodule \n\n"; 
			}
		}
		for(ArrayList<Pair<String,ArrayList<String>>> list : variablesRoles) {
			if(list.size()<varValue) {
				for(int j = list.size(); j<varValue; j++) {
					list.add(list.get(0));
				}
			}
		}

		
		for(int i=0; i<varValue; i++) {
			ArrayList<String> subRoles = new ArrayList<String>();
			mapStates = new HashMap<String,ArrayList<Integer>>();
			mapStatesBranches = new HashMap<String,ArrayList<Integer>>();
			for(ArrayList<Pair<String,ArrayList<String>>> list : variablesRoles) {
				mapStates.put(list.get(i).getFirst(),new ArrayList<Integer>());
				mapStatesBranches.put(list.get(i).getFirst(),new ArrayList<Integer>());
				subRoles.add(list.get(i).getFirst());
			}
			

			code = statement.codeGenerator(code,mapStates,mapStatesBranches,subRoles,allRoles,i,varValue);

		}


		for(ArrayList<Pair<String,ArrayList<String>>> list : variablesRoles) {
			for(Pair<String,ArrayList<String>> pair : list) {
				String toFind = "module " + pair.getFirst() + "\n";
				int index = code.indexOf(toFind);

				String tmpStr = "";
				if(pair.getSecond()!=null) {
					for(String el : pair.getSecond()) {
						if(!code.contains(el.substring(1,el.length()-1))) {
							tmpStr = tmpStr + el.substring(1,el.length()-1) + "\n";
						}
					}
				}
				code = new StringBuilder(code).insert(index+toFind.length(),tmpStr).toString();
			}
		}


		return code ;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
