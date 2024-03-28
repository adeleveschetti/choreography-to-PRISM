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
        String fileName = "Chor-PRISM/markov-chain-gen/leader-election";
        FileInputStream is = new FileInputStream(fileName + ".language");
        @SuppressWarnings("deprecation")
        ANTLRInputStream input = new ANTLRInputStream(is);
        LanguageLexer lexer = new LanguageLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LanguageParser parser = new LanguageParser(tokens);
        ParseTree t = parser.protocol();
        LanguageVisitorImpl visitor = new LanguageVisitorImpl();
        Node ast = visitor.visit(t);
		/*String code = ast.generateCode(null,0,0,false,null,null);
		File file = new File(fileName+".prism");
		if (!file.exists()) {
			file.createNewFile();
		}
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
		output.write(code);
		output.close();*/
        ListPair states = null;
        Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> res = null;
        res = ast.generateStates(null, states, null, null, null, null, null);
        states = res.getSecond();
        for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> array : states.getList()) {
            ArrayList<Integer> tmp = new ArrayList<>();
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
        ListPair newList = convertStates(states);

        for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> array : newList.getList()) {
            System.out.println("(" + array.getFirst() + ": " + array.getSecond() + " -> " + array.getThird() +")");
        }
    }

}
