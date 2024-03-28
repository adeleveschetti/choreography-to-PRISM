package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;

import java.util.ArrayList;

public interface Node {
	String toPrint();
	String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot);	
	ArrayList<String> getRoles();
	Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, ArrayList<Pair<String,Integer>> lastUpdate, ArrayList<Pair<String,String>> consts);
	Matrix generateMarkovChain(ArrayList<Node> mods);

}
