package ast;

import java.util.ArrayList;
import java.util.Iterator;

import lib.*;

public class IfThenElseNode implements Node {

    private ArrayList<String> roles;
    private ArrayList<String> conds;
    private Node thenStat;
    private Node elseStat;

    public IfThenElseNode(ArrayList<String> r, ArrayList<String> c, Node then, Node elseS) {
        roles = r;
        conds = c;
        thenStat = then;
        elseStat = elseS;
    }

    @Override
    public ArrayList<String> getRoles() {

        return roles;
    }

    @Override
    public Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String, ArrayList<Node>>> stms, ArrayList<Pair<String, Integer>> lastUpdate, ArrayList<Pair<String, String>> consts) {
        for(Pair<String,Integer> el : lastUpdate){
            if(el.getSecond()==100){
                return new Pair(recValues,states);
            }
        }
        String cond = "";
        ArrayList<Pair<String, Integer>> condRoles = new ArrayList<>();
        for (Pair<String, Integer> pair : lastUpdate) {
            for (String role : roles) {
                if (pair.getFirst().equals(role)) {
                    condRoles.add(pair);
                }
            }
        }

        for (String el : conds) {
            String[] tmp = null;
            if (el.contains("&")) {
                tmp = el.split("&");
                for (String el2 : tmp) { // TODO: add other operators
                    if (el2.contains("|")) {
                        String[] tmp2 = el2.split("\\|");
                        for (String el3 : tmp2) {
                            cond = cond + "|" + (el3.substring(1, el3.length() - 1));
                        }
                    } else {
                        cond = cond + "&" + (el2.substring(1, el2.length() - 1));
                    }
                }
            }
            else if (el.contains("|")) {
                tmp = el.split("\\|");
                for (String el2 : tmp) { // TODO: add other operators
                    cond = cond + "|" + (el2.substring(1, el2.length() - 1));
                }
            }
            else {
                cond = cond + (el.substring(1, el.length() - 1));
            }
            cond = cond + "&";
        }
        ListPair newList = new ListPair();
        for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el : new ArrayList<Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>>>(states.getList())) {
            boolean verifiesState = true;
            ArrayList<Pair<String, Integer>> newState = new ArrayList<>();

            for (Pair<String, Integer> pair : el.getThird()) {
                if (roles.contains(pair.getFirst())) {

                    int index = -1;
                    for (int i = 0; i < condRoles.size(); i++) {

                        if (condRoles.get(i).getFirst().equals(pair.getFirst())) {
                            index = i;
                        }
                    }

                    if (index==-1 || condRoles.get(index).getSecond() != pair.getSecond()) {
                        verifiesState = false;
                    }

                } else {
                    newState.add(pair);
                }

            }

            if (verifiesState) {
                Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> toRet = null;
                if (states.stateVerifies(el.getThird(), cond, consts)) {

                    if (thenStat instanceof EndNode) {
                        for (int ii = 0; ii < el.getThird().size(); ii++) {
                            if (roles.contains(el.getThird().get(ii).getFirst())) {
                                newState.add(ii, new Pair(el.getThird().get(ii).getFirst(), 100));
                            }
                        }

                        Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet("1", el.getThird(), newState);
                        if (!states.contains(toAdd)) {
                            states.add(toAdd);
                        }
                        return new Pair(states, recValues);
                    }
                    else if (thenStat instanceof RecNode) {
                        for (int kk = 0; kk < roles.size(); kk++) {
                            for (int ii = 0; ii < el.getThird().size(); ii++) {
                                if (roles.get(kk).equals(el.getThird().get(ii).getFirst())) {
                                    boolean found = false;

                                    for (int tt = 0; tt < recValues.size() && !found; tt++) {

                                        if (recValues.get(tt).getFirst().equals(thenStat.toPrint())) {
                                            found = true;
                                            boolean foundArray = false;
                                            for (Pair pair2 : recValues.get(tt).getSecond()) {
                                                if (pair2 != null && pair2.getFirst().equals(roles.get(kk))) {
                                                    foundArray = true;
                                                    newState.add(ii, new Pair(roles.get(kk), pair2.getSecond()));

												}
                                            }
                                            if (!foundArray) {
                                                recValues.get(tt).getSecond().add(new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                                newState.add(ii, new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                            }

                                        }
                                    }
                                    if (!found) {
                                        ArrayList<Pair<String, Integer>> newRecs = new ArrayList<>();
                                        newState.add(ii, new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                        newRecs.add(new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                        recValues.add(new Pair(thenStat.toPrint(), newRecs));
                                    }

                                }
                            }
                        }

                        Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet("1", el.getThird(), newState);
                        if (!states.contains(toAdd)) {
                            states.add(toAdd);
                        }
                        mods = new ArrayList<>();
                        for (Pair<String, ArrayList<Node>> pair : stms) {
                            if (pair.getFirst().equals(thenStat.toPrint())) {
                                mods = pair.getSecond();
                            }
                        }
                        for (Node stm : mods) {
                            toRet = stm.generateStates(mods, states, recValues, moduleNames, stms, newState, consts);
                            states = toRet.getSecond();
                            recValues = toRet.getFirst();
                        }
                    }
                    else if(thenStat instanceof InternalActionNode){
                        ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> tmpRec = recValues;
                        ListPair tmpStates = states;
                        for(int ii=0; ii<lastUpdate.size(); ii++){
                            boolean found = false;
                            for(Pair pair : newState){
                                if(pair.getFirst().equals(lastUpdate.get(ii).getFirst())){
                                    found = true;
                                }
                            }
                            if(!found){
                                newState.add(ii,lastUpdate.get(ii));
                            }
                        }
                        toRet = thenStat.generateStates(mods, states, recValues, moduleNames, stms, newState, consts);
                        String rate = toRet.getSecond().get(0).getFirst();
                        ArrayList<Pair<String,Integer>> newRet = toRet.getSecond().get(0).getThird();

                        Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet(rate, lastUpdate, newRet);
                        if (!states.contains(toAdd)) {
                            states.add(toAdd);
                        }
                        states = tmpStates;
                        recValues = tmpRec;
                        if(((InternalActionNode)thenStat).getStatement() instanceof EndNode){
                            return new Pair(recValues, states);
                        }
                    }
                    else {
                        if(newState.size()!=lastUpdate.size()){
                            for(int ii=0; ii<lastUpdate.size(); ii++){
                                boolean found = false;
                                for(Pair pair : newState){
                                    if(pair.getFirst().equals(lastUpdate.get(ii).getFirst())){
                                        found = true;
                                    }
                                }
                                if(!found){
                                    newState.add(ii,lastUpdate.get(ii));
                                }
                            }
                        }
                        if(!(thenStat instanceof EndNode)) {
                            toRet = thenStat.generateStates(mods, states, recValues, moduleNames, stms, newState, consts);
                            states = toRet.getSecond();
                            recValues = toRet.getFirst();
                        }
                    }
                } else {
                    if (elseStat instanceof EndNode) {
                        for (int ii = 0; ii < el.getThird().size(); ii++) {
                            if (roles.contains(el.getThird().get(ii).getFirst())) {
                                newState.add(ii, new Pair(el.getThird().get(ii), 100));
                            }
                        }
                        Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet("1", el.getThird(), newState);
                        if (!states.contains(toAdd)) {
                            states.add(toAdd);
                        }
                        return new Pair(states, recValues);
                    }
                    if (elseStat instanceof RecNode) {
                        for (int kk = 0; kk < roles.size(); kk++) {
                            for (int ii = 0; ii < el.getThird().size() ; ii++) {
                                if (roles.get(kk).equals(el.getThird().get(ii).getFirst())) {
                                    boolean found = false;

                                    for (int tt = 0; tt < recValues.size() && !found; tt++) {

                                        if (recValues.get(tt).getFirst().equals(elseStat.toPrint())) {
                                            found = true;
                                            boolean foundArray = false;
                                            for (Pair pair2 : recValues.get(tt).getSecond()) {
                                                if (pair2 != null && pair2.getFirst().equals(roles.get(kk))) {
                                                    foundArray = true;
                                                    newState.add(ii, new Pair(roles.get(kk), pair2.getSecond()));
												}
                                            }
                                            if (!foundArray) {
                                                recValues.get(tt).getSecond().add(new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                                newState.add(ii, new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                            }

                                        }
                                    }
                                    if (!found) {
                                        ArrayList<Pair<String, Integer>> newRecs = new ArrayList<>();
                                        newState.add(ii, new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                        newRecs.add(new Pair(roles.get(kk), el.getThird().get(ii).getSecond() + 1));
                                        recValues.add(new Pair(elseStat.toPrint(), newRecs));
                                    }

                                }
                            }
                        }


						Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet("1", el.getThird(), newState);
                        if (!states.contains(toAdd)) {
                            states.add(toAdd);
                        }
                        mods = new ArrayList<>();
                        for (Pair<String, ArrayList<Node>> pair : stms) {
                            if (pair.getFirst().equals(elseStat.toPrint())) {
                                mods = pair.getSecond();
                            }
                        }

                        for (Node stm : mods) {
                            toRet = stm.generateStates(mods, states, recValues, moduleNames, stms, newState, consts);
                            states = toRet.getSecond();
                            recValues = toRet.getFirst();
                        }
                    }
					else if(elseStat instanceof InternalActionNode){
						ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> tmpRec = recValues;
						ListPair tmpStates = states;
						for(int ii=0; ii<lastUpdate.size(); ii++){
							boolean found = false;
							for(Pair pair : newState){
								if(pair.getFirst().equals(lastUpdate.get(ii).getFirst())){
									found = true;
								}
							}
							if(!found){
								newState.add(ii,lastUpdate.get(ii));
							}
						}
						toRet = elseStat.generateStates(mods, states, recValues, moduleNames, stms, newState, consts);
						String rate = toRet.getSecond().get(0).getFirst();
						ArrayList<Pair<String,Integer>> newRet = toRet.getSecond().get(0).getThird();

						Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet(rate, lastUpdate, newRet);
						if (!states.contains(toAdd)) {
							states.add(toAdd);
						}
						states = tmpStates;
						recValues = tmpRec;
                        if(((InternalActionNode)elseStat).getStatement() instanceof EndNode){
                            return new Pair(recValues, states);
                        }
					}
					else {

                        if(newState.size()!=lastUpdate.size()){
                            for(int ii=0; ii<lastUpdate.size(); ii++){
                                boolean found = false;
                                for(Pair pair : newState){
                                    if(pair.getFirst().equals(lastUpdate.get(ii).getFirst())){
                                        found = true;
                                    }
                                }
                                if(!found){
                                    newState.add(ii,lastUpdate.get(ii));
                                }
                            }
                        }
                        toRet = thenStat.generateStates(mods, states, recValues, moduleNames, stms, newState, consts);
                        states = toRet.getSecond();
                        recValues = toRet.getFirst();
                        if(!(elseStat instanceof EndNode)) {
                            toRet = elseStat.generateStates(mods, states, recValues, moduleNames, stms, newState, consts);
                            states = toRet.getSecond();
                            recValues = toRet.getFirst();
                        }

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
    public String toPrint() {
        String toRet = "if ";
        for (String el : conds) {
            toRet = toRet + el;
        }
        toRet = toRet + "{ " + thenStat.toPrint() + " } else  { " + elseStat.toPrint() + " }";
        return toRet;
    }

    @Override
    public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<
            String> labels, String prot) {
        ArrayList<String> rolesTmp = new ArrayList<String>();
        ArrayList<String> condsTmp = new ArrayList<String>();

        for (String el : roles) {
            rolesTmp.add(Functions.changeIndex(el, index, maxIndex));
        }
        for (String el : conds) {
            condsTmp.add(Functions.returnStringNewIndex(el, index, maxIndex));
        }
        for (int i = 0; i < roles.size(); i++) {
            for (Node el2 : mods) {
                if (el2.toPrint().equals(rolesTmp.get(i))) {
                    int state = ((ModuleNode) el2).getValueRecursion(prot);
                    if (state == -1) {
                        state = ((ModuleNode) el2).getMaxState();
                        ((ModuleNode) el2).setValueRecursion(prot, state);
                    } else {

                        if (((ModuleNode) el2).getLastState() == -1) {
                            state = ((ModuleNode) el2).getNewState(prot);
                            String tmp = rolesTmp.get(i) + "=";
                            String tmpStm = tmp + (state - 1);
                            int indexCont = -1;
                            boolean flag = false;
                            for (String stm : ((ModuleNode) el2).getCommands()) {
                                if (stm.contains(tmpStm)) {
                                    flag = true;
                                }
                            }
                            if (!flag) {
                                state--;
                            } else {
                                ((ModuleNode) el2).removeNewState(prot);
                            }
                            ((ModuleNode) el2).setValueRecursion(prot, state);

                        } else {
                            if (((ModuleNode) el2).getMaxValueRecursion(prot) < ((ModuleNode) el2).getNewState(prot)) {
                                state = ((ModuleNode) el2).getNewState(prot);
                                if (state == 1) {
                                    state = state + 1;
                                }
                                while ((state - 1) >= ((ModuleNode) el2).getNewState(prot)) {
                                    ((ModuleNode) el2).removeNewState(prot);
                                }

                            } else {

                                state = ((ModuleNode) el2).getMaxValueRecursion(prot);

                            }

                        }


                    }
                    String tmp = rolesTmp.get(i) + "=";
                    String tmpStm = tmp + state;
                    int indexCont = -1;
                    for (String stm : ((ModuleNode) el2).getCommands()) {
                        if (stm.contains(tmpStm)) {
                            state++;
                            tmpStm = tmp + state;
                        }
                    }
                    String stat = "";
                    boolean ifte = false;
                    for (String comms : ((ModuleNode) el2).getCommands()) {
                        if (comms.contains("IFTE")) {
                            stat = comms.substring(0, comms.indexOf(" -> IFTE"));
                            ifte = true;
                        }
                    }
                    String stat2 = "";
                    boolean ifte2 = false;
                    for (String comms : ((ModuleNode) el2).getCommands()) {
                        if (comms.contains("IFTE")) {
                            stat2 = comms.substring(0, comms.indexOf(" -> IFTE"));
                            ifte2 = true;
                        }
                    }

                    if (!ifte) {
                        stat = "[] (" + rolesTmp.get(i) + "=" + state + ")";
                    }
                    int indexTmp = stat.indexOf(tmp);
                    String statTmp = stat;

                    stat = stat + "&" + condsTmp.get(i) + " -> ";

					/*if(thenStat instanceof RecNode){

						int stateRec = ((ModuleNode) el2).getValueRecursion(thenStat.toPrint());
						if(stateRec == -1) {

							((ModuleNode) el2).setValueRecursion(thenStat.toPrint(),state+1);
							state = state + 1 ;
						}
						else {
							state = stateRec;
						}

					}*/
                    String statNew = "";

                    int stateNew = Character.getNumericValue(stat.charAt(indexTmp + tmp.length())) + 1;
                    if (stateNew <= ((ModuleNode) el2).getMaxFinState()) {

                        stateNew = ((ModuleNode) el2).getMaxFinState() + 1;

                    }
                    if (thenStat instanceof RecNode) {
                        stateNew = ((ModuleNode) el2).getValueRecursion(thenStat.toPrint());
                    }
                    if (thenStat instanceof EndNode || stateNew == -1) {
                        stat = stat + "(" + rolesTmp.get(i) + "'=" + "TBD" + ");";
                    } else {
                        stat = stat + "1 : (" + rolesTmp.get(i) + "'=" + stateNew + ");";
                        if (!(thenStat instanceof RecNode)) {
                            statNew = "[] (" + rolesTmp.get(i) + "=" + stateNew + ") -> " + "IFTE";
                        }
                    }
                    Iterator<String> itr = ((ModuleNode) el2).getCommands().iterator();
                    while (itr.hasNext()) {
                        String comms = itr.next();
                        if (comms.contains("IFTE") && ifte && comms.substring(0, comms.indexOf(" -> IFTE")).equals(statTmp)) {
                            itr.remove();
                        }
                    }
                    if (thenStat instanceof IfThenElseNode) {
                        ((ModuleNode) el2).setNewStateIndex(prot, stateNew, 0);
                    } else {
                        ((ModuleNode) el2).setNewState(prot, stateNew);
                    }
                    ((ModuleNode) el2).addCommand(statNew);
                    ((ModuleNode) el2).addCommand(stat);
                    if (thenStat instanceof EndNode || thenStat instanceof RecNode) {
                        ((ModuleNode) el2).setLastState(-1);
                    }
                    thenStat.generateCode(mods, index, maxIndex, isCtmc, labels, prot);

                    if (!ifte2) {
                        stat2 = "[] (" + rolesTmp.get(i) + "=" + state + ")";

                    }

                    String tmp2 = rolesTmp.get(i) + "=";
                    int indexTmp2 = stat2.indexOf(tmp);
                    String statTmp2 = stat2;
                    stat2 = stat2 + "&!" + condsTmp.get(i) + " -> ";

                    String statNew2 = "";
                    int stateNew2 = Character.getNumericValue(stat2.charAt(indexTmp2 + tmp2.length())) + 1;
                    if (stateNew2 <= ((ModuleNode) el2).getMaxFinState()) {
                        stateNew2 = ((ModuleNode) el2).getMaxFinState() + 1;
                    }
                    if (stateNew2 == stateNew) {
                        stateNew2++;
                    }
                    if (elseStat instanceof EndNode || elseStat instanceof RecNode) {
                        ((ModuleNode) el2).setLastState(-1);
                    }
                    if (elseStat instanceof RecNode) {
                        stateNew2 = ((ModuleNode) el2).getValueRecursion(elseStat.toPrint());
                    }
                    if (elseStat instanceof EndNode || stateNew2 == -1) {
                        stat2 = stat2 + "1 : (" + rolesTmp.get(i) + "'=" + "TBD" + ");";
                    } else {
                        stat2 = stat2 + "1 : (" + rolesTmp.get(i) + "'=" + stateNew2 + ");";
                        if (!(elseStat instanceof RecNode)) {
                            statNew2 = "[] (" + rolesTmp.get(i) + "=" + stateNew2 + ") -> " + "IFTE";
                        }
                    }

                    Iterator<String> itr2 = ((ModuleNode) el2).getCommands().iterator();
                    while (itr2.hasNext()) {
                        String comms = itr2.next();
                        if (comms.contains("IFTE") && ifte2 && comms.substring(0, comms.indexOf(" -> IFTE")).equals(statTmp2)) {
                            itr.remove();
                        }
                    }

                    //((ModuleNode) el2).setNewState(prot, state);
                    if (elseStat instanceof IfThenElseNode) {
                        ((ModuleNode) el2).setNewStateIndex(prot, stateNew2, 0);
                    } else {
                        ((ModuleNode) el2).setNewState(prot, stateNew2);
                    }

                    ((ModuleNode) el2).addCommand(stat2);
                    ((ModuleNode) el2).addCommand(statNew2);
                    elseStat.generateCode(mods, index, maxIndex, isCtmc, labels, prot);

                }
            }
        }

        return null;
    }

    @Override
    public ArrayList<Pair<String,ArrayList<String>>> generatePrismCode(ArrayList<Pair<String,ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String,ArrayList<State>>> states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, Pair<String,State> lastUpdate, ArrayList<Pair<String,String>> consts){
        boolean found = false;
        String newState = "";
        State newStates = new State();
        ArrayList<Pair<String,String>> codesIf = new ArrayList<>();
        ArrayList<Pair<String,String>> codesElse = new ArrayList<>();

        for(int i=0; i<conds.size(); i++) {

            int max = -1;
            for(Pair<String,ArrayList<State>> state : states){
                if(state.getFirst().equals(prot)){
                    found = true;
                    for(int j=0;j<state.getSecond().size();j++){
                        if(state.getSecond().get(j).getModuleState(roles.get(i))>max) {
                            max = state.getSecond().get(j).getModuleState(roles.get(i));
                        }
                    }
                }
            }
            newStates.addState(new Pair(roles.get(i),max));
            String toAdd = "[] (" + roles.get(i) + "=" + max + ")";
            String toAdd2 = "[] (" + roles.get(i) + "=" + max + ")";

            for(Pair<String,ArrayList<String>> _code : code){
                if(_code.getFirst().equals(roles.get(i))){
                    for(int ii=0; ii<_code.getSecond().size(); ii++){
                        if(_code.getSecond().get(ii).contains("IFTE")){
                            toAdd = _code.getSecond().get(ii).substring(0,_code.getSecond().get(ii).indexOf(" -> IFTE")) ;
                            toAdd2 = _code.getSecond().get(ii).substring(0,_code.getSecond().get(ii).indexOf(" -> IFTE")) ;
                        }
                    }
                }
            }
            if(!conds.get(i).equals(" ")){
                toAdd = toAdd + "&" + conds.get(i) + " -> IFTE";
                toAdd2 = toAdd2 + "&!(" + conds.get(i) + ") -> IFTE";
            }

            codesIf.add(new Pair(roles.get(i),toAdd));
            codesElse.add(new Pair(roles.get(i),toAdd2));

        }
        for(Pair<String,String> el : codesIf) {
            for (Pair<String, ArrayList<String>> pair : code) {
                if (pair.getFirst().equals(el.getFirst())){
                    pair.getSecond().add(el.getSecond());
                }
            }
        }
        if(!(thenStat instanceof EndNode)){
            code = thenStat.generatePrismCode(code,index,maxIndex,prot,mods,states,recValues,moduleNames, stms, new Pair(prot,newStates), consts);
        }
        for(Pair<String,String> el : codesElse) {
            for (Pair<String, ArrayList<String>> pair : code) {
                if (pair.getFirst().equals(el.getFirst())){
                    pair.getSecond().add(el.getSecond());
                }
            }
        }
        if(!(elseStat instanceof EndNode)){
            code = elseStat.generatePrismCode(code,index,maxIndex,prot,mods,states,recValues,moduleNames, stms, new Pair(prot,newStates), consts);
        }

        return code;
    }

}
