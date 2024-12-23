package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;
import lib.State;

import java.util.ArrayList;

public class ProtocolNode implements Node{

	public String id;
	public ArrayList<Node> statements ;
	public int stateInit;
	
	ProtocolNode(String _id, ArrayList<Node> _statements){
		id = _id;
		statements = _statements;
	}
	
	@Override
	public String toPrint() {
		return id;
	}

	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		for(Node el : statements) {
			for(int i=1;i<=maxIndex; i++) {
				el.generateCode(mods,i,maxIndex,isCtmc,labels,prot);
			}
		}
		return null;
	}

	public void setCtmc(boolean _isCtmc){
		for(Node el : statements){
			if(el instanceof BranchNode){
				((BranchNode) el).setCtmc(_isCtmc);
			}
		}
	}

	public ArrayList<Node> getStatements(){
		return statements;
	}

	@Override
	public ArrayList<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, ArrayList<Pair<String,Integer>> lastUpdate, ArrayList<Pair<String,String>> consts){
		if(recValues==null){
			recValues = new ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>();
		}
		ArrayList<Pair<String,Integer>> tmp = new ArrayList<>();
		ArrayList<Pair<String,Integer>> values = states.get(0).getThird();
		for(String name : moduleNames){
			for(Pair el : values){
				if(name.equals(el.getFirst())){
					tmp.add(el);
				}
			}
		}
		recValues.add(new Pair(id,tmp));
		Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> toRet = null;
		for(Node el : statements) {
			toRet = el.generateStates(mods, states,recValues,moduleNames,stms,lastUpdate,consts);
		}
		return toRet;
	}

	@Override
	public ArrayList<Pair<String,ArrayList<String>>> generatePrismCode(ArrayList<Pair<String,ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String,ArrayList<State>>> states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, Pair<String,State> lastUpdate, ArrayList<Pair<String,String>> consts){
		ArrayList<Pair<String,ArrayList<String>>> toRet = new ArrayList<>();

		for(Node el : statements) {
			toRet = el.generatePrismCode(code,index,maxIndex,prot,mods, states,recValues,moduleNames,stms,lastUpdate,consts);
		}

		return code;
	}

	@Override
	public Matrix generateMarkovChain(ArrayList<Node> mods) {
		return null;
	}

}
