package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;

import java.util.ArrayList;

public class RecNode implements Node{

	public String protocolName ;
	
	RecNode(String _name){
		protocolName = _name;
	}
	
	@Override
	public String toPrint() {
		return protocolName;
	}

	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> getRoles() {
		// TODO Auto-generated method stub
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

}
