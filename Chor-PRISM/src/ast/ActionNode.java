package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;
import lib.State;

import java.util.ArrayList;

public class ActionNode implements Node{
	private ArrayList<String> actions = null;
	private String actionA ;
	private String actionB;
	
	public ActionNode(String _actionA, String _actionB) {
		actionA = _actionA;
		actionB = _actionB;
	}

	public ActionNode(ArrayList<String> _actions){
		actions = _actions;
	}
	@Override
	public String toPrint() {
		if(actions!=null){
			String toRet = "";
			for(int i=0; i<actions.size(); i++){
				toRet = toRet + actions.get(i) ;
				if(i<actions.size()-1){toRet = toRet + "&&";}
			}
			return toRet;
		}
		if(actionA == null || actionA.equals(" ")) {
			if (actionB == null || actionB.equals(" ")) {
				return "";
			}
			else{
				return " &&"+actionB;
			}
		}
		else {
			if(actionB == null || actionB.equals(" ")) {
				return actionA + "&& ";
			}
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
	public ArrayList<Pair<String,ArrayList<String>>> generatePrismCode(ArrayList<Pair<String,ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String,ArrayList<State>>> states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, Pair<String,State> lastUpdate, ArrayList<Pair<String,String>> consts){
		return code;
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
