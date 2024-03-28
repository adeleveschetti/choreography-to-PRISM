package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;

import java.util.ArrayList;

public class EndNode implements Node {

	ArrayList<Node> roles ;
	
	public EndNode(ArrayList<Node> _roles) {roles = _roles;}
	
	@Override
	public String toPrint() {
		return "END";
	}
	
	@Override
	public ArrayList<String> getRoles(){
		return new ArrayList<String>();
	}

    @Override
	public Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, ArrayList<Pair<String,Integer>> lastUpdate, ArrayList<Pair<String,String>> consts){
		return new Pair(recValues,states);
	}
    @Override
	public Matrix generateMarkovChain(ArrayList<Node> mods) {
		return null;
	}


	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		
		return "";
	}

}
