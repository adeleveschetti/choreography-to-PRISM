// Generated from java-escape by ANTLR 4.11.1
package parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMICOLON=1, COLON=2, DOT=3, COMMA=4, BRANCH=5, FROM=6, ASSIGN=7, UNDERSCORE=8, 
		STAR=9, LPAR=10, RPAR=11, SLPAR=12, SRPAR=13, CLPAR=14, CRPAR=15, AT=16, 
		IF=17, THEN=18, ELSE=19, END=20, WS=21, DOUBLE_STRING=22, INTEGER=23, 
		ID=24;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SEMICOLON", "COLON", "DOT", "COMMA", "BRANCH", "FROM", "ASSIGN", "UNDERSCORE", 
			"STAR", "LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", "AT", "IF", 
			"THEN", "ELSE", "END", "WS", "DOUBLE_STRING", "DIGIT", "INTEGER", "CHAR", 
			"ID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':'", "'.'", "','", "'+'", "'->'", "':='", "'_'", "'*'", 
			"'('", "')'", "'['", "']'", "'{'", "'}'", "'@'", "'if'", "'then'", "'else'", 
			"'END'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMICOLON", "COLON", "DOT", "COMMA", "BRANCH", "FROM", "ASSIGN", 
			"UNDERSCORE", "STAR", "LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", 
			"AT", "IF", "THEN", "ELSE", "END", "WS", "DOUBLE_STRING", "INTEGER", 
			"ID"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public LangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Lang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0018\u0095\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0004\u0014"+
		"j\b\u0014\u000b\u0014\f\u0014k\u0001\u0014\u0001\u0014\u0001\u0015\u0001"+
		"\u0015\u0004\u0015r\b\u0015\u000b\u0015\f\u0015s\u0001\u0015\u0001\u0015"+
		"\u0001\u0016\u0001\u0016\u0001\u0017\u0004\u0017{\b\u0017\u000b\u0017"+
		"\f\u0017|\u0001\u0018\u0001\u0018\u0001\u0019\u0001\u0019\u0004\u0019"+
		"\u0083\b\u0019\u000b\u0019\f\u0019\u0084\u0001\u0019\u0001\u0019\u0001"+
		"\u0019\u0001\u0019\u0005\u0019\u008b\b\u0019\n\u0019\f\u0019\u008e\t\u0019"+
		"\u0001\u0019\u0005\u0019\u0091\b\u0019\n\u0019\f\u0019\u0094\t\u0019\u0000"+
		"\u0000\u001a\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b"+
		"\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b"+
		"\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016"+
		"-\u0000/\u00171\u00003\u0018\u0001\u0000\u0003\u0004\u0000\t\n\r\r  \u00a0"+
		"\u00a0\u0001\u0000\"\"\u0002\u0000AZaz\u009c\u0000\u0001\u0001\u0000\u0000"+
		"\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001\u0000\u0000"+
		"\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000\u0000\u0000"+
		"\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000\u0000\u0000"+
		"\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000\u0000\u0000"+
		"\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000\u0000\u0000"+
		"\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000\u0000\u0000"+
		"\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000\u0000\u0000"+
		"\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000\u0000#\u0001"+
		"\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'\u0001\u0000"+
		"\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000/\u0001\u0000\u0000\u0000\u00003\u0001\u0000\u0000\u0000\u00015"+
		"\u0001\u0000\u0000\u0000\u00037\u0001\u0000\u0000\u0000\u00059\u0001\u0000"+
		"\u0000\u0000\u0007;\u0001\u0000\u0000\u0000\t=\u0001\u0000\u0000\u0000"+
		"\u000b?\u0001\u0000\u0000\u0000\rB\u0001\u0000\u0000\u0000\u000fE\u0001"+
		"\u0000\u0000\u0000\u0011G\u0001\u0000\u0000\u0000\u0013I\u0001\u0000\u0000"+
		"\u0000\u0015K\u0001\u0000\u0000\u0000\u0017M\u0001\u0000\u0000\u0000\u0019"+
		"O\u0001\u0000\u0000\u0000\u001bQ\u0001\u0000\u0000\u0000\u001dS\u0001"+
		"\u0000\u0000\u0000\u001fU\u0001\u0000\u0000\u0000!W\u0001\u0000\u0000"+
		"\u0000#Z\u0001\u0000\u0000\u0000%_\u0001\u0000\u0000\u0000\'d\u0001\u0000"+
		"\u0000\u0000)i\u0001\u0000\u0000\u0000+o\u0001\u0000\u0000\u0000-w\u0001"+
		"\u0000\u0000\u0000/z\u0001\u0000\u0000\u00001~\u0001\u0000\u0000\u0000"+
		"3\u0082\u0001\u0000\u0000\u000056\u0005;\u0000\u00006\u0002\u0001\u0000"+
		"\u0000\u000078\u0005:\u0000\u00008\u0004\u0001\u0000\u0000\u00009:\u0005"+
		".\u0000\u0000:\u0006\u0001\u0000\u0000\u0000;<\u0005,\u0000\u0000<\b\u0001"+
		"\u0000\u0000\u0000=>\u0005+\u0000\u0000>\n\u0001\u0000\u0000\u0000?@\u0005"+
		"-\u0000\u0000@A\u0005>\u0000\u0000A\f\u0001\u0000\u0000\u0000BC\u0005"+
		":\u0000\u0000CD\u0005=\u0000\u0000D\u000e\u0001\u0000\u0000\u0000EF\u0005"+
		"_\u0000\u0000F\u0010\u0001\u0000\u0000\u0000GH\u0005*\u0000\u0000H\u0012"+
		"\u0001\u0000\u0000\u0000IJ\u0005(\u0000\u0000J\u0014\u0001\u0000\u0000"+
		"\u0000KL\u0005)\u0000\u0000L\u0016\u0001\u0000\u0000\u0000MN\u0005[\u0000"+
		"\u0000N\u0018\u0001\u0000\u0000\u0000OP\u0005]\u0000\u0000P\u001a\u0001"+
		"\u0000\u0000\u0000QR\u0005{\u0000\u0000R\u001c\u0001\u0000\u0000\u0000"+
		"ST\u0005}\u0000\u0000T\u001e\u0001\u0000\u0000\u0000UV\u0005@\u0000\u0000"+
		"V \u0001\u0000\u0000\u0000WX\u0005i\u0000\u0000XY\u0005f\u0000\u0000Y"+
		"\"\u0001\u0000\u0000\u0000Z[\u0005t\u0000\u0000[\\\u0005h\u0000\u0000"+
		"\\]\u0005e\u0000\u0000]^\u0005n\u0000\u0000^$\u0001\u0000\u0000\u0000"+
		"_`\u0005e\u0000\u0000`a\u0005l\u0000\u0000ab\u0005s\u0000\u0000bc\u0005"+
		"e\u0000\u0000c&\u0001\u0000\u0000\u0000de\u0005E\u0000\u0000ef\u0005N"+
		"\u0000\u0000fg\u0005D\u0000\u0000g(\u0001\u0000\u0000\u0000hj\u0007\u0000"+
		"\u0000\u0000ih\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000ki\u0001"+
		"\u0000\u0000\u0000kl\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000"+
		"mn\u0006\u0014\u0000\u0000n*\u0001\u0000\u0000\u0000oq\u0005\"\u0000\u0000"+
		"pr\b\u0001\u0000\u0000qp\u0001\u0000\u0000\u0000rs\u0001\u0000\u0000\u0000"+
		"sq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tu\u0001\u0000\u0000"+
		"\u0000uv\u0005\"\u0000\u0000v,\u0001\u0000\u0000\u0000wx\u000209\u0000"+
		"x.\u0001\u0000\u0000\u0000y{\u0003-\u0016\u0000zy\u0001\u0000\u0000\u0000"+
		"{|\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000"+
		"\u0000}0\u0001\u0000\u0000\u0000~\u007f\u0007\u0002\u0000\u0000\u007f"+
		"2\u0001\u0000\u0000\u0000\u0080\u0083\u00031\u0018\u0000\u0081\u0083\u0003"+
		"\u000f\u0007\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0081\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0082\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u008c\u0001"+
		"\u0000\u0000\u0000\u0086\u008b\u00031\u0018\u0000\u0087\u008b\u0003-\u0016"+
		"\u0000\u0088\u008b\u0003\u000f\u0007\u0000\u0089\u008b\u0003\u0011\b\u0000"+
		"\u008a\u0086\u0001\u0000\u0000\u0000\u008a\u0087\u0001\u0000\u0000\u0000"+
		"\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u0089\u0001\u0000\u0000\u0000"+
		"\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u0092\u0001\u0000\u0000\u0000"+
		"\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0091\u00031\u0018\u0000\u0090"+
		"\u008f\u0001\u0000\u0000\u0000\u0091\u0094\u0001\u0000\u0000\u0000\u0092"+
		"\u0090\u0001\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093"+
		"4\u0001\u0000\u0000\u0000\u0094\u0092\u0001\u0000\u0000\u0000\t\u0000"+
		"ks|\u0082\u0084\u008a\u008c\u0092\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}