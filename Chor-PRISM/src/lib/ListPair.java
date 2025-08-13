package lib;

import java.util.ArrayList;

public class ListPair {

    private ArrayList<Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>>> data = null;

    public ListPair() {
        data = new ArrayList<>();
    }

    public ListPair(Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> firstEl) {
        data = new ArrayList<>();
        data.add(firstEl);
    }

    public void add(Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el) {

        data.add(el);
    }

    public void addElementIndex(int index, Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el) {
        data.add(index, el);
    }

    public void set(int index, Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el) {
        data.set(index, el);
    }

    public void remove(int index) {
        data.remove(index);
    }


    public ArrayList<Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>>> getList() {
        return data;
    }

    public Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> get(int index) {
        return data.get(index);
    }


    public boolean stateVerifies(ArrayList<Pair<String, Integer>> state, String cond, ArrayList<Pair<String,String>> consts){

        String[] conds = null;

        if(cond.contains("&")){
            conds = cond.split("&");
        }
        if(cond.contains("|")){
            conds = cond.split("\\|");
        }
        else{
            conds = new String[1];
            conds[0] = cond;
        }
        for(String el : conds) {
            if(!el.isEmpty()){

                if(el.matches("^[a-zA-Z0-9_.-]*$")){
                    for(Pair<String,Integer> pair : state){
                        if(pair.getFirst().equals(el) && pair.getSecond()==0){
                            return false;
                        }
                    }
                }
                else if(el.contains("!")){
                    for(Pair<String,Integer> pair : state){
                        if(pair.getFirst().equals(el) && pair.getSecond()==1){
                            return false;
                        }
                    }
                }
                else{
                    if(el.contains("<")){
                        String var1 = el.substring(1,el.indexOf("<"));
                        String var2 = el.substring(el.indexOf("<")+1,el.length()-1);
                     // TODO: add case in which there is a complex expression
                        if(!var2.contains("+")) {
                            for (Pair<String, Integer> pair : state) {
                               if (pair.getFirst().equals(var1) && pair.getSecond() <= Double.parseDouble(var2)) {
                                    return false;
                               }
                            }
                        }
                    }
                    else if(el.contains(">")){
                        String var1 = el.substring(1,el.indexOf(">"));
                        String var2 = el.substring(el.indexOf(">")+1,el.length()-1);
                        if(!var2.contains("+")) { // TODO: add case in which there is a complex expression
                            for (Pair<String, Integer> pair : state) {
                                if (pair.getFirst().equals(var1) && pair.getSecond() <= Double.parseDouble(var2)) {
                                    return false;
                                }

                            }
                        }
                    }
                    else if(el.contains("=")){

                        String var1 = el.substring(1,el.indexOf("="));
                        String var2 = el.substring(el.indexOf("=")+1,el.length()-1);

                      if(!var2.contains("+")) { // TODO: add case in which there is a complex expression
                            for (Pair<String, Integer> pair : state) {
                                if (pair.getFirst().equals(var1) && pair.getSecond() != Double.parseDouble(var2)) {
                                    return false;
                                }

                            }
                       }
                    }
                    else if(el.contains("!=")){
                        String var1 = el.substring(1,el.indexOf("!="));
                        String var2 = el.substring(el.indexOf("!=")+2,el.length()-1);

                        if(var2.matches("^[0-9]")) { // TODO: add case in which there is a complex expression

                            for (Pair<String, Integer> pair : state) {
                                if (pair.getFirst().equals(var1) && pair.getSecond() == Double.parseDouble(var2)) {
                                    return false;
                                }
                            }
                        }
                        else if(var2.matches("^[a-zA-Z0-9_.-]*$")){

                            for (Pair<String, Integer> pair : state) {
                                if (pair.getFirst().equals(var1) ) {
                                    for (Pair<String, Integer> pair2 : state) {
                                        if (pair2.getFirst().equals(var2)) {
                                            if (pair.getSecond() == pair2.getSecond()) {
                                                return false;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public int getSize() {
        return data.size();
    }

    public boolean equals(ListPair list) {
        if (data.size() != list.getSize()) {
            return false;
        }
        for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el : data) {
            for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el2 : list.getList()) {
                if (!el.getFirst().equals(el2.getFirst())) {
                    return false;
                }
                for (int i = 0; i < el.getSecond().size(); i++) {
                    if (!el.getSecond().get(i).getFirst().equals(el2.getSecond().get(i).getFirst())) {
                        return false;
                    } else {
                        if (el.getSecond().get(i).getSecond() != el2.getSecond().get(i).getSecond()) {
                            return false;
                        }
                    }
                }
                for (int i = 0; i < el.getThird().size(); i++) {
                    if (!el.getThird().get(i).getFirst().equals(el2.getThird().get(i).getFirst())) {
                        return false;
                    } else {
                        if (el.getThird().get(i).getSecond() != el2.getThird().get(i).getSecond()) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
}

    public boolean contains(Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> pair) {
        for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el : data) {
            boolean flag = false;
            boolean firstEqual = false;
            boolean rateEqual = el.getFirst().equals(pair.getFirst());
            int counter1 = 0;
            for (int i = 0; i < el.getSecond().size(); i++) {
                /*if (el.getSecond().get(i).getFirst().equals(pair.getSecond().get(i).getFirst()) && (int) el.getSecond().get(i).getSecond() != (int) pair.getSecond().get(i).getSecond()) {
                    flag = true;
                }
                if (!flag && i == el.getSecond().size() - 1) {
                    firstEqual = true;
                }*/
                if (el.getSecond().get(i).getFirst().equals(pair.getSecond().get(i).getFirst()) && (int) el.getSecond().get(i).getSecond() == (int) pair.getSecond().get(i).getSecond()) {
                    counter1++;
                }
            }
            flag = false;
            int counter2 = 0;
            for (int i = 0; i < el.getThird().size(); i++) {
                if (el.getThird().get(i).getFirst().equals(pair.getThird().get(i).getFirst()) && (int) el.getThird().get(i).getSecond() == (int) pair.getThird().get(i).getSecond()) {
                    flag = true;
                    counter2 ++;
                }
                /*if (counter==el.getThird().size() && i == el.getThird().size() - 1 && rateEqual && firstEqual) {
                    return true;
                }*/
            }
            if(counter1 == el.getSecond().size() && counter2 == el.getThird().size() && rateEqual){
                return true;
            }
        }

        return false;
    }
}