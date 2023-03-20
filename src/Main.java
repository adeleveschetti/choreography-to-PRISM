import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ast.LangVisitorImpl;
import ast.Node;
import parser.LangLexer;
import parser.LangParser;

public class Main {

	public static void main(String[] args) throws Exception {
		String fileName = "example3.lang";
		FileInputStream is = new FileInputStream(fileName);
		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream(is);
		LangLexer lexer = new LangLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LangParser parser = new LangParser(tokens);
		ParseTree t = parser.protocol();
		LangVisitorImpl visitor = new LangVisitorImpl();
		Node ast = visitor.visit(t);
		String toRet = "";
		HashMap<String,ArrayList<Integer>> mapStatesBranches = null ;
		HashMap<String,ArrayList<Integer>> mapStates = null ;
		ArrayList<String> roles = null;
		ArrayList<String> allRoles = null;
		toRet = ast.codeGenerator(toRet,mapStates,mapStatesBranches,roles,allRoles,0,0);
		
		File file = new File("GeneratedCode.prism");
		if (!file.exists()) {
            file.createNewFile();
        }
		BufferedWriter output = new BufferedWriter(new FileWriter(file));
	    output.write(toRet);
	    output.close();
	}

}
