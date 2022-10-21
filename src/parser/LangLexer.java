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
		SEMICOLON=1, COLON=2, DOT=3, BRANCH=4, FROM=5, ASSIGN=6, UNDERSCORE=7, 
		STAR=8, LPAR=9, RPAR=10, SLPAR=11, SRPAR=12, CLPAR=13, CRPAR=14, IF=15, 
		THEN=16, ELSE=17, END=18, WS=19, DOUBLE_STRING=20, INTEGER=21, ID=22;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"SEMICOLON", "COLON", "DOT", "BRANCH", "FROM", "ASSIGN", "UNDERSCORE", 
			"STAR", "LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", "IF", "THEN", 
			"ELSE", "END", "WS", "DOUBLE_STRING", "DIGIT", "INTEGER", "CHAR", "ID"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':'", "'.'", "'+'", "'->'", "':='", "'_'", "'*'", "'('", 
			"')'", "'['", "']'", "'{'", "'}'", "'if'", "'then'", "'else'", "'END'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMICOLON", "COLON", "DOT", "BRANCH", "FROM", "ASSIGN", "UNDERSCORE", 
			"STAR", "LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", "IF", "THEN", 
			"ELSE", "END", "WS", "DOUBLE_STRING", "INTEGER", "ID"
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
		"\u0004\u0000\u0016\u008d\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b\u0001\u000b"+
		"\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0004\u0012b\b\u0012\u000b\u0012\f\u0012"+
		"c\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0004\u0013j\b\u0013"+
		"\u000b\u0013\f\u0013k\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014"+
		"\u0001\u0015\u0004\u0015s\b\u0015\u000b\u0015\f\u0015t\u0001\u0016\u0001"+
		"\u0016\u0001\u0017\u0001\u0017\u0004\u0017{\b\u0017\u000b\u0017\f\u0017"+
		"|\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0005\u0017\u0083\b"+
		"\u0017\n\u0017\f\u0017\u0086\t\u0017\u0001\u0017\u0005\u0017\u0089\b\u0017"+
		"\n\u0017\f\u0017\u008c\t\u0017\u0000\u0000\u0018\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0000+\u0015-\u0000/\u0016\u0001\u0000\u0003\u0004"+
		"\u0000\t\n\r\r  \u00a0\u00a0\u0001\u0000\"\"\u0002\u0000AZaz\u0094\u0000"+
		"\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000"+
		"\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000"+
		"\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r"+
		"\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011"+
		"\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015"+
		"\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019"+
		"\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d"+
		"\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001"+
		"\u0000\u0000\u0000\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000"+
		"\u0000\u0000\'\u0001\u0000\u0000\u0000\u0000+\u0001\u0000\u0000\u0000"+
		"\u0000/\u0001\u0000\u0000\u0000\u00011\u0001\u0000\u0000\u0000\u00033"+
		"\u0001\u0000\u0000\u0000\u00055\u0001\u0000\u0000\u0000\u00077\u0001\u0000"+
		"\u0000\u0000\t9\u0001\u0000\u0000\u0000\u000b<\u0001\u0000\u0000\u0000"+
		"\r?\u0001\u0000\u0000\u0000\u000fA\u0001\u0000\u0000\u0000\u0011C\u0001"+
		"\u0000\u0000\u0000\u0013E\u0001\u0000\u0000\u0000\u0015G\u0001\u0000\u0000"+
		"\u0000\u0017I\u0001\u0000\u0000\u0000\u0019K\u0001\u0000\u0000\u0000\u001b"+
		"M\u0001\u0000\u0000\u0000\u001dO\u0001\u0000\u0000\u0000\u001fR\u0001"+
		"\u0000\u0000\u0000!W\u0001\u0000\u0000\u0000#\\\u0001\u0000\u0000\u0000"+
		"%a\u0001\u0000\u0000\u0000\'g\u0001\u0000\u0000\u0000)o\u0001\u0000\u0000"+
		"\u0000+r\u0001\u0000\u0000\u0000-v\u0001\u0000\u0000\u0000/z\u0001\u0000"+
		"\u0000\u000012\u0005;\u0000\u00002\u0002\u0001\u0000\u0000\u000034\u0005"+
		":\u0000\u00004\u0004\u0001\u0000\u0000\u000056\u0005.\u0000\u00006\u0006"+
		"\u0001\u0000\u0000\u000078\u0005+\u0000\u00008\b\u0001\u0000\u0000\u0000"+
		"9:\u0005-\u0000\u0000:;\u0005>\u0000\u0000;\n\u0001\u0000\u0000\u0000"+
		"<=\u0005:\u0000\u0000=>\u0005=\u0000\u0000>\f\u0001\u0000\u0000\u0000"+
		"?@\u0005_\u0000\u0000@\u000e\u0001\u0000\u0000\u0000AB\u0005*\u0000\u0000"+
		"B\u0010\u0001\u0000\u0000\u0000CD\u0005(\u0000\u0000D\u0012\u0001\u0000"+
		"\u0000\u0000EF\u0005)\u0000\u0000F\u0014\u0001\u0000\u0000\u0000GH\u0005"+
		"[\u0000\u0000H\u0016\u0001\u0000\u0000\u0000IJ\u0005]\u0000\u0000J\u0018"+
		"\u0001\u0000\u0000\u0000KL\u0005{\u0000\u0000L\u001a\u0001\u0000\u0000"+
		"\u0000MN\u0005}\u0000\u0000N\u001c\u0001\u0000\u0000\u0000OP\u0005i\u0000"+
		"\u0000PQ\u0005f\u0000\u0000Q\u001e\u0001\u0000\u0000\u0000RS\u0005t\u0000"+
		"\u0000ST\u0005h\u0000\u0000TU\u0005e\u0000\u0000UV\u0005n\u0000\u0000"+
		"V \u0001\u0000\u0000\u0000WX\u0005e\u0000\u0000XY\u0005l\u0000\u0000Y"+
		"Z\u0005s\u0000\u0000Z[\u0005e\u0000\u0000[\"\u0001\u0000\u0000\u0000\\"+
		"]\u0005E\u0000\u0000]^\u0005N\u0000\u0000^_\u0005D\u0000\u0000_$\u0001"+
		"\u0000\u0000\u0000`b\u0007\u0000\u0000\u0000a`\u0001\u0000\u0000\u0000"+
		"bc\u0001\u0000\u0000\u0000ca\u0001\u0000\u0000\u0000cd\u0001\u0000\u0000"+
		"\u0000de\u0001\u0000\u0000\u0000ef\u0006\u0012\u0000\u0000f&\u0001\u0000"+
		"\u0000\u0000gi\u0005\"\u0000\u0000hj\b\u0001\u0000\u0000ih\u0001\u0000"+
		"\u0000\u0000jk\u0001\u0000\u0000\u0000ki\u0001\u0000\u0000\u0000kl\u0001"+
		"\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000mn\u0005\"\u0000\u0000n(\u0001"+
		"\u0000\u0000\u0000op\u000209\u0000p*\u0001\u0000\u0000\u0000qs\u0003)"+
		"\u0014\u0000rq\u0001\u0000\u0000\u0000st\u0001\u0000\u0000\u0000tr\u0001"+
		"\u0000\u0000\u0000tu\u0001\u0000\u0000\u0000u,\u0001\u0000\u0000\u0000"+
		"vw\u0007\u0002\u0000\u0000w.\u0001\u0000\u0000\u0000x{\u0003-\u0016\u0000"+
		"y{\u0003\r\u0006\u0000zx\u0001\u0000\u0000\u0000zy\u0001\u0000\u0000\u0000"+
		"{|\u0001\u0000\u0000\u0000|z\u0001\u0000\u0000\u0000|}\u0001\u0000\u0000"+
		"\u0000}\u0084\u0001\u0000\u0000\u0000~\u0083\u0003-\u0016\u0000\u007f"+
		"\u0083\u0003)\u0014\u0000\u0080\u0083\u0003\r\u0006\u0000\u0081\u0083"+
		"\u0003\u000f\u0007\u0000\u0082~\u0001\u0000\u0000\u0000\u0082\u007f\u0001"+
		"\u0000\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0082\u0081\u0001"+
		"\u0000\u0000\u0000\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0082\u0001"+
		"\u0000\u0000\u0000\u0084\u0085\u0001\u0000\u0000\u0000\u0085\u008a\u0001"+
		"\u0000\u0000\u0000\u0086\u0084\u0001\u0000\u0000\u0000\u0087\u0089\u0003"+
		"-\u0016\u0000\u0088\u0087\u0001\u0000\u0000\u0000\u0089\u008c\u0001\u0000"+
		"\u0000\u0000\u008a\u0088\u0001\u0000\u0000\u0000\u008a\u008b\u0001\u0000"+
		"\u0000\u0000\u008b0\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000"+
		"\u0000\t\u0000cktz|\u0082\u0084\u008a\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}