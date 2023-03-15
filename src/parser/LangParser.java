// Generated from java-escape by ANTLR 4.11.1
package parser;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class LangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.11.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		SEMICOLON=1, COLON=2, DOT=3, DOTS=4, COMMA=5, BRANCH=6, FROM=7, ASSIGN=8, 
		NEQ=9, EQ=10, LEQ=11, GEQ=12, LE=13, GE=14, UNDERSCORE=15, STAR=16, LPAR=17, 
		RPAR=18, SLPAR=19, SRPAR=20, CLPAR=21, CRPAR=22, AT=23, IF=24, THEN=25, 
		ELSE=26, IFplus=27, ELSEplus=28, END=29, AND=30, PREAMBLE=31, ENDPREAMBLE=32, 
		CONST=33, FOREACH=34, IN=35, WS=36, DOUBLE_STRING=37, INTEGER=38, CHAR=39, 
		ID=40;
	public static final int
		RULE_protocol = 0, RULE_preamble = 1, RULE_statement = 2, RULE_ifThenElse = 3, 
		RULE_ifElse = 4, RULE_internalAction = 5, RULE_protocolID = 6, RULE_rate = 7, 
		RULE_message = 8, RULE_varDef = 9, RULE_actions = 10, RULE_roleDef = 11, 
		RULE_roleSpec = 12, RULE_roleGroup = 13, RULE_roleIndex = 14, RULE_indexSpec = 15, 
		RULE_role = 16, RULE_forLoop = 17, RULE_roleVar = 18, RULE_variableDecl = 19, 
		RULE_cond = 20, RULE_condIf = 21, RULE_index = 22, RULE_id = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"protocol", "preamble", "statement", "ifThenElse", "ifElse", "internalAction", 
			"protocolID", "rate", "message", "varDef", "actions", "roleDef", "roleSpec", 
			"roleGroup", "roleIndex", "indexSpec", "role", "forLoop", "roleVar", 
			"variableDecl", "cond", "condIf", "index", "id"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "':'", "'.'", "'...'", "','", "'+'", "'->'", "':='", "'!='", 
			"'='", "'<='", "'>='", "'<'", "'>'", "'_'", "'*'", "'('", "')'", "'['", 
			"']'", "'{'", "'}'", "'@'", "'if'", "'then'", "'else'", "'IF'", "'ELSE'", 
			"'END'", "'&&'", "'preamble'", "'endpreamble'", "'const'", "'foreach'", 
			"'in'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "SEMICOLON", "COLON", "DOT", "DOTS", "COMMA", "BRANCH", "FROM", 
			"ASSIGN", "NEQ", "EQ", "LEQ", "GEQ", "LE", "GE", "UNDERSCORE", "STAR", 
			"LPAR", "RPAR", "SLPAR", "SRPAR", "CLPAR", "CRPAR", "AT", "IF", "THEN", 
			"ELSE", "IFplus", "ELSEplus", "END", "AND", "PREAMBLE", "ENDPREAMBLE", 
			"CONST", "FOREACH", "IN", "WS", "DOUBLE_STRING", "INTEGER", "CHAR", "ID"
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

	@Override
	public String getGrammarFileName() { return "java-escape"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public LangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProtocolContext extends ParserRuleContext {
		public TerminalNode SEMICOLON() { return getToken(LangParser.SEMICOLON, 0); }
		public ProtocolIDContext protocolID() {
			return getRuleContext(ProtocolIDContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(LangParser.ASSIGN, 0); }
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public PreambleContext preamble() {
			return getRuleContext(PreambleContext.class,0);
		}
		public VarDefContext varDef() {
			return getRuleContext(VarDefContext.class,0);
		}
		public List<RoleDefContext> roleDef() {
			return getRuleContexts(RoleDefContext.class);
		}
		public RoleDefContext roleDef(int i) {
			return getRuleContext(RoleDefContext.class,i);
		}
		public ProtocolContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_protocol; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterProtocol(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitProtocol(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitProtocol(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProtocolContext protocol() throws RecognitionException {
		ProtocolContext _localctx = new ProtocolContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_protocol);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(49);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PREAMBLE) {
				{
				setState(48);
				preamble();
				}
			}

			setState(52);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CHAR) {
				{
				setState(51);
				varDef();
				}
			}

			setState(54);
			match(SEMICOLON);
			setState(56); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(55);
					roleDef();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(58); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			{
			setState(60);
			protocolID();
			setState(61);
			match(ASSIGN);
			setState(62);
			statement();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PreambleContext extends ParserRuleContext {
		public TerminalNode PREAMBLE() { return getToken(LangParser.PREAMBLE, 0); }
		public TerminalNode ENDPREAMBLE() { return getToken(LangParser.ENDPREAMBLE, 0); }
		public List<VariableDeclContext> variableDecl() {
			return getRuleContexts(VariableDeclContext.class);
		}
		public VariableDeclContext variableDecl(int i) {
			return getRuleContext(VariableDeclContext.class,i);
		}
		public PreambleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_preamble; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterPreamble(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitPreamble(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitPreamble(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PreambleContext preamble() throws RecognitionException {
		PreambleContext _localctx = new PreambleContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_preamble);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(PREAMBLE);
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOUBLE_STRING) {
				{
				{
				setState(65);
				variableDecl();
				}
				}
				setState(70);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(71);
			match(ENDPREAMBLE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatementContext extends ParserRuleContext {
		public RateContext rate;
		public List<RateContext> rateValues = new ArrayList<RateContext>();
		public List<RoleContext> role() {
			return getRuleContexts(RoleContext.class);
		}
		public RoleContext role(int i) {
			return getRuleContext(RoleContext.class,i);
		}
		public List<TerminalNode> FROM() { return getTokens(LangParser.FROM); }
		public TerminalNode FROM(int i) {
			return getToken(LangParser.FROM, i);
		}
		public List<TerminalNode> COLON() { return getTokens(LangParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(LangParser.COLON, i);
		}
		public List<MessageContext> message() {
			return getRuleContexts(MessageContext.class);
		}
		public MessageContext message(int i) {
			return getRuleContext(MessageContext.class,i);
		}
		public TerminalNode DOT() { return getToken(LangParser.DOT, 0); }
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public List<TerminalNode> SLPAR() { return getTokens(LangParser.SLPAR); }
		public TerminalNode SLPAR(int i) {
			return getToken(LangParser.SLPAR, i);
		}
		public List<RateContext> rate() {
			return getRuleContexts(RateContext.class);
		}
		public RateContext rate(int i) {
			return getRuleContext(RateContext.class,i);
		}
		public List<TerminalNode> SRPAR() { return getTokens(LangParser.SRPAR); }
		public TerminalNode SRPAR(int i) {
			return getToken(LangParser.SRPAR, i);
		}
		public List<ActionsContext> actions() {
			return getRuleContexts(ActionsContext.class);
		}
		public ActionsContext actions(int i) {
			return getRuleContext(ActionsContext.class,i);
		}
		public TerminalNode LPAR() { return getToken(LangParser.LPAR, 0); }
		public List<TerminalNode> BRANCH() { return getTokens(LangParser.BRANCH); }
		public TerminalNode BRANCH(int i) {
			return getToken(LangParser.BRANCH, i);
		}
		public TerminalNode RPAR() { return getToken(LangParser.RPAR, 0); }
		public InternalActionContext internalAction() {
			return getRuleContext(InternalActionContext.class,0);
		}
		public IfThenElseContext ifThenElse() {
			return getRuleContext(IfThenElseContext.class,0);
		}
		public ProtocolIDContext protocolID() {
			return getRuleContext(ProtocolIDContext.class,0);
		}
		public IfElseContext ifElse() {
			return getRuleContext(IfElseContext.class,0);
		}
		public TerminalNode END() { return getToken(LangParser.END, 0); }
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_statement);
		int _la;
		try {
			setState(156);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,13,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(73);
				role();
				setState(74);
				match(FROM);
				setState(75);
				role();
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(76);
					match(COMMA);
					setState(77);
					role();
					}
					}
					setState(82);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(83);
				match(COLON);
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLPAR) {
					{
					setState(84);
					match(SLPAR);
					setState(85);
					rate();
					setState(86);
					match(SRPAR);
					}
				}

				setState(93);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
				case 1:
					{
					setState(90);
					actions();
					setState(91);
					match(FROM);
					}
					break;
				}
				setState(95);
				message();
				setState(96);
				match(DOT);
				setState(97);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(99);
				role();
				setState(100);
				match(FROM);
				setState(101);
				role();
				setState(106);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(102);
					match(COMMA);
					setState(103);
					role();
					}
					}
					setState(108);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(109);
				match(LPAR);
				setState(110);
				match(BRANCH);
				setState(111);
				match(SLPAR);
				setState(112);
				((StatementContext)_localctx).rate = rate();
				((StatementContext)_localctx).rateValues.add(((StatementContext)_localctx).rate);
				setState(113);
				match(SRPAR);
				setState(117);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
				case 1:
					{
					setState(114);
					actions();
					setState(115);
					match(FROM);
					}
					break;
				}
				setState(119);
				message();
				setState(120);
				match(COLON);
				setState(121);
				statement();
				setState(135); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(122);
					match(BRANCH);
					setState(123);
					match(SLPAR);
					setState(124);
					((StatementContext)_localctx).rate = rate();
					((StatementContext)_localctx).rateValues.add(((StatementContext)_localctx).rate);
					setState(125);
					match(SRPAR);
					setState(129);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,9,_ctx) ) {
					case 1:
						{
						setState(126);
						actions();
						setState(127);
						match(FROM);
						}
						break;
					}
					setState(131);
					message();
					setState(132);
					match(COLON);
					setState(133);
					statement();
					}
					}
					setState(137); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BRANCH );
				setState(139);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(145);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==SLPAR) {
					{
					setState(141);
					match(SLPAR);
					setState(142);
					rate();
					setState(143);
					match(SRPAR);
					}
				}

				setState(147);
				internalAction();
				setState(150);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(148);
					match(DOT);
					setState(149);
					statement();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(152);
				ifThenElse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(153);
				protocolID();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(154);
				ifElse();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(155);
				match(END);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfThenElseContext extends ParserRuleContext {
		public StatementContext thenStat;
		public StatementContext elseStat;
		public TerminalNode IF() { return getToken(LangParser.IF, 0); }
		public CondContext cond() {
			return getRuleContext(CondContext.class,0);
		}
		public TerminalNode AT() { return getToken(LangParser.AT, 0); }
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public TerminalNode THEN() { return getToken(LangParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(LangParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(LangParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(LangParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(LangParser.CRPAR, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> ELSE() { return getTokens(LangParser.ELSE); }
		public TerminalNode ELSE(int i) {
			return getToken(LangParser.ELSE, i);
		}
		public IfThenElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifThenElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIfThenElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIfThenElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIfThenElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfThenElseContext ifThenElse() throws RecognitionException {
		IfThenElseContext _localctx = new IfThenElseContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_ifThenElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(158);
			match(IF);
			setState(159);
			cond();
			setState(160);
			match(AT);
			setState(161);
			role();
			setState(162);
			match(THEN);
			setState(163);
			match(CLPAR);
			setState(164);
			((IfThenElseContext)_localctx).thenStat = statement();
			setState(165);
			match(CRPAR);
			setState(173);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE) {
				{
				{
				setState(166);
				match(ELSE);
				setState(167);
				match(CLPAR);
				setState(168);
				((IfThenElseContext)_localctx).elseStat = statement();
				setState(169);
				match(CRPAR);
				}
				}
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IfElseContext extends ParserRuleContext {
		public StatementContext thenStat;
		public StatementContext elseStat;
		public TerminalNode IFplus() { return getToken(LangParser.IFplus, 0); }
		public TerminalNode LPAR() { return getToken(LangParser.LPAR, 0); }
		public CondIfContext condIf() {
			return getRuleContext(CondIfContext.class,0);
		}
		public TerminalNode RPAR() { return getToken(LangParser.RPAR, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(LangParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(LangParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(LangParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(LangParser.CRPAR, i);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> ELSEplus() { return getTokens(LangParser.ELSEplus); }
		public TerminalNode ELSEplus(int i) {
			return getToken(LangParser.ELSEplus, i);
		}
		public IfElseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifElse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIfElse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIfElse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIfElse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IfElseContext ifElse() throws RecognitionException {
		IfElseContext _localctx = new IfElseContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_ifElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(176);
			match(IFplus);
			setState(177);
			match(LPAR);
			setState(178);
			condIf();
			setState(179);
			match(RPAR);
			setState(180);
			match(CLPAR);
			setState(181);
			((IfElseContext)_localctx).thenStat = statement();
			setState(182);
			match(CRPAR);
			setState(190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSEplus) {
				{
				{
				setState(183);
				match(ELSEplus);
				setState(184);
				match(CLPAR);
				setState(185);
				((IfElseContext)_localctx).elseStat = statement();
				setState(186);
				match(CRPAR);
				}
				}
				setState(192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class InternalActionContext extends ParserRuleContext {
		public TerminalNode CLPAR() { return getToken(LangParser.CLPAR, 0); }
		public TerminalNode DOUBLE_STRING() { return getToken(LangParser.DOUBLE_STRING, 0); }
		public TerminalNode CRPAR() { return getToken(LangParser.CRPAR, 0); }
		public TerminalNode AT() { return getToken(LangParser.AT, 0); }
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public InternalActionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_internalAction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterInternalAction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitInternalAction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitInternalAction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InternalActionContext internalAction() throws RecognitionException {
		InternalActionContext _localctx = new InternalActionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_internalAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			match(CLPAR);
			setState(194);
			match(DOUBLE_STRING);
			setState(195);
			match(CRPAR);
			setState(196);
			match(AT);
			setState(197);
			role();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProtocolIDContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public ProtocolIDContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_protocolID; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterProtocolID(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitProtocolID(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitProtocolID(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ProtocolIDContext protocolID() throws RecognitionException {
		ProtocolIDContext _localctx = new ProtocolIDContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_protocolID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RateContext extends ParserRuleContext {
		public TerminalNode DOUBLE_STRING() { return getToken(LangParser.DOUBLE_STRING, 0); }
		public RateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRate(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRate(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RateContext rate() throws RecognitionException {
		RateContext _localctx = new RateContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(201);
			match(DOUBLE_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MessageContext extends ParserRuleContext {
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public List<ForLoopContext> forLoop() {
			return getRuleContexts(ForLoopContext.class);
		}
		public ForLoopContext forLoop(int i) {
			return getRuleContext(ForLoopContext.class,i);
		}
		public MessageContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_message; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterMessage(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitMessage(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitMessage(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MessageContext message() throws RecognitionException {
		MessageContext _localctx = new MessageContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_message);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			actions();
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FOREACH) {
				{
				{
				setState(204);
				forLoop();
				}
				}
				setState(209);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDefContext extends ParserRuleContext {
		public TerminalNode CHAR() { return getToken(LangParser.CHAR, 0); }
		public TerminalNode EQ() { return getToken(LangParser.EQ, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public VarDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterVarDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitVarDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitVarDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDefContext varDef() throws RecognitionException {
		VarDefContext _localctx = new VarDefContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_varDef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(210);
			match(CHAR);
			setState(211);
			match(EQ);
			setState(212);
			match(INTEGER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ActionsContext extends ParserRuleContext {
		public Token DOUBLE_STRING;
		public List<Token> action = new ArrayList<Token>();
		public List<TerminalNode> AND() { return getTokens(LangParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(LangParser.AND, i);
		}
		public List<TerminalNode> DOUBLE_STRING() { return getTokens(LangParser.DOUBLE_STRING); }
		public TerminalNode DOUBLE_STRING(int i) {
			return getToken(LangParser.DOUBLE_STRING, i);
		}
		public ActionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_actions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterActions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitActions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitActions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ActionsContext actions() throws RecognitionException {
		ActionsContext _localctx = new ActionsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_actions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DOUBLE_STRING) {
				{
				setState(214);
				((ActionsContext)_localctx).DOUBLE_STRING = match(DOUBLE_STRING);
				((ActionsContext)_localctx).action.add(((ActionsContext)_localctx).DOUBLE_STRING);
				}
			}

			setState(221);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AND) {
				{
				{
				setState(217);
				match(AND);
				setState(218);
				((ActionsContext)_localctx).DOUBLE_STRING = match(DOUBLE_STRING);
				((ActionsContext)_localctx).action.add(((ActionsContext)_localctx).DOUBLE_STRING);
				}
				}
				setState(223);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoleDefContext extends ParserRuleContext {
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public TerminalNode FROM() { return getToken(LangParser.FROM, 0); }
		public TerminalNode SEMICOLON() { return getToken(LangParser.SEMICOLON, 0); }
		public IndexSpecContext indexSpec() {
			return getRuleContext(IndexSpecContext.class,0);
		}
		public List<RoleSpecContext> roleSpec() {
			return getRuleContexts(RoleSpecContext.class);
		}
		public RoleSpecContext roleSpec(int i) {
			return getRuleContext(RoleSpecContext.class,i);
		}
		public RoleDefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roleDef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRoleDef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRoleDef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRoleDef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleDefContext roleDef() throws RecognitionException {
		RoleDefContext _localctx = new RoleDefContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_roleDef);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(224);
			role();
			setState(225);
			match(FROM);
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==CHAR) {
				{
				setState(226);
				indexSpec();
				}
			}

			setState(232);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ID) {
				{
				{
				setState(229);
				roleSpec();
				}
				}
				setState(234);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(235);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoleSpecContext extends ParserRuleContext {
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public TerminalNode COLON() { return getToken(LangParser.COLON, 0); }
		public List<RoleVarContext> roleVar() {
			return getRuleContexts(RoleVarContext.class);
		}
		public RoleVarContext roleVar(int i) {
			return getRuleContext(RoleVarContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(LangParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(LangParser.COMMA, i);
		}
		public RoleSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roleSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRoleSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRoleSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRoleSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleSpecContext roleSpec() throws RecognitionException {
		RoleSpecContext _localctx = new RoleSpecContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_roleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(237);
			role();
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(238);
				match(COLON);
				{
				setState(239);
				roleVar();
				setState(244);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(240);
					match(COMMA);
					setState(241);
					roleVar();
					}
					}
					setState(246);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
			}

			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoleGroupContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public RoleGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roleGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRoleGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRoleGroup(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRoleGroup(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleGroupContext roleGroup() throws RecognitionException {
		RoleGroupContext _localctx = new RoleGroupContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_roleGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoleIndexContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode SLPAR() { return getToken(LangParser.SLPAR, 0); }
		public TerminalNode SRPAR() { return getToken(LangParser.SRPAR, 0); }
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public TerminalNode BRANCH() { return getToken(LangParser.BRANCH, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public RoleIndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roleIndex; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRoleIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRoleIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRoleIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleIndexContext roleIndex() throws RecognitionException {
		RoleIndexContext _localctx = new RoleIndexContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_roleIndex);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
			match(ID);
			setState(252);
			match(SLPAR);
			setState(258);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				{
				setState(253);
				index();
				}
				break;
			case 2:
				{
				{
				setState(254);
				index();
				setState(255);
				match(BRANCH);
				setState(256);
				match(INTEGER);
				}
				}
				break;
			}
			setState(260);
			match(SRPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexSpecContext extends ParserRuleContext {
		public Token upperBound;
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public TerminalNode IN() { return getToken(LangParser.IN, 0); }
		public TerminalNode SLPAR() { return getToken(LangParser.SLPAR, 0); }
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public TerminalNode DOTS() { return getToken(LangParser.DOTS, 0); }
		public TerminalNode SRPAR() { return getToken(LangParser.SRPAR, 0); }
		public TerminalNode CHAR() { return getToken(LangParser.CHAR, 0); }
		public IndexSpecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_indexSpec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIndexSpec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIndexSpec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIndexSpec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexSpecContext indexSpec() throws RecognitionException {
		IndexSpecContext _localctx = new IndexSpecContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_indexSpec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(262);
			index();
			setState(263);
			match(IN);
			setState(264);
			match(SLPAR);
			setState(265);
			match(INTEGER);
			setState(266);
			match(DOTS);
			setState(267);
			((IndexSpecContext)_localctx).upperBound = match(CHAR);
			setState(268);
			match(SRPAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoleContext extends ParserRuleContext {
		public RoleGroupContext roleGroup() {
			return getRuleContext(RoleGroupContext.class,0);
		}
		public RoleIndexContext roleIndex() {
			return getRuleContext(RoleIndexContext.class,0);
		}
		public RoleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_role; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRole(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRole(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRole(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleContext role() throws RecognitionException {
		RoleContext _localctx = new RoleContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_role);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(272);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				{
				setState(270);
				roleGroup();
				}
				break;
			case 2:
				{
				setState(271);
				roleIndex();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ForLoopContext extends ParserRuleContext {
		public IndexContext indexIteration;
		public Token op;
		public IndexContext upperBound;
		public TerminalNode FOREACH() { return getToken(LangParser.FOREACH, 0); }
		public TerminalNode LPAR() { return getToken(LangParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(LangParser.RPAR, 0); }
		public ActionsContext actions() {
			return getRuleContext(ActionsContext.class,0);
		}
		public TerminalNode AT() { return getToken(LangParser.AT, 0); }
		public RoleContext role() {
			return getRuleContext(RoleContext.class,0);
		}
		public List<IndexContext> index() {
			return getRuleContexts(IndexContext.class);
		}
		public IndexContext index(int i) {
			return getRuleContext(IndexContext.class,i);
		}
		public TerminalNode EQ() { return getToken(LangParser.EQ, 0); }
		public TerminalNode LE() { return getToken(LangParser.LE, 0); }
		public TerminalNode GE() { return getToken(LangParser.GE, 0); }
		public TerminalNode LEQ() { return getToken(LangParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(LangParser.GEQ, 0); }
		public TerminalNode NEQ() { return getToken(LangParser.NEQ, 0); }
		public ForLoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forLoop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterForLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitForLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitForLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ForLoopContext forLoop() throws RecognitionException {
		ForLoopContext _localctx = new ForLoopContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_forLoop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(274);
			match(FOREACH);
			setState(275);
			match(LPAR);
			setState(276);
			((ForLoopContext)_localctx).indexIteration = index();
			setState(277);
			((ForLoopContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 32256L) != 0) ) {
				((ForLoopContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(278);
			((ForLoopContext)_localctx).upperBound = index();
			setState(279);
			match(RPAR);
			setState(280);
			actions();
			setState(281);
			match(AT);
			setState(282);
			role();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class RoleVarContext extends ParserRuleContext {
		public TerminalNode DOUBLE_STRING() { return getToken(LangParser.DOUBLE_STRING, 0); }
		public RoleVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_roleVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterRoleVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitRoleVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitRoleVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RoleVarContext roleVar() throws RecognitionException {
		RoleVarContext _localctx = new RoleVarContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_roleVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			match(DOUBLE_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VariableDeclContext extends ParserRuleContext {
		public TerminalNode DOUBLE_STRING() { return getToken(LangParser.DOUBLE_STRING, 0); }
		public VariableDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterVariableDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitVariableDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitVariableDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VariableDeclContext variableDecl() throws RecognitionException {
		VariableDeclContext _localctx = new VariableDeclContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_variableDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(286);
			match(DOUBLE_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondContext extends ParserRuleContext {
		public TerminalNode DOUBLE_STRING() { return getToken(LangParser.DOUBLE_STRING, 0); }
		public CondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCond(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCond(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondContext cond() throws RecognitionException {
		CondContext _localctx = new CondContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(DOUBLE_STRING);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CondIfContext extends ParserRuleContext {
		public Token op;
		public IndexContext index() {
			return getRuleContext(IndexContext.class,0);
		}
		public TerminalNode INTEGER() { return getToken(LangParser.INTEGER, 0); }
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public TerminalNode EQ() { return getToken(LangParser.EQ, 0); }
		public TerminalNode LE() { return getToken(LangParser.LE, 0); }
		public TerminalNode GE() { return getToken(LangParser.GE, 0); }
		public TerminalNode LEQ() { return getToken(LangParser.LEQ, 0); }
		public TerminalNode GEQ() { return getToken(LangParser.GEQ, 0); }
		public TerminalNode NEQ() { return getToken(LangParser.NEQ, 0); }
		public CondIfContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condIf; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterCondIf(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitCondIf(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitCondIf(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CondIfContext condIf() throws RecognitionException {
		CondIfContext _localctx = new CondIfContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_condIf);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			index();
			setState(291);
			((CondIfContext)_localctx).op = _input.LT(1);
			_la = _input.LA(1);
			if ( !(((_la) & ~0x3f) == 0 && ((1L << _la) & 32256L) != 0) ) {
				((CondIfContext)_localctx).op = (Token)_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(292);
			_la = _input.LA(1);
			if ( !(_la==INTEGER || _la==ID) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IndexContext extends ParserRuleContext {
		public TerminalNode CHAR() { return getToken(LangParser.CHAR, 0); }
		public IndexContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_index; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterIndex(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitIndex(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitIndex(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IndexContext index() throws RecognitionException {
		IndexContext _localctx = new IndexContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_index);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(294);
			match(CHAR);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class IdContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
		public IdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_id; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).enterId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof LangListener ) ((LangListener)listener).exitId(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof LangVisitor ) return ((LangVisitor<? extends T>)visitor).visitId(this);
			else return visitor.visitChildren(this);
		}
	}

	public final IdContext id() throws RecognitionException {
		IdContext _localctx = new IdContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			match(ID);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\u0004\u0001(\u012b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0003\u0000"+
		"2\b\u0000\u0001\u0000\u0003\u00005\b\u0000\u0001\u0000\u0001\u0000\u0004"+
		"\u00009\b\u0000\u000b\u0000\f\u0000:\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0001\u0001\u0001\u0005\u0001C\b\u0001\n\u0001\f\u0001"+
		"F\t\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0005\u0002O\b\u0002\n\u0002\f\u0002R\t\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"Y\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002^\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0005\u0002i\b\u0002\n\u0002\f\u0002l\t"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002v\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0003\u0002\u0082\b\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0004\u0002\u0088\b\u0002\u000b\u0002\f"+
		"\u0002\u0089\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0003\u0002\u0092\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002\u0097\b\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002\u009d\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003\u00ac\b\u0003\n\u0003"+
		"\f\u0003\u00af\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0005\u0004\u00bd\b\u0004\n\u0004\f\u0004\u00c0"+
		"\t\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001"+
		"\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b"+
		"\u0005\b\u00ce\b\b\n\b\f\b\u00d1\t\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0003\n\u00d8\b\n\u0001\n\u0001\n\u0005\n\u00dc\b\n\n\n\f\n\u00df\t"+
		"\n\u0001\u000b\u0001\u000b\u0001\u000b\u0003\u000b\u00e4\b\u000b\u0001"+
		"\u000b\u0005\u000b\u00e7\b\u000b\n\u000b\f\u000b\u00ea\t\u000b\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00f3\b\f"+
		"\n\f\f\f\u00f6\t\f\u0003\f\u00f8\b\f\u0001\r\u0001\r\u0001\u000e\u0001"+
		"\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0003"+
		"\u000e\u0103\b\u000e\u0001\u000e\u0001\u000e\u0001\u000f\u0001\u000f\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001"+
		"\u0010\u0001\u0010\u0003\u0010\u0111\b\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0001\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001"+
		"\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001"+
		"\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001\u0017\u0000\u0000\u0018"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.\u0000\u0002\u0001\u0000\t\u000e\u0002\u0000&&(("+
		"\u0130\u00001\u0001\u0000\u0000\u0000\u0002@\u0001\u0000\u0000\u0000\u0004"+
		"\u009c\u0001\u0000\u0000\u0000\u0006\u009e\u0001\u0000\u0000\u0000\b\u00b0"+
		"\u0001\u0000\u0000\u0000\n\u00c1\u0001\u0000\u0000\u0000\f\u00c7\u0001"+
		"\u0000\u0000\u0000\u000e\u00c9\u0001\u0000\u0000\u0000\u0010\u00cb\u0001"+
		"\u0000\u0000\u0000\u0012\u00d2\u0001\u0000\u0000\u0000\u0014\u00d7\u0001"+
		"\u0000\u0000\u0000\u0016\u00e0\u0001\u0000\u0000\u0000\u0018\u00ed\u0001"+
		"\u0000\u0000\u0000\u001a\u00f9\u0001\u0000\u0000\u0000\u001c\u00fb\u0001"+
		"\u0000\u0000\u0000\u001e\u0106\u0001\u0000\u0000\u0000 \u0110\u0001\u0000"+
		"\u0000\u0000\"\u0112\u0001\u0000\u0000\u0000$\u011c\u0001\u0000\u0000"+
		"\u0000&\u011e\u0001\u0000\u0000\u0000(\u0120\u0001\u0000\u0000\u0000*"+
		"\u0122\u0001\u0000\u0000\u0000,\u0126\u0001\u0000\u0000\u0000.\u0128\u0001"+
		"\u0000\u0000\u000002\u0003\u0002\u0001\u000010\u0001\u0000\u0000\u0000"+
		"12\u0001\u0000\u0000\u000024\u0001\u0000\u0000\u000035\u0003\u0012\t\u0000"+
		"43\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000056\u0001\u0000\u0000"+
		"\u000068\u0005\u0001\u0000\u000079\u0003\u0016\u000b\u000087\u0001\u0000"+
		"\u0000\u00009:\u0001\u0000\u0000\u0000:8\u0001\u0000\u0000\u0000:;\u0001"+
		"\u0000\u0000\u0000;<\u0001\u0000\u0000\u0000<=\u0003\f\u0006\u0000=>\u0005"+
		"\b\u0000\u0000>?\u0003\u0004\u0002\u0000?\u0001\u0001\u0000\u0000\u0000"+
		"@D\u0005\u001f\u0000\u0000AC\u0003&\u0013\u0000BA\u0001\u0000\u0000\u0000"+
		"CF\u0001\u0000\u0000\u0000DB\u0001\u0000\u0000\u0000DE\u0001\u0000\u0000"+
		"\u0000EG\u0001\u0000\u0000\u0000FD\u0001\u0000\u0000\u0000GH\u0005 \u0000"+
		"\u0000H\u0003\u0001\u0000\u0000\u0000IJ\u0003 \u0010\u0000JK\u0005\u0007"+
		"\u0000\u0000KP\u0003 \u0010\u0000LM\u0005\u0005\u0000\u0000MO\u0003 \u0010"+
		"\u0000NL\u0001\u0000\u0000\u0000OR\u0001\u0000\u0000\u0000PN\u0001\u0000"+
		"\u0000\u0000PQ\u0001\u0000\u0000\u0000QS\u0001\u0000\u0000\u0000RP\u0001"+
		"\u0000\u0000\u0000SX\u0005\u0002\u0000\u0000TU\u0005\u0013\u0000\u0000"+
		"UV\u0003\u000e\u0007\u0000VW\u0005\u0014\u0000\u0000WY\u0001\u0000\u0000"+
		"\u0000XT\u0001\u0000\u0000\u0000XY\u0001\u0000\u0000\u0000Y]\u0001\u0000"+
		"\u0000\u0000Z[\u0003\u0014\n\u0000[\\\u0005\u0007\u0000\u0000\\^\u0001"+
		"\u0000\u0000\u0000]Z\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000"+
		"^_\u0001\u0000\u0000\u0000_`\u0003\u0010\b\u0000`a\u0005\u0003\u0000\u0000"+
		"ab\u0003\u0004\u0002\u0000b\u009d\u0001\u0000\u0000\u0000cd\u0003 \u0010"+
		"\u0000de\u0005\u0007\u0000\u0000ej\u0003 \u0010\u0000fg\u0005\u0005\u0000"+
		"\u0000gi\u0003 \u0010\u0000hf\u0001\u0000\u0000\u0000il\u0001\u0000\u0000"+
		"\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000km\u0001\u0000"+
		"\u0000\u0000lj\u0001\u0000\u0000\u0000mn\u0005\u0011\u0000\u0000no\u0005"+
		"\u0006\u0000\u0000op\u0005\u0013\u0000\u0000pq\u0003\u000e\u0007\u0000"+
		"qu\u0005\u0014\u0000\u0000rs\u0003\u0014\n\u0000st\u0005\u0007\u0000\u0000"+
		"tv\u0001\u0000\u0000\u0000ur\u0001\u0000\u0000\u0000uv\u0001\u0000\u0000"+
		"\u0000vw\u0001\u0000\u0000\u0000wx\u0003\u0010\b\u0000xy\u0005\u0002\u0000"+
		"\u0000y\u0087\u0003\u0004\u0002\u0000z{\u0005\u0006\u0000\u0000{|\u0005"+
		"\u0013\u0000\u0000|}\u0003\u000e\u0007\u0000}\u0081\u0005\u0014\u0000"+
		"\u0000~\u007f\u0003\u0014\n\u0000\u007f\u0080\u0005\u0007\u0000\u0000"+
		"\u0080\u0082\u0001\u0000\u0000\u0000\u0081~\u0001\u0000\u0000\u0000\u0081"+
		"\u0082\u0001\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083"+
		"\u0084\u0003\u0010\b\u0000\u0084\u0085\u0005\u0002\u0000\u0000\u0085\u0086"+
		"\u0003\u0004\u0002\u0000\u0086\u0088\u0001\u0000\u0000\u0000\u0087z\u0001"+
		"\u0000\u0000\u0000\u0088\u0089\u0001\u0000\u0000\u0000\u0089\u0087\u0001"+
		"\u0000\u0000\u0000\u0089\u008a\u0001\u0000\u0000\u0000\u008a\u008b\u0001"+
		"\u0000\u0000\u0000\u008b\u008c\u0005\u0012\u0000\u0000\u008c\u009d\u0001"+
		"\u0000\u0000\u0000\u008d\u008e\u0005\u0013\u0000\u0000\u008e\u008f\u0003"+
		"\u000e\u0007\u0000\u008f\u0090\u0005\u0014\u0000\u0000\u0090\u0092\u0001"+
		"\u0000\u0000\u0000\u0091\u008d\u0001\u0000\u0000\u0000\u0091\u0092\u0001"+
		"\u0000\u0000\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0096\u0003"+
		"\n\u0005\u0000\u0094\u0095\u0005\u0003\u0000\u0000\u0095\u0097\u0003\u0004"+
		"\u0002\u0000\u0096\u0094\u0001\u0000\u0000\u0000\u0096\u0097\u0001\u0000"+
		"\u0000\u0000\u0097\u009d\u0001\u0000\u0000\u0000\u0098\u009d\u0003\u0006"+
		"\u0003\u0000\u0099\u009d\u0003\f\u0006\u0000\u009a\u009d\u0003\b\u0004"+
		"\u0000\u009b\u009d\u0005\u001d\u0000\u0000\u009cI\u0001\u0000\u0000\u0000"+
		"\u009cc\u0001\u0000\u0000\u0000\u009c\u0091\u0001\u0000\u0000\u0000\u009c"+
		"\u0098\u0001\u0000\u0000\u0000\u009c\u0099\u0001\u0000\u0000\u0000\u009c"+
		"\u009a\u0001\u0000\u0000\u0000\u009c\u009b\u0001\u0000\u0000\u0000\u009d"+
		"\u0005\u0001\u0000\u0000\u0000\u009e\u009f\u0005\u0018\u0000\u0000\u009f"+
		"\u00a0\u0003(\u0014\u0000\u00a0\u00a1\u0005\u0017\u0000\u0000\u00a1\u00a2"+
		"\u0003 \u0010\u0000\u00a2\u00a3\u0005\u0019\u0000\u0000\u00a3\u00a4\u0005"+
		"\u0015\u0000\u0000\u00a4\u00a5\u0003\u0004\u0002\u0000\u00a5\u00ad\u0005"+
		"\u0016\u0000\u0000\u00a6\u00a7\u0005\u001a\u0000\u0000\u00a7\u00a8\u0005"+
		"\u0015\u0000\u0000\u00a8\u00a9\u0003\u0004\u0002\u0000\u00a9\u00aa\u0005"+
		"\u0016\u0000\u0000\u00aa\u00ac\u0001\u0000\u0000\u0000\u00ab\u00a6\u0001"+
		"\u0000\u0000\u0000\u00ac\u00af\u0001\u0000\u0000\u0000\u00ad\u00ab\u0001"+
		"\u0000\u0000\u0000\u00ad\u00ae\u0001\u0000\u0000\u0000\u00ae\u0007\u0001"+
		"\u0000\u0000\u0000\u00af\u00ad\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005"+
		"\u001b\u0000\u0000\u00b1\u00b2\u0005\u0011\u0000\u0000\u00b2\u00b3\u0003"+
		"*\u0015\u0000\u00b3\u00b4\u0005\u0012\u0000\u0000\u00b4\u00b5\u0005\u0015"+
		"\u0000\u0000\u00b5\u00b6\u0003\u0004\u0002\u0000\u00b6\u00be\u0005\u0016"+
		"\u0000\u0000\u00b7\u00b8\u0005\u001c\u0000\u0000\u00b8\u00b9\u0005\u0015"+
		"\u0000\u0000\u00b9\u00ba\u0003\u0004\u0002\u0000\u00ba\u00bb\u0005\u0016"+
		"\u0000\u0000\u00bb\u00bd\u0001\u0000\u0000\u0000\u00bc\u00b7\u0001\u0000"+
		"\u0000\u0000\u00bd\u00c0\u0001\u0000\u0000\u0000\u00be\u00bc\u0001\u0000"+
		"\u0000\u0000\u00be\u00bf\u0001\u0000\u0000\u0000\u00bf\t\u0001\u0000\u0000"+
		"\u0000\u00c0\u00be\u0001\u0000\u0000\u0000\u00c1\u00c2\u0005\u0015\u0000"+
		"\u0000\u00c2\u00c3\u0005%\u0000\u0000\u00c3\u00c4\u0005\u0016\u0000\u0000"+
		"\u00c4\u00c5\u0005\u0017\u0000\u0000\u00c5\u00c6\u0003 \u0010\u0000\u00c6"+
		"\u000b\u0001\u0000\u0000\u0000\u00c7\u00c8\u0005(\u0000\u0000\u00c8\r"+
		"\u0001\u0000\u0000\u0000\u00c9\u00ca\u0005%\u0000\u0000\u00ca\u000f\u0001"+
		"\u0000\u0000\u0000\u00cb\u00cf\u0003\u0014\n\u0000\u00cc\u00ce\u0003\""+
		"\u0011\u0000\u00cd\u00cc\u0001\u0000\u0000\u0000\u00ce\u00d1\u0001\u0000"+
		"\u0000\u0000\u00cf\u00cd\u0001\u0000\u0000\u0000\u00cf\u00d0\u0001\u0000"+
		"\u0000\u0000\u00d0\u0011\u0001\u0000\u0000\u0000\u00d1\u00cf\u0001\u0000"+
		"\u0000\u0000\u00d2\u00d3\u0005\'\u0000\u0000\u00d3\u00d4\u0005\n\u0000"+
		"\u0000\u00d4\u00d5\u0005&\u0000\u0000\u00d5\u0013\u0001\u0000\u0000\u0000"+
		"\u00d6\u00d8\u0005%\u0000\u0000\u00d7\u00d6\u0001\u0000\u0000\u0000\u00d7"+
		"\u00d8\u0001\u0000\u0000\u0000\u00d8\u00dd\u0001\u0000\u0000\u0000\u00d9"+
		"\u00da\u0005\u001e\u0000\u0000\u00da\u00dc\u0005%\u0000\u0000\u00db\u00d9"+
		"\u0001\u0000\u0000\u0000\u00dc\u00df\u0001\u0000\u0000\u0000\u00dd\u00db"+
		"\u0001\u0000\u0000\u0000\u00dd\u00de\u0001\u0000\u0000\u0000\u00de\u0015"+
		"\u0001\u0000\u0000\u0000\u00df\u00dd\u0001\u0000\u0000\u0000\u00e0\u00e1"+
		"\u0003 \u0010\u0000\u00e1\u00e3\u0005\u0007\u0000\u0000\u00e2\u00e4\u0003"+
		"\u001e\u000f\u0000\u00e3\u00e2\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001"+
		"\u0000\u0000\u0000\u00e4\u00e8\u0001\u0000\u0000\u0000\u00e5\u00e7\u0003"+
		"\u0018\f\u0000\u00e6\u00e5\u0001\u0000\u0000\u0000\u00e7\u00ea\u0001\u0000"+
		"\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000\u0000\u00e8\u00e9\u0001\u0000"+
		"\u0000\u0000\u00e9\u00eb\u0001\u0000\u0000\u0000\u00ea\u00e8\u0001\u0000"+
		"\u0000\u0000\u00eb\u00ec\u0005\u0001\u0000\u0000\u00ec\u0017\u0001\u0000"+
		"\u0000\u0000\u00ed\u00f7\u0003 \u0010\u0000\u00ee\u00ef\u0005\u0002\u0000"+
		"\u0000\u00ef\u00f4\u0003$\u0012\u0000\u00f0\u00f1\u0005\u0005\u0000\u0000"+
		"\u00f1\u00f3\u0003$\u0012\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000\u00f3"+
		"\u00f6\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000\u0000\u00f4"+
		"\u00f5\u0001\u0000\u0000\u0000\u00f5\u00f8\u0001\u0000\u0000\u0000\u00f6"+
		"\u00f4\u0001\u0000\u0000\u0000\u00f7\u00ee\u0001\u0000\u0000\u0000\u00f7"+
		"\u00f8\u0001\u0000\u0000\u0000\u00f8\u0019\u0001\u0000\u0000\u0000\u00f9"+
		"\u00fa\u0005(\u0000\u0000\u00fa\u001b\u0001\u0000\u0000\u0000\u00fb\u00fc"+
		"\u0005(\u0000\u0000\u00fc\u0102\u0005\u0013\u0000\u0000\u00fd\u0103\u0003"+
		",\u0016\u0000\u00fe\u00ff\u0003,\u0016\u0000\u00ff\u0100\u0005\u0006\u0000"+
		"\u0000\u0100\u0101\u0005&\u0000\u0000\u0101\u0103\u0001\u0000\u0000\u0000"+
		"\u0102\u00fd\u0001\u0000\u0000\u0000\u0102\u00fe\u0001\u0000\u0000\u0000"+
		"\u0103\u0104\u0001\u0000\u0000\u0000\u0104\u0105\u0005\u0014\u0000\u0000"+
		"\u0105\u001d\u0001\u0000\u0000\u0000\u0106\u0107\u0003,\u0016\u0000\u0107"+
		"\u0108\u0005#\u0000\u0000\u0108\u0109\u0005\u0013\u0000\u0000\u0109\u010a"+
		"\u0005&\u0000\u0000\u010a\u010b\u0005\u0004\u0000\u0000\u010b\u010c\u0005"+
		"\'\u0000\u0000\u010c\u010d\u0005\u0014\u0000\u0000\u010d\u001f\u0001\u0000"+
		"\u0000\u0000\u010e\u0111\u0003\u001a\r\u0000\u010f\u0111\u0003\u001c\u000e"+
		"\u0000\u0110\u010e\u0001\u0000\u0000\u0000\u0110\u010f\u0001\u0000\u0000"+
		"\u0000\u0111!\u0001\u0000\u0000\u0000\u0112\u0113\u0005\"\u0000\u0000"+
		"\u0113\u0114\u0005\u0011\u0000\u0000\u0114\u0115\u0003,\u0016\u0000\u0115"+
		"\u0116\u0007\u0000\u0000\u0000\u0116\u0117\u0003,\u0016\u0000\u0117\u0118"+
		"\u0005\u0012\u0000\u0000\u0118\u0119\u0003\u0014\n\u0000\u0119\u011a\u0005"+
		"\u0017\u0000\u0000\u011a\u011b\u0003 \u0010\u0000\u011b#\u0001\u0000\u0000"+
		"\u0000\u011c\u011d\u0005%\u0000\u0000\u011d%\u0001\u0000\u0000\u0000\u011e"+
		"\u011f\u0005%\u0000\u0000\u011f\'\u0001\u0000\u0000\u0000\u0120\u0121"+
		"\u0005%\u0000\u0000\u0121)\u0001\u0000\u0000\u0000\u0122\u0123\u0003,"+
		"\u0016\u0000\u0123\u0124\u0007\u0000\u0000\u0000\u0124\u0125\u0007\u0001"+
		"\u0000\u0000\u0125+\u0001\u0000\u0000\u0000\u0126\u0127\u0005\'\u0000"+
		"\u0000\u0127-\u0001\u0000\u0000\u0000\u0128\u0129\u0005(\u0000\u0000\u0129"+
		"/\u0001\u0000\u0000\u0000\u001914:DPX]ju\u0081\u0089\u0091\u0096\u009c"+
		"\u00ad\u00be\u00cf\u00d7\u00dd\u00e3\u00e8\u00f4\u00f7\u0102\u0110";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}