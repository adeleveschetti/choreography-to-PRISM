package ast;

import java.util.ArrayList;
import java.util.HashMap;

public class ProtocolNode implements Node{

	private String protocolID = null;
	private Node statement;
	private HashMap<String,ArrayList<String>> rolesVars = new HashMap<String,ArrayList<String>> ();
	private ArrayList<ArrayList<String>> roleGroups = new ArrayList<ArrayList<String>> ();
	private ArrayList<String> roles = new ArrayList<String> ();
	private Node preamble ;

	public ProtocolNode(String id, Node stat, ArrayList<ArrayList<String>>  rol, Node pream, HashMap<String,ArrayList<String>> vars) {
		protocolID = id;
		statement = stat;
		roleGroups = rol ;
		preamble = pream ;
		rolesVars = vars;
	}

	@Override
	public String toPrint() {
		String ret =  protocolID + ", " ;
		for(String el : roles) {
			ret = ret + el + ", ";
		}
		return ret + statement.toPrint();
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
	public String codeGenerator(String toRet, HashMap<String,ArrayList<Integer>> mapStates, HashMap<String,ArrayList<Integer>> mapStatesBranches, ArrayList<String> rolesTot) {



		mapStates = new HashMap<String,ArrayList<Integer>>();
		mapStatesBranches = new HashMap<String,ArrayList<Integer>>();

		String code = ""; 
		if(preamble!=null) {
			code = preamble.codeGenerator(code,mapStates,mapStatesBranches,roles);
		}

		for(ArrayList<String> list : roleGroups) {
			for(String el : list) {

				code = code + "module " + el + "\n\n"+ el + "_STATE: [0..10] init 0;\n\n\nendmodule \n\n"; 
			}
		}

		int maxLen = 0;
		for(ArrayList<String> list : roleGroups) {
			if(list.size()>=maxLen) {
				maxLen = list.size();
			}
		}
		for(ArrayList<String> list : roleGroups) {
			if(list.size()<maxLen) {
				for(int j=list.size(); j<maxLen; j++) {
					list.add(list.get(0));
				}
			}
		}

		for(int i=0; i<maxLen; i++) {
			roles = new ArrayList<String>();
			mapStates = new HashMap<String,ArrayList<Integer>>();
			mapStatesBranches = new HashMap<String,ArrayList<Integer>>();

			for(ArrayList<String> list : roleGroups ) {
				roles.add(list.get(i));
				mapStates.put(list.get(i),new ArrayList<Integer>());
				mapStatesBranches.put(list.get(i),new ArrayList<Integer>());
			}
			code = statement.codeGenerator(code,mapStates,mapStatesBranches,roles);
		}

		for(String module : rolesVars.keySet()) {
			String toFind = "module " + module + "\n";
			int index = code.indexOf(toFind);

			String tmpStr = "";
			if(rolesVars.get(module).size()>0) {
				for(String el : rolesVars.get(module)) {
					tmpStr = tmpStr + el.substring(1,el.length()-1) + "\n";
				}
			}
			code = new StringBuilder(code).insert(index+toFind.length(),tmpStr).toString();
		}


		return code ;
	}

	@Override
	public Node getStatement() {
		// TODO Auto-generated method stub
		return null;
	}

}
