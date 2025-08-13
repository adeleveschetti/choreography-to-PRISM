package ast;

import lib.*;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CommandNode implements Node {

    ArrayList<String> rates;
    ArrayList<String> updates;
    String role;
    String guard;
    int upperBound = -1;

    Node cont;

    public CommandNode(String _role, ArrayList<String> _rates, ArrayList<String> _updates, String _guard) {
        role = _role;
        rates = _rates;
        updates = _updates;
        guard = _guard;
    }

    public void addCont(Node _cont) {
        cont = _cont;
    }



    public void setUpperBound(int val) {
        upperBound = val;
    }

    @Override
    public String toPrint() {
        String toPrint = role;
        if (guard != null) {
            toPrint = toPrint + guard + " -> ";
        }
        for (int i = 0; i < rates.size(); i++) {
            if (i > 0) {
                toPrint += " + ";
            }
            toPrint = toPrint + " " + rates.get(i) + " : " + updates.get(i);
        }
        return toPrint;
    }

    public String getGuard() {
        return guard;
    }

    public String getRole() {
        return role;
    }

    public ArrayList<String> getRates() {
        return rates;
    }

    public ArrayList<String> getUpdates() {
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


        boolean condVerified = false;
        boolean orCond = false;
        int size = states.getSize();
        if (guard != null) {
            if (guard.contains("|")) {
                orCond = true;
            }
            String[] conds = guard.split("[&|]");


            int counter = 0;
            for (String el2 : conds) {

                for (Pair<String, Integer> el : lastUpdate) {

                    if (!el2.contains("=") && !el2.contains("<") && !el2.contains(">")) {

                        String newEl = "";
                        if (el2.contains("!")) {
                            newEl = "(" + el2.substring(1, el2.length()) + "=0)";
                        } else {
                            newEl = "(" + el2 + "=1)";
                        }
                        el2 = newEl;
                    }
                    if (el2.contains("=") || el2.contains("<") || el2.contains(">")) {
                        String var = "";
                        String op = "";
                        String opp = "";
                        if (el2.contains("=")) {
                            if (el2.contains("!")) {
                                var = el2.substring(2, el2.indexOf("="));
                            } else {
                                var = el2.substring(1, el2.indexOf("="));
                            }
                            op = "=";
                            opp = "!=";
                        } else if (el2.contains("<")) {
                            var = el2.substring(1, el2.indexOf("<"));
                            op = "<";
                            opp = ">";

                        } else if (el2.contains(">")) {
                            var = el2.substring(1, el2.indexOf(">"));
                            op = ">";
                            opp = "<";
                        }
                        String tmpEl2 = el2;
                        if (el2.contains("!")) {
                            tmpEl2 = el2.substring(1, el2.indexOf(op)) + opp + el2.substring(el2.indexOf(op) + 1, el2.length());
                        }
                        int val = -1;
                        String tmp = el2.substring(el2.indexOf(op) + 1, el2.length() - 1);

                        if (tmp.matches(".*[a-z].*")) {
                            for (Pair<String, Integer> pair : lastUpdate) {
                                if (pair.getFirst().equals(tmp)) {
                                    val = pair.getSecond();

                                }
                            }
                        } else {
                            val = Integer.parseInt(tmp);
                        }

                        if (var.equals(el.getFirst())) {
                            if (tmpEl2.contains("!") && val != el.getSecond()) {
                                counter++;
                            } else if (tmpEl2.contains("<") && el.getSecond() < val) {
                                counter++;
                            } else if (tmpEl2.contains(">") && el.getSecond() < val) {
                                counter++;
                            } else if (tmpEl2.contains("=") && !tmpEl2.contains("!") && el.getSecond() == val) {
                                counter++;
                            }
                        }
                    } else {
                        if (el2.contains("!")) {
                            String var = el2.substring(2, el2.length() - 1);
                            if (var.equals(el.getFirst()) && el.getSecond() == 0) {
                                counter++;
                            }
                        } else {
                            String var = el2.substring(1, el2.length() - 1);
                            if (var.equals(el.getFirst()) && el.getSecond() == 1) {
                                counter++;
                            }
                        }
                    }
                }
            }
            if (orCond && counter > 0) {
                condVerified = true;
            }
            if (counter == conds.length) {
                condVerified = true;
            }
        } else {
            condVerified = true;
        }
        if (condVerified) { // TODO: add check on the condition
            ArrayList<ArrayList<Pair<String, Integer>>> newVals = new ArrayList<>();
            int countVar = 0;
            for (String el : updates) {
                if (el.contains("[")) {
                    countVar++;
                }
            }

            for (String el : updates) {
                if (!el.equals(" ")) {
                    newVals = new ArrayList<>();
                    String[] oldUps = el.split("&");
                    String[] newUps = null;
                    int evenIndex = 0; // Initialize index for even positions
                    int oddIndex = 1;  // Initialize index for odd positions
                    ArrayList<String[]> upsArray = new ArrayList<>();
                    if (el.contains("[")) {
                        for (int p = 1; p <= upperBound; p++) {
                            newUps = new String[oldUps.length];
                            for (int i = 0; i < oldUps.length; i++) {
                                if (oldUps[i].contains("[")) {
                                    String newString = Functions.returnStringNewIndex(oldUps[i], p, upperBound);
                                    newUps[i] = newString;
                                } else {
                                    newUps[i] = oldUps[i];
                                }
                            }
                            upsArray.add(newUps);
                        }

                    } else {
                        upsArray.add(el.split("&"));
                    }

                    for (String[] ups : upsArray) {
                        ArrayList<Pair<String, Integer>> newValList = new ArrayList<>();
                        for (String el2 : ups) {

                            String op = null;
                            int val = -1;
                            String var = el2.substring(1, el2.indexOf("=") - 1);
                            String var2 = el2.substring(el2.indexOf("=") + 1, el2.length() - 1);
                            if (el2.contains("+")) { //TODO: add different operations
                                op = String.valueOf(el2.charAt(el2.indexOf("+")));
                                val = Integer.parseInt(el2.substring(el2.indexOf("+") + 1, el2.length() - 1));
                            } else if (var2.matches(".*[a-z].*")) {
                                for (Pair<String, Integer> pair : lastUpdate) {
                                    if (pair.getFirst().equals(var2)) {
                                        val = pair.getSecond();
                                    }
                                }
                            } else {
                                val = Integer.parseInt(var2);
                            }

                            for (Pair<String, Integer> pair : lastUpdate) {
                                String maxVal = "";
                                for (Pair<String, String> c : consts) {
                                    if (c.getFirst().equals(var)) {
                                        maxVal = c.getSecond();

                                    }
                                }
                                if (pair.getFirst().equals(var)) {
                                    int res = -1;
                                    boolean added = false;
                                    if (op == null && val <= Integer.parseInt(maxVal)) {
                                        res = val;
                                        added = true;
                                    } else {
                                        if (pair.getSecond() + val <= Integer.parseInt(maxVal)) {
                                            res = pair.getSecond() + val;
                                            added = true;
                                        }
                                    }
                                    if (added) {
                                        newValList.add(new Pair(var, res));
                                    }

                                }
                            }
                        }
                        newVals.add(newValList);
                    }
                }
                else{
                    newVals.add(new ArrayList<>());
                }

                ArrayList<String> roles = new ArrayList<>();
                if (role.contains("[")) {
                    for (int p = 1; p <= upperBound; p++) {
                        roles.add(Functions.returnStringNewIndex(role, p, upperBound));
                    }
                } else {
                    roles.add(role);
                }

                for (int i = 0; i < roles.size(); i++) {
                    for (Pair<String, Integer> el2 : lastUpdate) { // TODO: add parametrization of roles
                        Pair<String, Integer> toAdd = null;
                        if (el2.getFirst().equals(roles.get(i))) {
                            if (cont instanceof RecNode) {
                                boolean found = false;
                                for (int tt = 0; tt < recValues.size() && !found; tt++) {
                                    if (recValues.get(tt).getFirst().equals(cont.toPrint())) {
                                        found = true;
                                        boolean foundArray = false;
                                        for (Pair pair2 : recValues.get(tt).getSecond()) {
                                            if (pair2 != null && pair2.getFirst().equals(roles.get(i))) {
                                                foundArray = true;
                                                toAdd = new Pair(roles.get(i), pair2.getSecond());
                                            }
                                        }
                                        if (!foundArray) {
                                            recValues.get(tt).getSecond().add(new Pair(roles.get(i), el2.getSecond() + 1));
                                            toAdd = new Pair(roles.get(i), el2.getSecond() + 1);
                                        }
                                        newVals.get(i).add(toAdd);

                                    }
                                }
                                if (!found) {
                                    toAdd = new Pair(roles.get(i), el2.getSecond() + 1);
                                    newVals.get(i).add(toAdd);
                                    ArrayList<Pair<String, Integer>> newRecs = new ArrayList<>();
                                    newRecs.add(toAdd);
                                    recValues.add(new Pair(cont.toPrint(), newRecs));
                                }
                            } else if (cont instanceof EndNode) { // TODO: to fix
                                toAdd = new Pair(roles.get(i), 100);
                                newVals.get(i).add(toAdd);
                            } else {
                                if (((int) el2.getSecond()) != 100) {
                                    toAdd = new Pair(roles.get(i), el2.getSecond() + 1);
                                    newVals.get(i).add(toAdd);
                                }
                            }


                        }
                    }
                }


                ArrayList<Pair<String, Integer>> ordered = new ArrayList<Pair<String, Integer>>();
                for (int kk = 0; kk < lastUpdate.size(); kk++) {
                    ordered.add(null);
                }
                for (int jj = 0; jj < newVals.size(); jj++) {

                    for (int ii = 0; ii < lastUpdate.size(); ii++) {

                        boolean found = false;
                        for (int kk = 0; kk < newVals.get(jj).size(); kk++) {
                            if (newVals.get(jj).get(kk).getFirst().equals(lastUpdate.get(ii).getFirst()) && newVals.get(jj).get(kk).getSecond() != lastUpdate.get(ii).getSecond()) {
                                ordered.set(ii, newVals.get(jj).get(kk));
                                found = true;
                            }
                        }
                        if (!found) {
                            ordered.set(ii, lastUpdate.get(ii));
                        }
                    }
                    ArrayList<Pair<String, Integer>> tmpList = new ArrayList<>();
                    for (Pair pair : ordered) {
                        tmpList.add(pair);
                    }
                    Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet(rates.get(0), lastUpdate, tmpList);
                    if (!states.contains(toAdd)) {
                        states.add(toAdd);
                    }
                }
            }
        }

        return new Pair(recValues, states);
    }

    @Override
    public Matrix generateMarkovChain(ArrayList<Node> mods) {
        return null;
    }

    @Override
    public ArrayList<Pair<String, ArrayList<String>>> generatePrismCode
            (ArrayList<Pair<String, ArrayList<String>>> code, int index, int maxIndex, String
                    prot, ArrayList<Node> mods, ArrayList<Pair<String, ArrayList<State>>> states, ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String, ArrayList<Node>>> stms, Pair<String, State> lastUpdate, ArrayList<Pair<String, String>> consts) {
        return null;
    }
}
