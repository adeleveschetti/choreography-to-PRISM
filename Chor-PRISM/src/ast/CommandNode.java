package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;
import lib.State;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommandNode implements Node{

    ArrayList<String> rates ;
    ArrayList<String> updates ;
    String role;

    public CommandNode(String _role, ArrayList<String> _rates, ArrayList<String> _updates){
        role = _role;
        rates = _rates;
        updates = _updates;
    }

    @Override
    public String toPrint() {

        String toPrint = role ;

        for(String el : rates){
            toPrint = toPrint + " : "  + el ;
        }
        for(String el : updates){
            toPrint = toPrint + el;
        }
        return toPrint;
    }

    public String getRole(){
        return role;
    }

    public ArrayList<String> getRates(){
        return rates;
    }

    public ArrayList<String> getUpdates(){
        return updates;
    }

    @Override
    public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
        return null;
    }

    @Override
    public ArrayList<String> getRoles() {
        return null;
    }

    @Override
    public Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String, ArrayList<Node>>> stms, ArrayList<Pair<String, Integer>> lastUpdate, ArrayList<Pair<String, String>> consts) {
        return null;
    }

    @Override
    public Matrix generateMarkovChain(ArrayList<Node> mods) {
        return null;
    }

    @Override
    public ArrayList<Pair<String, ArrayList<String>>> generatePrismCode(ArrayList<Pair<String, ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String, ArrayList<State>>> states, ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String, ArrayList<Node>>> stms, Pair<String, State> lastUpdate, ArrayList<Pair<String, String>> consts) {
        return null;
    }
}
