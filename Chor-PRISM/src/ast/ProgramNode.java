package ast;

import lib.*;

import java.util.ArrayList;

public class ProgramNode implements Node{

	public Node preamble ;
	public ArrayList<Node> modules = new ArrayList<Node>();
	public ArrayList<Node> protocols = new ArrayList<Node>();
	public int n ;

	ProgramNode(Node _preamble, ArrayList<Node> _modules, ArrayList<Node> _protocols, int _n){
		preamble = _preamble;
		modules = _modules;
		protocols = _protocols;
		n = _n;
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		String program = "";
		isCtmc = ((PreambleNode) preamble).isCtmc();
		program = program + preamble.generateCode(modules,index,n,isCtmc,labels,prot);

		for(Node el : protocols) {
			el.generateCode(modules,index,n,isCtmc,labels,el.toPrint());
		}
		program = program + "\n";
		for(Node el : modules) {
			program = program + "module " + el.toPrint() + "\n\n";
			program = program + el.toPrint() + " : [0..TBD] init 0;\n";
			if(((ModuleNode) el).getVars()!=null) {
				for(String el2 : ((ModuleNode) el).getVars()) {
					program = program + el2 + "\n";
				}
			}
			program = program + "\n";
			for(String el2 : ((ModuleNode) el).getCommands()) {				
				program = program + el2 + "\n";
			}
			program = program + "\nendmodule\n\n";
			program = program.replaceAll("TBD", String.valueOf(((ModuleNode) el).getMaxFinState()+1));
		}
		return program;
	}

	@Override
	public ArrayList<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Pair<String,ArrayList<String>>> generatePrismCode(ArrayList<Pair<String,ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String,ArrayList<State>>> states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, Pair<String,State> lastUpdate, ArrayList<Pair<String,String>> consts){
		states = new ArrayList<Pair<String,ArrayList<State>>>();
		// isCtmc = ((PreambleNode) preamble).isCtmc();
		if(code == null){
			code = new ArrayList<>();
		}
		code =  preamble.generatePrismCode(code,1,n,prot,modules,states,recValues,moduleNames,stms,lastUpdate,consts);

		State initState = new State();

		for(Node el : modules) {
			ArrayList<String> toAdd = new ArrayList<>();
			toAdd.add("module " + el.toPrint() );
			toAdd.add(el.toPrint() + " : [0..TBD] init 0;");
			if(((ModuleNode) el).getVars()!=null) {
				for(String el2 : ((ModuleNode) el).getVars()) {
					toAdd.add(el2);
				}
			}

			code.add(new Pair(el.toPrint(),toAdd));
			initState.addState(new Pair(el.toPrint(),0));
		}

		for(Node el : protocols) {
			lastUpdate = new Pair(el.toPrint(),initState);
			ArrayList<State> newStates = new ArrayList<>();
			newStates.add(initState);
			states.add(new Pair(el.toPrint(),newStates));
			code = el.generatePrismCode(code,1,n,el.toPrint(),modules,states,recValues,moduleNames,stms,lastUpdate,consts);
		}
		for(Node el : modules) {
			for(Pair<String,ArrayList<String>> pair : code){
				if(pair.getFirst().equals(el.toPrint())){
					pair.getSecond().add("endmodule");
				}
			}
		}
		for(Pair<String,ArrayList<String>> pair : code){
			int endState = -1;
			for(Pair<String,ArrayList<State>> tmp : states){
				for(State el : tmp.getSecond()){
					if(el.getModuleState(pair.getFirst())>endState){
						endState = el.getModuleState(pair.getFirst());
					}
				}
			}
			for(int i=0; i<pair.getSecond().size(); i++){
				if(pair.getSecond().get(i).contains("TBD")){
					String newStr = pair.getSecond().get(i).replaceAll("TBD",String.valueOf(endState));
					pair.getSecond().set(i,newStr);
				}
			}
		}
		return code;

	}

	@Override
	public Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, ArrayList<Pair<String,Integer>> lastUpdate, ArrayList<Pair<String,String>> consts){
		states = new ListPair();
		Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> toRet = null;
		ArrayList<Pair<String, Integer>> values = new ArrayList<Pair<String, Integer>>();
		moduleNames = new ArrayList<String>();
		ArrayList<Pair<String, Integer>> valuesInit = new ArrayList<Pair<String, Integer>>();

		for(Node el : modules){
			values.add(new Pair(el.toPrint(),0));
			moduleNames.add(el.toPrint());
			valuesInit.add(new Pair(el.toPrint(),-1));
			if(consts==null){
				consts = new ArrayList<>();
			}
			for(String el2 : ((ModuleNode)el).getVars()){
				if(el2.contains("..")) {
					consts.add(new Pair(el2.substring(0, el2.indexOf(":") - 1),el2.substring(el2.indexOf("..") + 2, el2.indexOf("]"))));
				}
			}
		}

		states.add(new Triplet(0,valuesInit,values));
		for(Node el : modules) {
			toRet = el.generateStates(modules,states,recValues,moduleNames,stms,null,consts);
			states = toRet.getSecond();
			recValues = toRet.getFirst();
		}
		for(String var : ((PreambleNode) preamble).getVars()) {
			if (var.contains("=")) { // TODO : fix that
				String[] tmp = var.substring(0, var.indexOf("=")).split(" ");
				for (String t : tmp) {
					if (!t.contains("const") && !t.contains("double") && !t.contains("int") && !t.contains("bool")) {
						consts.add(new Pair(t,var.substring(var.indexOf("= ") + 2, var.length() - 2)));

					}
				}
			}
		}
		stms = new ArrayList<>();
		for(Node el : protocols) {
			stms.add(new Pair(el.toPrint(),((ProtocolNode)el).getStatements()));
		}
		for(Node el : protocols) {
			toRet = el.generateStates(modules,states,recValues,moduleNames,stms,states.get(states.getSize()-1).getThird(),consts);
			states = toRet.getSecond();
			recValues = toRet.getFirst();
		}
		toRet = preamble.generateStates(modules,states,toRet.getFirst(),moduleNames,stms,states.get(states.getSize()-1).getThird(),consts);
		return toRet;
	}

	@Override
	public Matrix generateMarkovChain(ArrayList<Node> mods) {
		return null;
	}

}
