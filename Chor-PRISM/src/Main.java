import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;
import lib.Triplet;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ast.LanguageVisitorImpl;
import ast.Node;
import parser.LanguageLexer;
import parser.LanguageParser;

public class Main {

    public static int contained(ArrayList<Pair<String, Integer>> check, ArrayList<Pair<Integer, ArrayList<Pair<String, Integer>>>> array) {
        for (Pair<Integer, ArrayList<Pair<String, Integer>>> tmp : array) {

            boolean contained = true;
            for (int i = 0; i < tmp.getSecond().size(); i++) {
                if (!tmp.getSecond().get(i).getFirst().equals(check.get(i).getFirst()) || (int)tmp.getSecond().get(i).getSecond() != (int)check.get(i).getSecond()) {
                    contained = false;
                }
                if (contained && i == tmp.getSecond().size() - 1) {
                    return tmp.getFirst();
                }
            }
        }
        return -1;
    }

    public static int containedList(ArrayList<Pair<String, Integer>> check, ArrayList<ArrayList<Pair<String, Integer>>> array) {
        for (ArrayList<Pair<String,Integer>> tmp : array) {
            boolean contained = true;
            for (int i = 0; i < tmp.size(); i++) {
                if ((int)check.get(i).getSecond()==-1 || !tmp.get(i).getFirst().equals(check.get(i).getFirst()) || (int)tmp.get(i).getSecond() != (int)check.get(i).getSecond()) {
                    contained = false;
                }
                if (contained && i == tmp.size() - 1) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static ListPair convertStates(ListPair states) {
        ListPair toRet = new ListPair();
        ArrayList<Pair<Integer, ArrayList<Pair<String, Integer>>>> values = new ArrayList<>();
        int counter = 0;
        for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> triplet : states.getList()) {
            if (contained(triplet.getThird(), values) == -1) {
                if(triplet.getSecond().get(0).getSecond()!=-1) {
                    if (contained(triplet.getSecond(), values) == -1) {
                        values.add(new Pair(counter, triplet.getSecond()));
                        counter++;
                    }
                }

                values.add(new Pair(counter,triplet.getThird()));
                counter++;
            }
            if(triplet.getSecond().get(0).getSecond()!=-1) {
                toRet.add(new Triplet(triplet.getFirst(), values.get(contained(triplet.getSecond(), values)).getFirst(), values.get(contained(triplet.getThird(), values)).getFirst()));
            }
        }
        return toRet;
    }

    public static void main(String[] args) throws Exception {
        String fileName = "Chor-PRISM/markov-chain-gen/new-syntax";
        FileInputStream is = new FileInputStream(fileName + ".language");
        @SuppressWarnings("deprecation")
        ANTLRInputStream input = new ANTLRInputStream(is);
        LanguageLexer lexer = new LanguageLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LanguageParser parser = new LanguageParser(tokens);
        ParseTree t = parser.protocol();
        LanguageVisitorImpl visitor = new LanguageVisitorImpl();
        Node ast = visitor.visit(t);
		//String code = ast.generateCode(null,0,0,false,null,null);
        ArrayList<Pair<String,ArrayList<String>>> codes = ast.generatePrismCode(null,0,0,"",null, null, null, null, null, null, null);
        String generatedCode = "";
        for(Pair<String,ArrayList<String>> pair : codes){
            for(String el : pair.getSecond()){
                generatedCode = generatedCode + el + "\n";
            }
        }
        File file = new File(fileName+".prism");
        if (!file.exists()) {
            file.createNewFile();
        }
        BufferedWriter output = new BufferedWriter(new FileWriter(file));
        output.write(generatedCode);
        output.close();
/*
        ListPair states = null;
        Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> res = null;
        res = ast.generateStates(null, states, null, null, null, null, null);
        states = res.getSecond();
        ArrayList<ArrayList<Pair<String,Integer>>> tmpList = new ArrayList<>();

        for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> array : states.getList()) {
            ArrayList<Integer> tmp = new ArrayList<>();
            if(containedList(array.getSecond(),tmpList)==-1){
                tmpList.add(array.getSecond());
            }
            if(containedList(array.getThird(),tmpList)==-1){
                tmpList.add(array.getThird());
            }
            System.out.print("(" + array.getFirst() + " , [");
            for (Pair<String, Integer> el : array.getSecond()) {
                tmp.add(el.getSecond());
                System.out.print("<" + el.getFirst() + "," + el.getSecond() + "> ");
            }
            System.out.print("] -> ");
            System.out.print("[");
            for (Pair<String, Integer> el : array.getThird()) {
                tmp.add(el.getSecond());
                System.out.print("<" + el.getFirst() + "," + el.getSecond() + "> ");
            }
            System.out.print("])");
            System.out.println("");
        }


        System.out.println("===============");
        int index = -1;
        for(int i=0; i<tmpList.size(); i++){
            for(Pair<String,Integer> pair : tmpList.get(i))  {
                if((int)pair.getSecond()==-1) {
                    index = i;
                }
            }
        }
        tmpList.remove(index);
        for(ArrayList<Pair<String,Integer>> el : tmpList){
            System.out.print("[");
            for(Pair<String,Integer> pair : el) {
                System.out.print("<" + pair.getFirst() + "," + pair.getSecond() + "> ");
            }
            System.out.print("]");
            System.out.println(" ");

        }
        System.out.println("#" + (tmpList.size()) + " states generated.");

        System.out.println("===============");

        ListPair newList = convertStates(states);
        File file2 = new File(fileName+".m");
        if (!file2.exists()) {
            file2.createNewFile();
        }
        BufferedWriter output2 = new BufferedWriter(new FileWriter(file2));
        String toPrint = "P=sparse("+tmpList.size()+","+tmpList.size()+");\n";
        for (Triplet array : newList.getList()) {
            System.out.println("(" + array.getFirst() + ": " + array.getSecond() + " -> " + array.getThird() +")");
            int index1 = (int)array.getSecond()+1;
            int index2 = (int)array.getThird()+1;
            toPrint = toPrint + "P("+index1+","+index2+")="+array.getFirst()+";\n";
        }

        output2.write(toPrint);
        output2.close();
*/

    }

}
