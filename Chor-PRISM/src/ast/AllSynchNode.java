package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;
import lib.State;

import java.util.ArrayList;
import java.util.Random;

public class AllSynchNode implements Node {
    ArrayList<String> roles;
    ArrayList<Node> commands;
    Node stat;

    public AllSynchNode(ArrayList<String> _roles, ArrayList<Node> _commands, Node _stat) {
        commands = _commands;
        roles = _roles;
        stat = _stat;
    }

    @Override
    public String toPrint() {
        String toPrint = "";
        for (Node el : commands) {
            toPrint = toPrint + el.toPrint();
        }
        return toPrint;
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
        ArrayList<Pair<String, ArrayList<String>>> toRet;
        boolean firstLabel = false;
        String label = "";
        ArrayList<String> labels = new ArrayList<String>();
        ArrayList<Pair<String, State>> newRecs = new ArrayList<>();
        State newStates = new State();

        while (!firstLabel) {
            String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            StringBuilder salt = new StringBuilder();
            Random rnd = new Random();
            int size = 5;
            while (salt.length() < size) { // length of the random string.
                int indexLabel = (int) (rnd.nextFloat() * SALTCHARS.length());
                salt.append(SALTCHARS.charAt(indexLabel));
            }
            label = salt.toString();
            if (labels == null) {
                labels = new ArrayList<String>();
                labels.add(label);
                firstLabel = true;
            } else {
                if (!labels.contains(label)) {
                    firstLabel = true;
                    labels.add(label);
                }
            }
        }
        Pair<String, State> initState = null;

        if (prot.equals(lastUpdate.getFirst())) {
            initState = lastUpdate;
        } else {
            for (Pair<String, ArrayList<State>> pair : states) {
                if (pair.getFirst().equals(prot)) {
                    initState = new Pair(prot, pair.getSecond().get(pair.getSecond().size()));
                }
            }
        }

        for (Node el : commands) {
            String newCmd = "[" + label + "]";
            int state = (initState.getSecond().getModuleState(((CommandNode) el).getRole()));
            newCmd = newCmd + "(" + ((CommandNode) el).getRole() + " = " + state + ") -> ";

            if (stat instanceof RecNode) {
                boolean found = false;
                for (Pair<String, ArrayList<State>> st : states) {
                    if (st.getFirst().equals(stat.toPrint())) {
                        found = true;
                        state = st.getSecond().get(0).getModuleState(((CommandNode) el).getRole());
                    }
                }
                if (!found) {
                    boolean foundRole = false;
                    for (Pair<String, State> pairNew : newRecs) {
                        if (pairNew.getFirst().equals(stat)) {
                            if (pairNew.getSecond().getModuleState(((CommandNode) el).getRole()) != -1) {
                                state = pairNew.getSecond().getModuleState(((CommandNode) el).getRole());
                                foundRole = true;
                            }
                        }
                    }
                    if (!foundRole) {
                        state = initState.getSecond().getModuleState(((CommandNode) el).getRole()) + 1;
                        boolean added = false;
                        for (Pair<String, State> pairNew : newRecs) {
                            if (pairNew.getFirst().equals(stat.toPrint())) {
                                pairNew.getSecond().addState(new Pair(((CommandNode) el).getRole(), state));
                                added = true;
                            }
                        }
                        if (newRecs.size() == 1 || !added) {
                            State stateToAdd = new State();
                            stateToAdd.addState(new Pair(((CommandNode) el).getRole(), state));
                            newRecs.add(new Pair(stat.toPrint(), stateToAdd));
                        }
                    }
                }
            } else {
                state = state + 1;
                newStates.addState(new Pair(((CommandNode) el).getRole(), state));

            }
            for (int i = 0; i < ((CommandNode) el).getUpdates().size(); i++) {
                if (i > 0) {
                    newCmd = newCmd + " + ";
                }
                newCmd += ((CommandNode) el).getRates().get(i) + ":" + ((CommandNode) el).getUpdates().get(i) + "&(" + ((CommandNode) el).getRole() + "'=" + state + ")";
                if (i == ((CommandNode) el).getUpdates().size() - 1) {
                    newCmd += ";";
                }
            }
            for (Pair<String, ArrayList<String>> pair : code) {
                if (pair.getFirst().equals(((CommandNode) el).getRole())) {
                    pair.getSecond().add(newCmd);
                }
            }

        }
        for(Pair<String,Integer> pair : lastUpdate.getSecond().getListOfState()){
            boolean contained = false;
            for(Pair<String,Integer> pair2 : newStates.getListOfState()){
                if(pair.getFirst().equals(pair2.getFirst()) && pair.getSecond()==pair2.getSecond()){
                    contained = true;
                }
            }
            if(!contained){
                newStates.addState(pair);
            }
        }
        boolean foundProt = false;
        for(Pair<String, ArrayList<State>> pair : states){
            if(pair.getFirst().equals(prot)){
                pair.getSecond().add(newStates);
            }
            if(pair.getFirst().equals(stat)){
                pair.getSecond().add(newStates);
                foundProt = true;
            }
        }

        if(!foundProt){

            for(Pair<String,State> pair : newRecs){
                ArrayList<State> newStatesRecs = new ArrayList<>();
                newStatesRecs.add(pair.getSecond());
                states.add(new Pair(pair.getFirst(),newStatesRecs));
            }
        }
        toRet = stat.generatePrismCode(code, index, maxIndex, prot, mods, states, recValues, moduleNames, stms, new Pair(prot,newStates), consts);
        return toRet;
    }

}
