import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ast.LanguageVisitorImpl;
import ast.Node;
import parser.LanguageLexer;
import parser.LanguageParser;

public class Main {

	public static void main(String[] args) throws Exception {
		String fileName = "example.language";
		FileInputStream is = new FileInputStream(fileName);
		@SuppressWarnings("deprecation")
		ANTLRInputStream input = new ANTLRInputStream(is);
		LanguageLexer lexer = new LanguageLexer(input);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		LanguageParser parser = new LanguageParser(tokens);
		ParseTree t = parser.protocol();
		LanguageVisitorImpl visitor = new LanguageVisitorImpl();
		Node ast = visitor.visit(t);
		String code = ast.generateCode("",-1,-1,null,0);
		System.out.println(code);
		/*
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
	    */
	}
	
}
