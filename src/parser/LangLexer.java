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
		STAR=9, LPAR=10, RPAR=11, SLPAR=12, SRPAR=13, CLPAR=14, CRPAR=15, IF=16, 
		THEN=17, ELSE=18, END=19, WS=20, DOUBLE_STRING=21, INTEGER=22, ID=23;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SEMICOLON", "COLON", "DOT", "COMMA", "BRANCH", "FROM", "ASSIGN", "UNDERSCORE", 
			"STAR", "LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", "IF", "THEN", 
			"ELSE", "END", "WS", "DOUBLE_STRING", "DIGIT", "INTEGER", "CHAR", "ID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':'", "'.'", "','", "'+'", "'->'", "':='", "'_'", "'*'", 
			"'('", "')'", "'['", "']'", "'{'", "'}'", "'if'", "'then'", "'else'", 
			"'END'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMICOLON", "COLON", "DOT", "COMMA", "BRANCH", "FROM", "ASSIGN", 
			"UNDERSCORE", "STAR", "LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", 
			"IF", "THEN", "ELSE", "END", "WS", "DOUBLE_STRING", "INTEGER", "ID"
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
		"\u0004\u0000\u0017\u0091\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001"+
		"\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e"+
		"\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0004\u0013f\b\u0013\u000b\u0013\f\u0013g\u0001\u0013\u0001"+
		"\u0013\u0001\u0014\u0001\u0014\u0004\u0014n\b\u0014\u000b\u0014\f\u0014"+
		"o\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0016\u0004\u0016"+
		"w\b\u0016\u000b\u0016\f\u0016x\u0001\u0017\u0001\u0017\u0001\u0018\u0001"+
		"\u0018\u0004\u0018\u007f\b\u0018\u000b\u0018\f\u0018\u0080\u0001\u0018"+
		"\u0001\u0018\u0001\u0018\u0001\u0018\u0005\u0018\u0087\b\u0018\n\u0018"+
		"\f\u0018\u008a\t\u0018\u0001\u0018\u0005\u0018\u008d\b\u0018\n\u0018\f"+
		"\u0018\u0090\t\u0018\u0000\u0000\u0019\u0001\u0001\u0003\u0002\u0005\u0003"+
		"\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013\n\u0015"+
		"\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011#\u0012"+
		"%\u0013\'\u0014)\u0015+\u0000-\u0016/\u00001\u0017\u0001\u0000\u0003\u0004"+
		"\u0000\t\n\r\r  \u00a0\u00a0\u0001\u0000\"\"\u0002\u0000AZaz\u0098\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000"+
		"\u0000-\u0001\u0000\u0000\u0000\u00001\u0001\u0000\u0000\u0000\u00013"+
		"\u0001\u0000\u0000\u0000\u00035\u0001\u0000\u0000\u0000\u00057\u0001\u0000"+
		"\u0000\u0000\u00079\u0001\u0000\u0000\u0000\t;\u0001\u0000\u0000\u0000"+
		"\u000b=\u0001\u0000\u0000\u0000\r@\u0001\u0000\u0000\u0000\u000fC\u0001"+
		"\u0000\u0000\u0000\u0011E\u0001\u0000\u0000\u0000\u0013G\u0001\u0000\u0000"+
		"\u0000\u0015I\u0001\u0000\u0000\u0000\u0017K\u0001\u0000\u0000\u0000\u0019"+
		"M\u0001\u0000\u0000\u0000\u001bO\u0001\u0000\u0000\u0000\u001dQ\u0001"+
		"\u0000\u0000\u0000\u001fS\u0001\u0000\u0000\u0000!V\u0001\u0000\u0000"+
		"\u0000#[\u0001\u0000\u0000\u0000%`\u0001\u0000\u0000\u0000\'e\u0001\u0000"+
		"\u0000\u0000)k\u0001\u0000\u0000\u0000+s\u0001\u0000\u0000\u0000-v\u0001"+
		"\u0000\u0000\u0000/z\u0001\u0000\u0000\u00001~\u0001\u0000\u0000\u0000"+
		"34\u0005;\u0000\u00004\u0002\u0001\u0000\u0000\u000056\u0005:\u0000\u0000"+
		"6\u0004\u0001\u0000\u0000\u000078\u0005.\u0000\u00008\u0006\u0001\u0000"+
		"\u0000\u00009:\u0005,\u0000\u0000:\b\u0001\u0000\u0000\u0000;<\u0005+"+
		"\u0000\u0000<\n\u0001\u0000\u0000\u0000=>\u0005-\u0000\u0000>?\u0005>"+
		"\u0000\u0000?\f\u0001\u0000\u0000\u0000@A\u0005:\u0000\u0000AB\u0005="+
		"\u0000\u0000B\u000e\u0001\u0000\u0000\u0000CD\u0005_\u0000\u0000D\u0010"+
		"\u0001\u0000\u0000\u0000EF\u0005*\u0000\u0000F\u0012\u0001\u0000\u0000"+
		"\u0000GH\u0005(\u0000\u0000H\u0014\u0001\u0000\u0000\u0000IJ\u0005)\u0000"+
		"\u0000J\u0016\u0001\u0000\u0000\u0000KL\u0005[\u0000\u0000L\u0018\u0001"+
		"\u0000\u0000\u0000MN\u0005]\u0000\u0000N\u001a\u0001\u0000\u0000\u0000"+
		"OP\u0005{\u0000\u0000P\u001c\u0001\u0000\u0000\u0000QR\u0005}\u0000\u0000"+
		"R\u001e\u0001\u0000\u0000\u0000ST\u0005i\u0000\u0000TU\u0005f\u0000\u0000"+
		"U \u0001\u0000\u0000\u0000VW\u0005t\u0000\u0000WX\u0005h\u0000\u0000X"+
		"Y\u0005e\u0000\u0000YZ\u0005n\u0000\u0000Z\"\u0001\u0000\u0000\u0000["+
		"\\\u0005e\u0000\u0000\\]\u0005l\u0000\u0000]^\u0005s\u0000\u0000^_\u0005"+
		"e\u0000\u0000_$\u0001\u0000\u0000\u0000`a\u0005E\u0000\u0000ab\u0005N"+
		"\u0000\u0000bc\u0005D\u0000\u0000c&\u0001\u0000\u0000\u0000df\u0007\u0000"+
		"\u0000\u0000ed\u0001\u0000\u0000\u0000fg\u0001\u0000\u0000\u0000ge\u0001"+
		"\u0000\u0000\u0000gh\u0001\u0000\u0000\u0000hi\u0001\u0000\u0000\u0000"+
		"ij\u0006\u0013\u0000\u0000j(\u0001\u0000\u0000\u0000km\u0005\"\u0000\u0000"+
		"ln\b\u0001\u0000\u0000ml\u0001\u0000\u0000\u0000no\u0001\u0000\u0000\u0000"+
		"om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pq\u0001\u0000\u0000"+
		"\u0000qr\u0005\"\u0000\u0000r*\u0001\u0000\u0000\u0000st\u000209\u0000"+
		"t,\u0001\u0000\u0000\u0000uw\u0003+\u0015\u0000vu\u0001\u0000\u0000\u0000"+
		"wx\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000\u0000xy\u0001\u0000\u0000"+
		"\u0000y.\u0001\u0000\u0000\u0000z{\u0007\u0002\u0000\u0000{0\u0001\u0000"+
		"\u0000\u0000|\u007f\u0003/\u0017\u0000}\u007f\u0003\u000f\u0007\u0000"+
		"~|\u0001\u0000\u0000\u0000~}\u0001\u0000\u0000\u0000\u007f\u0080\u0001"+
		"\u0000\u0000\u0000\u0080~\u0001\u0000\u0000\u0000\u0080\u0081\u0001\u0000"+
		"\u0000\u0000\u0081\u0088\u0001\u0000\u0000\u0000\u0082\u0087\u0003/\u0017"+
		"\u0000\u0083\u0087\u0003+\u0015\u0000\u0084\u0087\u0003\u000f\u0007\u0000"+
		"\u0085\u0087\u0003\u0011\b\u0000\u0086\u0082\u0001\u0000\u0000\u0000\u0086"+
		"\u0083\u0001\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0086"+
		"\u0085\u0001\u0000\u0000\u0000\u0087\u008a\u0001\u0000\u0000\u0000\u0088"+
		"\u0086\u0001\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089"+
		"\u008e\u0001\u0000\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008b"+
		"\u008d\u0003/\u0017\u0000\u008c\u008b\u0001\u0000\u0000\u0000\u008d\u0090"+
		"\u0001\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008e\u008f"+
		"\u0001\u0000\u0000\u0000\u008f2\u0001\u0000\u0000\u0000\u0090\u008e\u0001"+
		"\u0000\u0000\u0000\t\u0000gox~\u0080\u0086\u0088\u008e\u0001\u0006\u0000"+
		"\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}