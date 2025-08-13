package ast;

import lib.*;

import javax.print.attribute.IntegerSyntax;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class AllSynchNode implements Node {
    ArrayList<String> roles;
    ArrayList<Node> commands;
    Node stat;
    boolean isPar = false;
    int upperBound = -1;

    public AllSynchNode(ArrayList<String> _roles, ArrayList<Node> _commands, Node _stat, boolean _isPar, int _maxIndex) {
        commands = _commands;
        roles = _roles;
        stat = _stat;
        isPar = _isPar;
        upperBound = _maxIndex;
        for (Node cmd : commands) {
            ((CommandNode) cmd).setUpperBound(upperBound);
        }
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
        return roles;
    }

    public static ArrayList<ArrayList<Pair<String, Integer>>> generateCombinations(ArrayList<ArrayList<Pair<String, Integer>>> newUps) {
        ArrayList<ArrayList<Pair<String, Integer>>> result = new ArrayList<>();

        int n = newUps.size();
        for (int i = 1; i < (1 << n); i++) {
            ArrayList<Pair<String, Integer>> combination = new ArrayList<>();
            Set<String> firstElements = new HashSet<>();
            boolean isValid = true;
            int count = 0; // To count how many lists are included in the combination

            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    for (Pair<String, Integer> pair : newUps.get(j)) {
                        if (firstElements.contains(pair.getFirst())) {
                            isValid = false;
                            break;
                        } else {
                            firstElements.add(pair.getFirst());
                        }
                    }
                    if (isValid) {
                        combination.addAll(newUps.get(j));
                        count++;
                    } else {
                        break;
                    }
                }
            }
            if (isValid && count > 1) { // Only add the combination if it includes elements from at least two different lists
                result.add(combination);
            }
        }

        return result;
    }


    @Override
    public Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String, ArrayList<Node>>> stms, ArrayList<Pair<String, Integer>> lastUpdate, ArrayList<Pair<String, String>> consts) {


        Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> toRet = null;
        int size = states.getSize();
        ArrayList<ListPair> newStatesCmd = new ArrayList<>();

        for (Node el : commands) {
            toRet = el.generateStates(mods, states, recValues, moduleNames, stms, lastUpdate, consts);
            newStatesCmd.add(toRet.getSecond());
            recValues = toRet.getFirst();
            if(!roles.contains(((CommandNode)el).getRole())) {
                roles.add(((CommandNode) el).getRole());
            }
        }



        int newSize = states.getSize();
        int diff = newSize - size;

        ArrayList<ArrayList<Pair<String, Integer>>> newUps = new ArrayList<>();
        ArrayList<Pair<String, Integer>> tmp = null;

        for (int ii = 0; ii < diff; ii++) {
            tmp = new ArrayList<>();
            int count = 0;
            for (Pair<String, Integer> el2 : toRet.getSecond().getList().get(toRet.getSecond().getList().size() - 1 - ii).getThird()) {
                count++;
                for (Pair<String, Integer> pair : lastUpdate) {
                    if (pair.getFirst().equals(el2.getFirst()) && pair.getSecond() != el2.getSecond()) {
                        tmp.add(el2);
                    }
                }
            }
            if (!tmp.isEmpty()) {
                newUps.add(tmp);
            }
        }

        boolean diffProc = true;
        for(int i=0; i<roles.size()-1; i++){
            for(int j=i+1; j<roles.size(); j++){
                if(roles.get(i)==roles.get(j)){
                    diffProc = false;
                }
            }
        }

        ArrayList<ArrayList<Pair<String, Integer>>> result = new ArrayList<>();

        if(diffProc && newUps.size()==roles.size()){
            ArrayList<Pair<String,Integer>> tmpArray = new ArrayList<>();
            for(ArrayList<Pair<String,Integer>> el : newUps){
                for(Pair<String,Integer> pair : el) {
                    tmpArray.add(pair);
                }
            }
            result.add(tmpArray);

        }
        else{ result = generateCombinations(newUps); }

        newUps = new ArrayList<>();
        for (ArrayList<Pair<String, Integer>> array : result) {
            tmp = new ArrayList<>();
            for (Pair<String, Integer> el : lastUpdate) {
                boolean found = false;
                for (Pair<String, Integer> pair : array) {
                    if (pair.getFirst().equals(el.getFirst())) {
                        tmp.add(pair);
                        found = true;
                    }
                }
                if (!found) {
                    tmp.add(el);
                }
            }
            if (!tmp.isEmpty()) {
                newUps.add(tmp);
            }
        }
        String rate = states.getList().get(states.getSize() - 1).getFirst(); // TODO : fix that
        for (int i = 0; i < diff; i++) {
            states.remove(newSize - 1 - i);
        }
        for (ArrayList<Pair<String, Integer>> array : newUps) {
            Triplet toAdd = new Triplet<>(rate, lastUpdate, array);
            if (!states.contains(toAdd)) {
                states.add(toAdd);
            }
        }
       /* for (int i = 0; i < states.getSize(); i++) {
            for (Pair el2 : states.get(i).getSecond()) {
                System.out.print("[" + el2.getFirst() + "," + el2.getSecond() + "] ");
            }
            System.out.print("->");
            for (Pair el2 : states.get(i).getThird()) {
                System.out.print("[" + el2.getFirst() + "," + el2.getSecond() + "] ");
            }
            System.out.println("----------");

        }
        System.out.println("################# " + newSize + " " + size);*/
        newSize = states.getSize();
        diff = newSize - size;

        for (int i = 0; i < diff; i++) {
            lastUpdate = states.getList().get(newSize - 1 - i).getThird();
            if (stat instanceof RecNode) {
                ArrayList<Node> newStms = new ArrayList<>();
                for (Pair<String, ArrayList<Node>> el : stms) {
                    if (el.getFirst().equals(stat.toPrint())) {
                        newStms = el.getSecond();
                    }
                }
                if (states.getSize() != newSize) {
                    for (int k = 0; k < newStms.size(); k++) {
                        toRet = newStms.get(k).generateStates(newStms, states, recValues, moduleNames, stms, lastUpdate, consts);
                        states = toRet.getSecond();
                        recValues = toRet.getFirst();
                    }
                }
            } else if (stat instanceof EndNode) {
                break;
            } else {
                toRet = stat.generateStates(mods, states, recValues, moduleNames, stms, lastUpdate, consts);
                states = toRet.getSecond();
                recValues = toRet.getFirst();
            }
        }
        return toRet;
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
        if (isPar) {

            for (Node el : commands) {
                for (int t = 1; t <= upperBound; t++) {
                    String role = Functions.returnStringNewIndex(((CommandNode) el).getRole(), t, maxIndex);

                    String newCmd = "[" + label + "]";
                    int state = (initState.getSecond().getModuleState(role));
                    newCmd = newCmd + "(" + role + " = " + state + ") -> ";

                    if (stat instanceof RecNode) {
                        boolean found = false;
                        for (Pair<String, ArrayList<State>> st : states) {
                            if (st.getFirst().equals(stat.toPrint())) {
                                found = true;
                                state = st.getSecond().get(0).getModuleState(role);
                            }
                        }
                        if (!found) {
                            boolean foundRole = false;
                            for (Pair<String, State> pairNew : newRecs) {
                                if (pairNew.getFirst().equals(stat)) {
                                    if (pairNew.getSecond().getModuleState(role) != -1) {
                                        state = pairNew.getSecond().getModuleState(role);
                                        foundRole = true;
                                    }
                                }
                            }
                            if (!foundRole) {
                                state = initState.getSecond().getModuleState(role) + 1;
                                boolean added = false;
                                for (Pair<String, State> pairNew : newRecs) {
                                    if (pairNew.getFirst().equals(stat.toPrint())) {
                                        pairNew.getSecond().addState(new Pair(role, state));
                                        added = true;
                                    }
                                }
                                if (newRecs.size() == 1 || !added) {
                                    State stateToAdd = new State();
                                    stateToAdd.addState(new Pair(role, state));
                                    newRecs.add(new Pair(stat.toPrint(), stateToAdd));
                                }
                            }
                        }
                    } else {
                        state = state + 1;
                        newStates.addState(new Pair(role, state));

                    }
                    for (int i = 0; i < ((CommandNode) el).getUpdates().size(); i++) {
                        if (i > 0) {
                            newCmd = newCmd + " + ";
                        }
                        newCmd += Functions.returnStringNewIndex(((CommandNode) el).getRates().get(i), t, maxIndex) + ":" + Functions.returnStringNewIndex(((CommandNode) el).getUpdates().get(i), t, maxIndex) + "&(" + role + "'=" + state + ")";
                        if (i == ((CommandNode) el).getUpdates().size() - 1) {
                            newCmd += ";";
                        }
                    }

                    for (Pair<String, ArrayList<String>> pair : code) {
                        if (pair.getFirst().equals(role)) {
                            pair.getSecond().add(newCmd);
                        }
                    }
                }
            }
        } else {
            for (Node el : commands) {
                String cmd = "[" + label + "] (" + ((CommandNode) el).getRole() + " = ";
                int state = (initState.getSecond().getModuleState(((CommandNode) el).getRole()));
                cmd += state + ")";

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
                cmd += "&" + ((CommandNode) el).getGuard() + " -> ";

                for (int i = 0; i < ((CommandNode) el).getRates().size(); i++) {
                    if (i != 0) {
                        cmd += " + ";
                    }
                    cmd += ((CommandNode) el).getRates().get(i) + " : " + ((CommandNode) el).getUpdates().get(i) + "&(" + ((CommandNode) el).getRole() + "'=" + state + ")";
                    if (i == ((CommandNode) el).getRates().size() - 1) {
                        cmd += ";";
                    }
                }
                for (Pair<String, ArrayList<String>> pair : code) {
                    if (pair.getFirst().equals(((CommandNode) el).getRole())) {
                        pair.getSecond().add(cmd);
                    }
                }
            }
        }
        for (Pair<String, Integer> pair : lastUpdate.getSecond().getListOfState()) {
            boolean contained = false;

            for (Pair<String, Integer> pair2 : newStates.getListOfState()) {
                if (pair.getFirst().equals(pair2.getFirst()) && pair.getSecond() == pair2.getSecond()) {
                    contained = true;
                }
            }
            if (!contained) {
                newStates.addState(pair);
            }
        }
        boolean foundProt = false;
        for (Pair<String, ArrayList<State>> pair : states) {
            if (pair.getFirst().equals(prot)) {
                pair.getSecond().add(newStates);
            }
            if (pair.getFirst().equals(stat.toPrint())) {
                pair.getSecond().add(newStates);
                foundProt = true;
            }
        }
        for (Pair<String, Integer> el : initState.getSecond().getListOfState()) {
            if (newStates.getModuleState(el.getFirst()) == -1) {
                newStates.addState(el);
            }
        }

        if (!foundProt) {

            for (Pair<String, State> pair : newRecs) {
                ArrayList<State> newStatesRecs = new ArrayList<>();
                newStatesRecs.add(pair.getSecond());
                states.add(new Pair(pair.getFirst(), newStatesRecs));
            }
        }

        toRet = stat.generatePrismCode(code, index, maxIndex, prot, mods, states, recValues, moduleNames, stms, new Pair(prot, newStates), consts);
        return toRet;
    }

}
