package lib;

import java.util.ArrayList;

public class State {

    ArrayList<Pair<String,Integer>> listOfStates = null;

    public State(){
        listOfStates = new ArrayList<>();
    }

    public void addState(Pair<String,Integer> _new){
        listOfStates.add(_new);
    }


    public ArrayList<Pair<String,Integer>> getListOfState(){
        return listOfStates;
    }

    public int getModuleState(String module){
        for(Pair<String,Integer> pair : listOfStates){
            if(pair.getFirst().equals(module)){
                return pair.getSecond();
            }
        }
        return -1;
    }

}
