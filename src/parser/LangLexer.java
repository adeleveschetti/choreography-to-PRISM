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
		IF=17, THEN=18, ELSE=19, END=20, PREAMBLE=21, ENDPREAMBLE=22, CONST=23, 
		WS=24, DOUBLE_STRING=25, INTEGER=26, ID=27;
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
			"THEN", "ELSE", "END", "PREAMBLE", "ENDPREAMBLE", "CONST", "WS", "DOUBLE_STRING", 
			"DIGIT", "INTEGER", "CHAR", "ID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':'", "'.'", "','", "'+'", "'->'", "':='", "'_'", "'*'", 
			"'('", "')'", "'['", "']'", "'{'", "'}'", "'@'", "'if'", "'then'", "'else'", 
			"'END'", "'preamble'", "'endpreamble'", "'const'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMICOLON", "COLON", "DOT", "COMMA", "BRANCH", "FROM", "ASSIGN", 
			"UNDERSCORE", "STAR", "LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", 
			"AT", "IF", "THEN", "ELSE", "END", "PREAMBLE", "ENDPREAMBLE", "CONST", 
			"WS", "DOUBLE_STRING", "INTEGER", "ID"
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
		"\u0004\u0000\u001b\u00b6\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0002\u0019\u0007\u0019\u0002\u001a\u0007\u001a"+
		"\u0002\u001b\u0007\u001b\u0002\u001c\u0007\u001c\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014"+
		"\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0017\u0004\u0017\u008b\b\u0017\u000b\u0017\f\u0017"+
		"\u008c\u0001\u0017\u0001\u0017\u0001\u0018\u0001\u0018\u0004\u0018\u0093"+
		"\b\u0018\u000b\u0018\f\u0018\u0094\u0001\u0018\u0001\u0018\u0001\u0019"+
		"\u0001\u0019\u0001\u001a\u0004\u001a\u009c\b\u001a\u000b\u001a\f\u001a"+
		"\u009d\u0001\u001b\u0001\u001b\u0001\u001c\u0001\u001c\u0004\u001c\u00a4"+
		"\b\u001c\u000b\u001c\f\u001c\u00a5\u0001\u001c\u0001\u001c\u0001\u001c"+
		"\u0001\u001c\u0005\u001c\u00ac\b\u001c\n\u001c\f\u001c\u00af\t\u001c\u0001"+
		"\u001c\u0005\u001c\u00b2\b\u001c\n\u001c\f\u001c\u00b5\t\u001c\u0000\u0000"+
		"\u001d\u0001\u0001\u0003\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006"+
		"\r\u0007\u000f\b\u0011\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e"+
		"\u001d\u000f\u001f\u0010!\u0011#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017"+
		"/\u00181\u00193\u00005\u001a7\u00009\u001b\u0001\u0000\u0003\u0004\u0000"+
		"\t\n\r\r  \u00a0\u00a0\u0001\u0000\"\"\u0002\u0000AZaz\u00bd\u0000\u0001"+
		"\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005"+
		"\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001"+
		"\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000"+
		"\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000"+
		"\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000"+
		"\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000"+
		"\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000"+
		"\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000"+
		"\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000"+
		"\'\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001"+
		"\u0000\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000"+
		"\u0000\u00001\u0001\u0000\u0000\u0000\u00005\u0001\u0000\u0000\u0000\u0000"+
		"9\u0001\u0000\u0000\u0000\u0001;\u0001\u0000\u0000\u0000\u0003=\u0001"+
		"\u0000\u0000\u0000\u0005?\u0001\u0000\u0000\u0000\u0007A\u0001\u0000\u0000"+
		"\u0000\tC\u0001\u0000\u0000\u0000\u000bE\u0001\u0000\u0000\u0000\rH\u0001"+
		"\u0000\u0000\u0000\u000fK\u0001\u0000\u0000\u0000\u0011M\u0001\u0000\u0000"+
		"\u0000\u0013O\u0001\u0000\u0000\u0000\u0015Q\u0001\u0000\u0000\u0000\u0017"+
		"S\u0001\u0000\u0000\u0000\u0019U\u0001\u0000\u0000\u0000\u001bW\u0001"+
		"\u0000\u0000\u0000\u001dY\u0001\u0000\u0000\u0000\u001f[\u0001\u0000\u0000"+
		"\u0000!]\u0001\u0000\u0000\u0000#`\u0001\u0000\u0000\u0000%e\u0001\u0000"+
		"\u0000\u0000\'j\u0001\u0000\u0000\u0000)n\u0001\u0000\u0000\u0000+w\u0001"+
		"\u0000\u0000\u0000-\u0083\u0001\u0000\u0000\u0000/\u008a\u0001\u0000\u0000"+
		"\u00001\u0090\u0001\u0000\u0000\u00003\u0098\u0001\u0000\u0000\u00005"+
		"\u009b\u0001\u0000\u0000\u00007\u009f\u0001\u0000\u0000\u00009\u00a3\u0001"+
		"\u0000\u0000\u0000;<\u0005;\u0000\u0000<\u0002\u0001\u0000\u0000\u0000"+
		"=>\u0005:\u0000\u0000>\u0004\u0001\u0000\u0000\u0000?@\u0005.\u0000\u0000"+
		"@\u0006\u0001\u0000\u0000\u0000AB\u0005,\u0000\u0000B\b\u0001\u0000\u0000"+
		"\u0000CD\u0005+\u0000\u0000D\n\u0001\u0000\u0000\u0000EF\u0005-\u0000"+
		"\u0000FG\u0005>\u0000\u0000G\f\u0001\u0000\u0000\u0000HI\u0005:\u0000"+
		"\u0000IJ\u0005=\u0000\u0000J\u000e\u0001\u0000\u0000\u0000KL\u0005_\u0000"+
		"\u0000L\u0010\u0001\u0000\u0000\u0000MN\u0005*\u0000\u0000N\u0012\u0001"+
		"\u0000\u0000\u0000OP\u0005(\u0000\u0000P\u0014\u0001\u0000\u0000\u0000"+
		"QR\u0005)\u0000\u0000R\u0016\u0001\u0000\u0000\u0000ST\u0005[\u0000\u0000"+
		"T\u0018\u0001\u0000\u0000\u0000UV\u0005]\u0000\u0000V\u001a\u0001\u0000"+
		"\u0000\u0000WX\u0005{\u0000\u0000X\u001c\u0001\u0000\u0000\u0000YZ\u0005"+
		"}\u0000\u0000Z\u001e\u0001\u0000\u0000\u0000[\\\u0005@\u0000\u0000\\ "+
		"\u0001\u0000\u0000\u0000]^\u0005i\u0000\u0000^_\u0005f\u0000\u0000_\""+
		"\u0001\u0000\u0000\u0000`a\u0005t\u0000\u0000ab\u0005h\u0000\u0000bc\u0005"+
		"e\u0000\u0000cd\u0005n\u0000\u0000d$\u0001\u0000\u0000\u0000ef\u0005e"+
		"\u0000\u0000fg\u0005l\u0000\u0000gh\u0005s\u0000\u0000hi\u0005e\u0000"+
		"\u0000i&\u0001\u0000\u0000\u0000jk\u0005E\u0000\u0000kl\u0005N\u0000\u0000"+
		"lm\u0005D\u0000\u0000m(\u0001\u0000\u0000\u0000no\u0005p\u0000\u0000o"+
		"p\u0005r\u0000\u0000pq\u0005e\u0000\u0000qr\u0005a\u0000\u0000rs\u0005"+
		"m\u0000\u0000st\u0005b\u0000\u0000tu\u0005l\u0000\u0000uv\u0005e\u0000"+
		"\u0000v*\u0001\u0000\u0000\u0000wx\u0005e\u0000\u0000xy\u0005n\u0000\u0000"+
		"yz\u0005d\u0000\u0000z{\u0005p\u0000\u0000{|\u0005r\u0000\u0000|}\u0005"+
		"e\u0000\u0000}~\u0005a\u0000\u0000~\u007f\u0005m\u0000\u0000\u007f\u0080"+
		"\u0005b\u0000\u0000\u0080\u0081\u0005l\u0000\u0000\u0081\u0082\u0005e"+
		"\u0000\u0000\u0082,\u0001\u0000\u0000\u0000\u0083\u0084\u0005c\u0000\u0000"+
		"\u0084\u0085\u0005o\u0000\u0000\u0085\u0086\u0005n\u0000\u0000\u0086\u0087"+
		"\u0005s\u0000\u0000\u0087\u0088\u0005t\u0000\u0000\u0088.\u0001\u0000"+
		"\u0000\u0000\u0089\u008b\u0007\u0000\u0000\u0000\u008a\u0089\u0001\u0000"+
		"\u0000\u0000\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000"+
		"\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008e\u0001\u0000"+
		"\u0000\u0000\u008e\u008f\u0006\u0017\u0000\u0000\u008f0\u0001\u0000\u0000"+
		"\u0000\u0090\u0092\u0005\"\u0000\u0000\u0091\u0093\b\u0001\u0000\u0000"+
		"\u0092\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000"+
		"\u0094\u0092\u0001\u0000\u0000\u0000\u0094\u0095\u0001\u0000\u0000\u0000"+
		"\u0095\u0096\u0001\u0000\u0000\u0000\u0096\u0097\u0005\"\u0000\u0000\u0097"+
		"2\u0001\u0000\u0000\u0000\u0098\u0099\u000209\u0000\u00994\u0001\u0000"+
		"\u0000\u0000\u009a\u009c\u00033\u0019\u0000\u009b\u009a\u0001\u0000\u0000"+
		"\u0000\u009c\u009d\u0001\u0000\u0000\u0000\u009d\u009b\u0001\u0000\u0000"+
		"\u0000\u009d\u009e\u0001\u0000\u0000\u0000\u009e6\u0001\u0000\u0000\u0000"+
		"\u009f\u00a0\u0007\u0002\u0000\u0000\u00a08\u0001\u0000\u0000\u0000\u00a1"+
		"\u00a4\u00037\u001b\u0000\u00a2\u00a4\u0003\u000f\u0007\u0000\u00a3\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a2\u0001\u0000\u0000\u0000\u00a4\u00a5"+
		"\u0001\u0000\u0000\u0000\u00a5\u00a3\u0001\u0000\u0000\u0000\u00a5\u00a6"+
		"\u0001\u0000\u0000\u0000\u00a6\u00ad\u0001\u0000\u0000\u0000\u00a7\u00ac"+
		"\u00037\u001b\u0000\u00a8\u00ac\u00033\u0019\u0000\u00a9\u00ac\u0003\u000f"+
		"\u0007\u0000\u00aa\u00ac\u0003\u0011\b\u0000\u00ab\u00a7\u0001\u0000\u0000"+
		"\u0000\u00ab\u00a8\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000"+
		"\u0000\u00ab\u00aa\u0001\u0000\u0000\u0000\u00ac\u00af\u0001\u0000\u0000"+
		"\u0000\u00ad\u00ab\u0001\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000"+
		"\u0000\u00ae\u00b3\u0001\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000"+
		"\u0000\u00b0\u00b2\u00037\u001b\u0000\u00b1\u00b0\u0001\u0000\u0000\u0000"+
		"\u00b2\u00b5\u0001\u0000\u0000\u0000\u00b3\u00b1\u0001\u0000\u0000\u0000"+
		"\u00b3\u00b4\u0001\u0000\u0000\u0000\u00b4:\u0001\u0000\u0000\u0000\u00b5"+
		"\u00b3\u0001\u0000\u0000\u0000\t\u0000\u008c\u0094\u009d\u00a3\u00a5\u00ab"+
		"\u00ad\u00b3\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}