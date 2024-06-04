package ast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import lib.*;

import javax.xml.stream.events.Characters;

public class BranchNode implements Node {

    private String role;
    private ArrayList<String> outRole;
    private boolean branch;
    private ArrayList<String> rates;
    private ArrayList<Node> updates;
    private ArrayList<Node> preconditions;
    private ArrayList<Node> statements;
    private boolean isCtmc ;

    public BranchNode(String _role, ArrayList<String> _outRole, boolean _branch, ArrayList<String> _rates, ArrayList<Node> _preconditions, ArrayList<Node> _updates, ArrayList<Node> _statements) {
        role = _role;
        outRole = _outRole;
        branch = _branch;
        rates = _rates;
        preconditions = _preconditions;
        updates = _updates;
        statements = _statements;
    }

    public void setCtmc(boolean _isCtmc){
        isCtmc = _isCtmc;
    }

    @Override
    public String toPrint() {
        // TODO Auto-generated method stub
        return "branch";
    }

    @Override
    public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {

        Functions funs = new Functions();
        String roleTmp = funs.changeIndex(role, index, maxIndex);
        ArrayList<String> outRolesTmp = new ArrayList<String>();
        int varAdd = 0;
        for (String el : outRole) {
            outRolesTmp.add(Functions.changeIndex(el, index, maxIndex));
        }
        int iA = -1;
        for (int i = 0; i < mods.size(); i++) {
            if (mods.get(i).toPrint().equals(roleTmp)) {
                iA = i;
            }
        }
        String statementA = null;
        String label = "";
        ArrayList<String> stats = new ArrayList<String>();
        for (String el : outRolesTmp) {
            stats.add(null);
        }
        boolean sameRole = false;
        ArrayList<Integer> allA = new ArrayList<Integer>();
        ArrayList<Integer> allB = new ArrayList<Integer>();
        int stateCtmc = -1;
        ArrayList<Integer> statesB = new ArrayList<Integer>();
        for (int k = 0; k < rates.size(); k++) {
            String stAprob = "";
            boolean firstLabel = false;
            sameRole = false;
            for (String el : outRolesTmp) {
                if (el.equals(roleTmp)) {
                    sameRole = true;
                }
            }
            boolean stateUsed = false;
            if (!sameRole && (!isCtmc) || isCtmc) {
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
            }
            int checkStateUsed = -1;
            int stateA = ((ModuleNode) mods.get(iA)).getValueRecursion(prot);

            if (stateA == -1) {
                stateA = ((ModuleNode) mods.get(iA)).getMaxState();
                ((ModuleNode) mods.get(iA)).setValueRecursion(prot, stateA);

            } else {

                int stateTmpNew = ((ModuleNode) mods.get(iA)).getNewState(prot);

                //if(((ModuleNode) mods.get(iA)).getMaxValueRecursion(prot)<=stateTmpNew && ((ModuleNode) mods.get(iA)).getNewState(prot)!=-1) {
                if (((ModuleNode) mods.get(iA)).getLastState() == -1) {
                    if (stateTmpNew != -1) {
                        stateA = stateTmpNew;
                    } else {
                        stateA = ((ModuleNode) mods.get(iA)).getMaxValueRecursion(prot) + 1;
                    }
                    checkStateUsed = stateA;
                    ((ModuleNode) mods.get(iA)).setLastState(stateA);
                } else {
                    if (((ModuleNode) mods.get(iA)).getMaxValueRecursion(prot) < ((ModuleNode) mods.get(iA)).getNewState(prot)) {
                        stateA = ((ModuleNode) mods.get(iA)).getNewState(prot);
                        stateA++;
                        while ((stateA) >= ((ModuleNode) mods.get(iA)).getNewState(prot)) {
                            ((ModuleNode) mods.get(iA)).removeNewState(prot);

                        }

                    } else {

                        stateA = ((ModuleNode) mods.get(iA)).getMaxValueRecursion(prot);
                    }
                }


            }
            if (isCtmc && k == 0) {
                stateCtmc = stateA;
            }

            if (isCtmc) {
                statementA = null;
            }
            int indexRate = rates.get(k).indexOf("*");
            String rateA = funs.changeIndex(rates.get(k).substring(0, indexRate), index, maxIndex);
            boolean ifte = false;
            for (String el : ((ModuleNode) mods.get(iA)).getCommands()) {
                if (el.contains("IFTE")) {
                    statementA = el.substring(0, el.indexOf("IFTE"));
                    ifte = true;
                }
            }

            if (statementA == null && (isCtmc || sameRole)) {
                if (isCtmc) {
                    if (rates.size() > 1) {

                        statementA = "[" + label + "] (" + roleTmp + "=" + stateCtmc + ") -> ";

                    } else {

                        statementA = "[" + label + "] (" + roleTmp + "=" + stateA + ") -> ";

                    }
                } else {
                    statementA = "[" + label + "] (" + roleTmp + "=" + stateA + ") -> ";
                }
            } else if (statementA == null && !isCtmc) {
                statementA = "[] (" + roleTmp + "=" + stateA + ") -> ";
                stAprob = "[" + label + "] (" + roleTmp + "=";

            } else if (statementA != null && !isCtmc) {
                if (!sameRole) {
                    stAprob = "[" + label + "] (" + roleTmp + "=";
                }
                if (!ifte) {
                    statementA = statementA + " + ";
                }

            }
            String tmpStr = roleTmp + "=";
            int indexTmp = statementA.indexOf(tmpStr);
            int stateNewA = Character.getNumericValue(statementA.charAt(indexTmp + tmpStr.length())) + 1;

            if (stateNewA == checkStateUsed) {
                ((ModuleNode) mods.get(iA)).removeNewState(prot);

            }
            statementA = statementA + rateA + " : ";

            String upA = "";
            String genUpdates = updates.get(k).generateCode(mods, index, maxIndex, isCtmc, labels, prot);
            if (!genUpdates.equals(" ")) {
                int indexUp = genUpdates.indexOf("&&");
                if (!genUpdates.substring(0, indexUp).equals(" ")) {
                    upA = Functions.returnStringNewIndex(genUpdates.substring(0, indexUp), index, maxIndex) + "&";

                }
            }
            boolean roleAContained = false;
            for (String tmp : statements.get(k).getRoles()) {
                if (roleTmp.equals(funs.changeIndex(tmp, index, maxIndex))) {
                    roleAContained = true;
                }
            }

            if (statements.get(k) instanceof RecNode) {

                int stateRec = ((ModuleNode) mods.get(iA)).getValueRecursion(statements.get(k).toPrint());
                if (stateRec == -1) {

                    ((ModuleNode) mods.get(iA)).setValueRecursion(statements.get(k).toPrint(), stateA + k + 1);
                    allA.add(stateA + k + 1);
                    stateA = stateA + k + 1;
                } else {

                    allA.add(stateA);
                    stateA = stateRec;
                }

            } else if ((!(statements.get(k) instanceof EndNode) && roleAContained) || !isCtmc) {

                stateA = ((ModuleNode) mods.get(iA)).getMaxState() + k + 1;
                allA.add(((ModuleNode) mods.get(iA)).getMaxState() + k + 1);
            }
            if (statements.get(k) instanceof EndNode || statements.get(k) instanceof RecNode) {
                ((ModuleNode) mods.get(iA)).setLastState(-1);
            }
            if (isCtmc || sameRole) {
                boolean tbd = false;
                statementA = statementA + upA + "(" + roleTmp + "'=";
                if (statements.get(k) instanceof EndNode) {
                    statementA = statementA + "TBD";
                    allA.add(stateA + 1);
                    tbd = true;
                } else if (!(statements.get(k) instanceof RecNode)) {
                    stateA = stateA + 1;
                }
                if (!tbd) {
                    statementA = statementA + stateA;
                }
                allA.add(stateA);

                statementA = statementA + ")";

            } else {

                statementA = statementA + "(" + roleTmp + "'=" + (stateA) + ")";
                if (statements.get(k) instanceof EndNode) {
                    stAprob = stAprob + (stateA) + ") -> 1 : " + upA + "(" + roleTmp + "'=" + "TBD" + ")";
                    allA.add(stateA + 1);

                } else {
                    stAprob = stAprob + (stateA) + ") -> 1 : " + upA + "(" + roleTmp + "'=" + (stateA + rates.size()) + ")";
                    allA.add(stateA + rates.size());

                }
                stateA = stateA + 1;

            }

            int oldState = -1;
            for (int kk = 0; kk < outRolesTmp.size(); kk++) {

                if (!sameRole) {

                    String stBprob = "";
                    allB = new ArrayList<Integer>();
                    int iB = -1;
                    for (int i = 0; i < mods.size(); i++) {
                        if (mods.get(i).toPrint().equals(outRolesTmp.get(kk))) {
                            iB = i;
                        }
                    }

                    int indexRateB = rates.get(0).indexOf("*");
                    String rateB = funs.changeIndex(rates.get(k).substring(indexRate + 1, rates.get(k).length()), index, maxIndex);

                    String statementB = stats.get(kk);
                    boolean ifteB = false;
                    for (String el2 : ((ModuleNode) mods.get(iB)).getCommands()) {
                        if (el2.contains("IFTE")) {
                            statementB = el2.substring(0, el2.indexOf("IFTE"));
                            ifteB = true;
                        }
                    }
                    int stateB = ((ModuleNode) mods.get(iB)).getValueRecursion(prot);

                    if (stateB == -1) {
                        stateB = ((ModuleNode) mods.get(iB)).getMaxState();
                        ((ModuleNode) mods.get(iB)).setValueRecursion(prot, stateB);
                    } else {

                        if (((ModuleNode) mods.get(iB)).getMaxValueRecursion(prot) <= ((ModuleNode) mods.get(iB)).getNewState(prot) && ((ModuleNode) mods.get(iB)).getNewState(prot) != -1) {
                            stateB = ((ModuleNode) mods.get(iB)).getNewState(prot);
                            ((ModuleNode) mods.get(iB)).setValueRecursion(prot, stateB);
                            ((ModuleNode) mods.get(iB)).removeNewState(prot);


                        } else {
                            stateB = ((ModuleNode) mods.get(iB)).getMaxValueRecursion(prot);
                        }

                    }

                    if (isCtmc && k == 0) {
                        statesB.add(kk, stateB);
                    }

                    if (isCtmc) {
                        if (rates.size() == 1) {
                            statementB = "[" + label + "] (" + outRolesTmp.get(kk) + "=" + stateB + ") -> ";
                        } else {
                            statementB = "[" + label + "] (" + outRolesTmp.get(kk) + "=" + statesB.get(kk) + ") -> ";
                            if (k > 0) {
                                allB.add(stateB);
                            }

                        }
                    } else if (statementB == null && !isCtmc) {
                        statementB = "[] (" + outRolesTmp.get(kk) + "=" + stateB + ") -> ";
                        stBprob = "[" + label + "] (" + outRolesTmp.get(kk) + "=";
                    } else if (statementB != null && !isCtmc) {
                        if (!sameRole) {
                            stBprob = "[" + label + "] (" + outRolesTmp.get(kk) + "=";
                        }
                        if (!ifte) {
                            statementB = statementB + " + ";
                        }
                    }
                    statementB = statementB + rateB + " : ";

                    String upB = "";

                    if (!genUpdates.equals(" ")) {
                        int indexUp = genUpdates.indexOf("&&");
                        if (!genUpdates.substring(indexUp + 2, genUpdates.length()).equals(" ")) {
                            upB = Functions.returnStringNewIndex(genUpdates.substring(indexUp + 2, genUpdates.length()), index, maxIndex) + "&";
                        }
                    }
                    boolean roleBContained = false;
                    for (String tmp : statements.get(k).getRoles()) {
                        if (outRolesTmp.get(kk).equals(funs.changeIndex(tmp, index, maxIndex))) {
                            roleBContained = true;
                        }
                    }
                    if (statements.get(k) instanceof RecNode) {

                        int stateRec = ((ModuleNode) mods.get(iB)).getValueRecursion(statements.get(k).toPrint());
                        if (stateRec == -1) {
                            ((ModuleNode) mods.get(iB)).setValueRecursion(statements.get(k).toPrint(), stateB + k);
                            allB.add(stateB + k);
                            stateB = stateB + k;
                        } else {
                            allB.add(stateB);
                            stateB = stateRec;

                        }


                    } else if ((!(statements.get(k) instanceof EndNode) && roleBContained) || !isCtmc) {

                        stateB = ((ModuleNode) mods.get(iB)).getMaxState() + 1;
                        allB.add(stateB);

                    }
                    if (isCtmc) {
                        statementB = statementB + upB + "(" + outRolesTmp.get(kk) + "'=";
                        if (statements.get(k) instanceof EndNode) {
                            statementB = statementB + "TBD";
                            allB.add(stateB + 1);

                        } else {
                            statementB = statementB + stateB;
                        }
                        statementB = statementB + ")";
                    } else {

                        statementB = statementB + "(" + outRolesTmp.get(kk) + "'=" + (stateB) + ")";
                        if (statements.get(k) instanceof EndNode) {
                            stBprob = stBprob + (stateB) + ") -> 1 : " + upB + "(" + outRolesTmp.get(kk) + "'=" + "TBD" + ")";
                            allB.add(stateB + 1);

                        } else {
                            stBprob = stBprob + (stateB) + ") -> 1 : " + upB + "(" + outRolesTmp.get(kk) + "'=" + (stateB + rates.size()) + ")";
                            allB.add(stateB + rates.size());

                        }
                        stateB = stateB + 1;

                    }

                    if (isCtmc || k == rates.size() - 1) {
                        ((ModuleNode) mods.get(iB)).addCommand(statementB + ";");
                    }
                    if (!isCtmc) {
                        ((ModuleNode) mods.get(iB)).addCommand(stBprob + ";");
                    }
                    for (String el : ((ModuleNode) mods.get(iB)).getCommands()) {
                        if (el.contains("IFTE")) {
                            ((ModuleNode) mods.get(iB)).getCommands().remove(el);
                            break;
                        }
                    }
                    for (int el : allB) {
                        ((ModuleNode) mods.get(iB)).setNewState(prot, el);

                    }
                }

            }

            if (isCtmc || k == rates.size() - 1) {
                ((ModuleNode) mods.get(iA)).addCommand(statementA + ";");
            }
            if (!isCtmc && !sameRole) {
                ((ModuleNode) mods.get(iA)).addCommand(stAprob + ";");
            }
            for (String el : ((ModuleNode) mods.get(iA)).getCommands()) {
                if (el.contains("IFTE")) {
                    ((ModuleNode) mods.get(iA)).getCommands().remove(el);
                    break;
                }
            }

        }


        for (int el : allA) {
            ((ModuleNode) mods.get(iA)).setNewState(prot, el);
        }


        for (int k = 0; k < statements.size(); k++) {
            for (String el : outRolesTmp) {
                if (el.equals(roleTmp)) {
                    sameRole = true;
                }
            }
			/*for(String el : outRole) {
				if(!role.equals(el) && !(statements.get(k) instanceof EndNode) && statements.get(k).getRoles().contains(el)) {
					int iB = -1;
					for(int i=0; i<mods.size(); i++) {
						if(mods.get(i).toPrint().equals(funs.changeIndex(el,index,maxIndex))) {
							iB = i;
						}
					}
					((ModuleNode) mods.get(iB)).setState();
					((ModuleNode) mods.get(iB)).setValueRecursion(prot, ((ModuleNode) mods.get(iB)).getState());
				}
			}*/

            statements.get(k).generateCode(mods, index, maxIndex, isCtmc, labels, prot);

        }


        return null;
    }

    @Override
    public ArrayList<String> getRoles() {
        ArrayList<String> roles = new ArrayList<String>();
        for (String el : outRole) {
            roles.add(el);
        }
        roles.add(role);
        return roles;
    }

    @Override
    public ArrayList<Pair<String, ArrayList<String>>> generatePrismCode(ArrayList<Pair<String, ArrayList<String>>> code, int index, int maxIndex, String prot, ArrayList<Node> mods, ArrayList<Pair<String, ArrayList<State>>> states, ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String, ArrayList<Node>>> stms, Pair<String, State> lastUpdate, ArrayList<Pair<String, String>> consts) {


        String roleB = "";
        String roleA = Functions.changeIndex(role, index, maxIndex);
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> outRoles = new ArrayList<>();

        roles.add(roleA);
        for (String el : outRole) { // TODO: there could be more than one output role
            for(int i=1; i<=maxIndex; i++) {
                roleB = Functions.changeIndex(el, i, maxIndex);
                outRoles.add(roleB);
                roles.add(roleB);
            }
        }
        String label = "";
        ArrayList<String> labels = new ArrayList<String>();

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
        Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> toRet = null;
        ArrayList<Pair<String,State>> newRecs = new ArrayList<>();
        State newStates = null;
        ArrayList<String> toAddNotCtmc = new ArrayList<>();
        for (int i = 0; i < updates.size(); i++) {
            newStates = new State();
            boolean firstLabel = false;

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
            String[] rts = new String[roles.size()];
            String[] tmp = rates.get(i).split("\\*");
            for(int ii=0; ii<tmp.length; ii++){
                rts[ii] = tmp[ii];
            }
            if(tmp.length<roles.size()){
                for(int j=tmp.length; j<roles.size(); j++){
                    rts[j] = rts[j-1];
                }
            }
            for(int j=1; j<rts.length; j++){
                rts[j] = Functions.returnStringNewIndex(rts[j], j, maxIndex);
            }
            String[] ups = new String[rts.length];
            if(updates.get(i).toPrint().contains("&&")){
                tmp = updates.get(i).toPrint().split("&&");
                for(int ii=0; ii<tmp.length; ii++){
                    ups[ii] = tmp[ii];
                }
                if(tmp.length<roles.size()){
                    for(int j=tmp.length; j<roles.size(); j++){
                        ups[j] = ups[j-1];
                    }
                }
                for(int j=1; j<ups.length; j++){
                    ups[j] = Functions.returnStringNewIndex(ups[j], j, maxIndex);
                }
            }
            else{
                for(int ii = 0; ii<ups.length; ii++){
                    ups[ii] = "";
                }
            }
            for (int j = 0; j < roles.size(); j++) {
                boolean contained = false;
                for (Pair<String, ArrayList<String>> pair : code) {
                    if (pair.getFirst().equals(roles.get(j))) {
                        String newState = "";
                        if(statements.get(i) instanceof RecNode){
                            boolean found = false;
                            for(Pair<String,ArrayList<State>> state : states){
                                if(state.getFirst().equals(statements.get(i).toPrint())){
                                    found = true;
                                    newState = String.valueOf(state.getSecond().get(0).getModuleState(roles.get(j)));
                                }
                            }
                            if(!found){
                                boolean foundRole = false;
                                for(Pair<String,State> pairNew : newRecs){
                                    if(pairNew.getFirst().equals(statements.get(i).toPrint())){
                                            if(pairNew.getSecond().getModuleState(roles.get(j))!=-1){
                                                newState = String.valueOf(pairNew.getSecond().getModuleState(roles.get(j)));
                                                foundRole = true;
                                            }
                                    }
                                }
                                if(!foundRole){
                                    newState = String.valueOf(initState.getSecond().getModuleState(roles.get(j)) + 1 + i);
                                    boolean added = false;
                                    for(Pair<String,State> pairNew : newRecs) {
                                        if (pairNew.getFirst().equals(statements.get(i).toPrint())) {
                                            pairNew.getSecond().addState(new Pair(roles.get(j), Integer.parseInt(newState)));
                                            added = true;
                                        }
                                    }
                                    if(newRecs.size()==1 || !added){
                                        State stateToAdd = new State();
                                        stateToAdd.addState(new Pair(roles.get(j), Integer.parseInt(newState)));
                                        newRecs.add(new Pair(statements.get(i).toPrint(),stateToAdd));
                                    }
                                }
                            }
                        }
                        else if(statements.get(i) instanceof EndNode){
                            newState = "TBD"; // TODO: change the value of the state
                        }
                        else{
                            newState = String.valueOf(initState.getSecond().getModuleState(roles.get(j)) + 1 + i) ;
                        }
                        if(j==0 || !roles.get(j).equals(roleA)){
                            if(newState.equals("TBD")){
                                newStates.addState(new Pair(roles.get(j), initState.getSecond().getModuleState(roles.get(j)) + 1));
                            }
                            else {
                                newStates.addState(new Pair(roles.get(j), Integer.valueOf(newState)));
                            }
                        }

                        String toAdd = "[";

                        if (!outRoles.get(0).equals(roleA)) { // TODO: fix, this is temporary
                            toAdd = toAdd + label + "] ";
                        } else {
                            toAdd = toAdd + "] ";
                        }
                        int stateToSet = initState.getSecond().getModuleState(roles.get(j));
                        if(stateToSet == -1){ stateToSet = 0;}
                        if(i==0){
                            toAddNotCtmc.add(toAdd + " (" + roles.get(j) + "=" + stateToSet + ") -> ");
                        }
                        toAdd = toAdd + " (" + roles.get(j) + "=" +stateToSet + ") -> " + rts[j] + " : " + ups[j] ;
                        int indexToDel = -1;
                        for(Pair<String,ArrayList<String>> _code : code){
                            if(_code.getFirst().equals(roles.get(j))){
                                for(int ii=0; ii<_code.getSecond().size(); ii++){
                                    if(_code.getSecond().get(ii).contains("IFTE")){
                                        String tmp2 = _code.getSecond().get(ii).substring(0,_code.getSecond().get(ii).indexOf("IFTE")) ;
                                        indexToDel = ii;
                                        if(!isCtmc){
                                            toAdd = tmp2.substring(0,tmp2.indexOf("[")+1) + label + tmp2.substring(tmp2.indexOf("]"),tmp2.length());
                                            toAddNotCtmc.set(j,toAdd);
                                        }
                                        else{
                                            toAdd = tmp2 + rts[j] + " : " + ups[j];
                                        }
                                    }
                                }
                                if(indexToDel!=-1) {
                                    _code.getSecond().remove(indexToDel);
                                }
                            }

                        }
                        if(i==0) {
                            toAddNotCtmc.set(j, toAddNotCtmc.get(j) +  rts[j] + " : " + ups[j] );
                        }
                        else{
                            String toPrint =  rts[j] + " : " + ups[j];
                            if(!toAddNotCtmc.get(j).contains(toPrint)) {
                                toAddNotCtmc.set(j, toAddNotCtmc.get(j) + "+" + toPrint);
                            }
                            else{
                                contained = true;
                            }

                        }

                        if(ups[j]==null || ups[j].isEmpty() || ups[j].equals(" ")){
                            toAdd = toAdd + "(" + roles.get(j) + "'=" + newState + ");";
                            if(!contained) {
                                toAddNotCtmc.set(j, toAddNotCtmc.get(j) + "(" + roles.get(j) + "'=" + newState + ")");
                            }
                            if(i==updates.size()-1 && !contained){
                                toAddNotCtmc.set(j, toAddNotCtmc.get(j)  + ";");
                            }
                        }
                        else{
                            toAdd = toAdd + "&(" + roles.get(j) + "'=" + newState + ");";
                            if(!contained) {
                                toAddNotCtmc.set(j, toAddNotCtmc.get(j) + "&(" + roles.get(j) + "'=" + newState + ")");
                            }
                            if(i==updates.size()-1 && !contained){
                                toAddNotCtmc.set(j, toAddNotCtmc.get(j)  + ";");
                            }
                        }
                        boolean codeContained = false;
                        for(String _code : pair.getSecond()) {
                            if(isCtmc){
                                if(_code.contains(toAdd.substring(toAdd.indexOf("]"),toAdd.length()))){
                                    codeContained = true;
                                }
                            }
                            else{
                                if(_code.contains(toAddNotCtmc.get(j).substring(toAddNotCtmc.get(j).indexOf("]"),toAddNotCtmc.get(j).length()))){
                                    codeContained = true;
                                }
                            }
                        }
                        if (isCtmc && !codeContained && (j == 0 || !roles.get(j).equals(roleA))) {
                            pair.getSecond().add(toAdd);
                        }
                        if (!isCtmc && i == updates.size() - 1 && !codeContained) {
                            pair.getSecond().add(toAddNotCtmc.get(j));
                        }
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
                if(pair.getFirst().equals(statements.get(i).toPrint())){
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

            if(!(statements.get(i) instanceof EndNode)){
                code = statements.get(i).generatePrismCode(code,index,maxIndex,prot,mods,states,recValues,moduleNames, stms, new Pair(prot,newStates), consts);
            }

        }


        return code;
    }


    @Override
    public Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String, ArrayList<Node>>> stms, ArrayList<Pair<String, Integer>> lastUpdate, ArrayList<Pair<String, String>> consts) {
        for (Pair<String, Integer> el : lastUpdate) {
            if (el.getSecond() == 100) {
                return new Pair(recValues, states);
            }
        }

        ArrayList<Pair<String, Integer>> initState = lastUpdate;
        int size = states.getSize();
        Pair<ArrayList<Pair<String, ArrayList<Pair<String, Integer>>>>, ListPair> toRet = null;

        ArrayList<Pair<String, Integer>> newStates = new ArrayList<Pair<String, Integer>>();
        for (int i = 0; i < updates.size(); i++) {
            newStates = new ArrayList<Pair<String, Integer>>();
            String[] ups = updates.get(i).toPrint().split("&&");
            for (int j = 0; j < ups.length; j++) {
                String[] modUps = ups[j].split("&");
                for (int k = 0; k < modUps.length; k++) {
                    String[] vars = modUps[k].split("'=");

                    if (!modUps[k].equals(" ")) {
                        if (vars[1].contains("+")) {
                            String[] tmp = vars[1].split("\\+");
                            for (Pair<String, Integer> pair : initState) {
                                if (pair.getFirst().equals(vars[0].substring(1, vars[0].length()))) {
                                    int index = -1;
                                    for (int kk = 0; kk < consts.size(); kk++) {
                                        if (consts.get(kk).getFirst().equals(vars[0].substring(1, vars[0].length()))) {
                                            index = kk;
                                        }
                                    }
                                    int res = pair.getSecond() + Integer.parseInt(tmp[1].substring(0, tmp[1].length() - 1));

                                    if (index != -1) {
                                        if (res <= Integer.parseInt(consts.get(index).getSecond())) {
                                            newStates.add(new Pair(vars[0].substring(1, vars[0].length()), res));
                                        } else {
                                            newStates.add(new Pair(vars[0].substring(1, vars[0].length()), pair.getSecond()));
                                        }
                                    } else {
                                        newStates.add(new Pair(vars[0].substring(1, vars[0].length()), pair.getSecond()));
                                    }
                                }
                            }
                        } else if (vars[1].contains("-")) {
                            String[] tmp = vars[1].split("\\-");
                            for (Pair<String, Integer> pair : initState) {
                                if (pair.getFirst().equals(vars[0].substring(1, vars[0].length()))) {
                                    int res = pair.getSecond() - Integer.parseInt(tmp[1].substring(0, tmp[1].length() - 1));
                                    newStates.add(new Pair(vars[0].substring(1, vars[0].length()), res));
                                }
                            }
                        } else if (Character.isDigit(vars[1].substring(0, vars[1].length() - 1).charAt(0))) {
                            newStates.add(new Pair(vars[0].substring(1, vars[0].length()), Integer.parseInt(vars[1].substring(0, vars[1].length() - 1))));
                        } else {
                            if (String.valueOf(vars[1].substring(0, vars[1].length() - 1).charAt(0)).equals("t")) {
                                newStates.add(new Pair(vars[0].substring(1, vars[0].length()), 1));
                            } else {
                                newStates.add(new Pair(vars[0].substring(1, vars[0].length()), 0));
                            }
                        }
                    }
                }
            }

            for (Pair<String, Integer> el : initState) { // TODO: add parametrization of roles
                Pair<String, Integer> toAdd = null;
                if (el.getFirst().equals(role)) {
                    if (statements.get(i) instanceof RecNode) {
                        boolean found = false;
                        for (int tt = 0; tt < recValues.size() && !found; tt++) {
                            if (recValues.get(tt).getFirst().equals(statements.get(i).toPrint())) {
                                found = true;
                                boolean foundArray = false;
                                for (Pair pair2 : recValues.get(tt).getSecond()) {
                                    if (pair2 != null && pair2.getFirst().equals(role)) {
                                        foundArray = true;
                                        toAdd = new Pair(role, pair2.getSecond());
                                    }
                                }
                                if (!foundArray) {
                                    recValues.get(tt).getSecond().add(new Pair(role, el.getSecond() + 1));
                                    toAdd = new Pair(role, el.getSecond() + 1);
                                }
                                newStates.add(toAdd);

                            }
                        }
                        if (!found) {
                            toAdd = new Pair(role, el.getSecond() + 1);
                            newStates.add(toAdd);
                            ArrayList<Pair<String, Integer>> newRecs = new ArrayList<>();
                            newRecs.add(toAdd);
                            recValues.add(new Pair(statements.get(i).toPrint(), newRecs));
                        }
                    } else if (statements.get(i) instanceof EndNode) { // TODO: to fix
                        toAdd = new Pair(role, 100);
                        newStates.add(toAdd);
                    } else {
                        toAdd = new Pair(role, el.getSecond() + i + 1);
                        newStates.add(toAdd);
                    }


                } else {
                    for (int kk = 0; kk < outRole.size(); kk++) {
                        if (el.getFirst().equals(outRole.get(kk))) {

                            if (statements.get(i) instanceof RecNode) {

                                boolean found = false;
                                for (int tt = 0; tt < recValues.size() && !found; tt++) {
                                    if (recValues.get(tt).getFirst().equals(statements.get(i).toPrint())) {
                                        found = true;
                                        boolean foundArray = false;
                                        for (Pair pair2 : recValues.get(tt).getSecond()) {
                                            if (pair2 != null && pair2.getFirst().equals(outRole.get(kk))) {
                                                foundArray = true;
                                                toAdd = new Pair(outRole.get(kk), pair2.getSecond());
                                            }
                                        }

                                        if (!foundArray) {
                                            recValues.get(tt).getSecond().add(new Pair(outRole.get(kk), el.getSecond() + 1));
                                            toAdd = new Pair(outRole.get(kk), el.getSecond() + 1);

                                        }

                                        newStates.add(toAdd);
                                    }
                                }

                                if (!found) {
                                    toAdd = new Pair(outRole.get(kk), el.getSecond() + 1);
                                    newStates.add(toAdd);
                                    ArrayList<Pair<String, Integer>> newRecs = new ArrayList<>();
                                    newRecs.add(toAdd);
                                    recValues.add(new Pair(statements.get(i).toPrint(), newRecs));
                                }

                            } else if (statements.get(i) instanceof EndNode) { // TODO: to fix
                                toAdd = new Pair(outRole.get(kk), 100);
                                newStates.add(toAdd);
                            } else {
                                toAdd = new Pair(outRole.get(kk), el.getSecond() + i + 1);
                                newStates.add(toAdd);
                            }
                        }
                    }

                }
            }
            ArrayList<Pair<String, Integer>> ordered = new ArrayList<Pair<String, Integer>>();
            for (int kk = 0; kk < initState.size(); kk++) {
                ordered.add(null);
            }
            for (int ii = 0; ii < initState.size(); ii++) {

                boolean found = false;
                for (int jj = 0; jj < newStates.size(); jj++) {
                    if (newStates.get(jj).getFirst().equals(initState.get(ii).getFirst())) {
                        ordered.set(ii, newStates.get(jj));
                        found = true;
                    }
                }
                if (!found) {
                    ordered.set(ii, initState.get(ii));
                }
            }
            Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> toAdd = new Triplet(rates.get(i), lastUpdate, ordered);

            if (!states.contains(toAdd)) {
                states.add(toAdd);
            }
            if (statements.get(i) instanceof RecNode) {
                ArrayList<Node> newStms = new ArrayList<>();
                for (Pair<String, ArrayList<Node>> el : stms) {
                    if (el.getFirst().equals(statements.get(i).toPrint())) {
                        newStms = el.getSecond();
                    }
                }
                if (size != states.getSize()) {
                    for (int k = 0; k < newStms.size(); k++) {
                        toRet = newStms.get(k).generateStates(newStms, states, recValues, moduleNames, stms, ordered, consts);
                        states = toRet.getSecond();
                        recValues = toRet.getFirst();
                    }
                } else {
                    return new Pair(recValues, states);
                }
            } else if (statements.get(i) instanceof EndNode) {
                return new Pair(recValues, states);
            } else {
                for (int k = 0; k < statements.size(); k++) {
                    toRet = statements.get(k).generateStates(statements, states, recValues, moduleNames, stms, ordered, consts);
                    states = toRet.getSecond();
                    recValues = toRet.getFirst();
                }
            }
        }

        return new Pair(toRet.getFirst(), toRet.getSecond());
    }

    @Override
    public Matrix generateMarkovChain(ArrayList<Node> mods) {
        return null;
    }
}