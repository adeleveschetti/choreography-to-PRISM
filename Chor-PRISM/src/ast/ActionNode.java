package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;

import java.util.ArrayList;

public class ActionNode implements Node{
	private String actionA ;
	private String actionB;
	
	public ActionNode(String _actionA, String _actionB) {
		actionA = _actionA;
		actionB = _actionB;
	}

	@Override
	public String toPrint() {
		if(actionB == null || actionB.equals(" ")) {
			return actionA;
		}
		return actionA+"&&"+actionB;
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
		if(actionB==null || actionB.equals(" ") ) {
			actionB = " ";
		}
		return actionA+"&&"+actionB;
	}
}
