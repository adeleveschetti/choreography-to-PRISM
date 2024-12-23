package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;
import lib.State;

import java.util.ArrayList;

public class MessageNode implements Node {

	Node actions;
	ArrayList<Node> loop;
	String message;
	boolean beforeAction;

	public MessageNode(Node _actions, ArrayList<Node> _loop, String _message, boolean _beforeAction) {
		actions = _actions;
		loop = _loop;
		message = _message;
		beforeAction = _beforeAction;
	}

	@Override
	public String toPrint() {
		return actions.toPrint();
	}

	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		if(loop==null) {
			return actions.generateCode(mods,index,maxIndex,isCtmc,labels,prot);
		}
		else if(actions==null && loop.size()>1) {

			String ret = "";
			for(int i=0; i<loop.size(); i++) {
				ret = ret + loop.get(i).generateCode(mods,index,maxIndex,isCtmc,labels,prot);
				if(i!=loop.size()-1) {
					ret = ret + "&&";
				}
			}
			return ret;
		}
		String ret = "";
		String loopMessage = loop.get(0).generateCode(mods,index,maxIndex,isCtmc,labels,prot);
		if(beforeAction) {
			ret = message + "&&" + loopMessage;
		}
		else {
			ret = loopMessage + "&&" + message;
		}
		return ret;
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

	@Override
	public ArrayList<Pair<String,ArrayList<String>>> generatePrismCode(ArrayList<Pair<String,ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String,ArrayList<State>>> states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, Pair<String,State> lastUpdate, ArrayList<Pair<String,String>> consts){
		return code;
	}

}
