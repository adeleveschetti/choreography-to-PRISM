import java.io.FileInputStream;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import ast.LangVisitorImpl;
import ast.Node;
import parser.LangLexer;
import parser.LangParser;

public class Main {

	public static void main(String[] args) throws Exception {
		String fileName = "example.lang";
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
		System.out.println(ast.codeGenerator(toRet,0));
	}

}
