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
		SEMICOLON=1, COLON=2, DOT=3, COMMA=4, BRANCH=5, FROM=6, ASSIGN=7, UNDERSCORE=8, 
		STAR=9, LPAR=10, RPAR=11, SLPAR=12, SRPAR=13, CLPAR=14, CRPAR=15, AT=16, 
		IF=17, THEN=18, ELSE=19, END=20, PREAMBLE=21, ENDPREAMBLE=22, CONST=23, 
		WS=24, DOUBLE_STRING=25, INTEGER=26, ID=27;
	public static final int
		RULE_protocol = 0, RULE_preamble = 1, RULE_statement = 2, RULE_ifThenElse = 3, 
		RULE_internalAction = 4, RULE_protocolID = 5, RULE_rate = 6, RULE_message = 7, 
		RULE_roleDef = 8, RULE_roleSpec = 9, RULE_roleGroup = 10, RULE_role = 11, 
		RULE_roleVar = 12, RULE_variableDecl = 13, RULE_cond = 14, RULE_id = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"protocol", "preamble", "statement", "ifThenElse", "internalAction", 
			"protocolID", "rate", "message", "roleDef", "roleSpec", "roleGroup", 
			"role", "roleVar", "variableDecl", "cond", "id"
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
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==PREAMBLE) {
				{
				setState(32);
				preamble();
				}
			}

			setState(36); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(35);
					roleDef();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(38); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
			setState(40);
			protocolID();
			setState(41);
			match(ASSIGN);
			setState(42);
			statement();
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
			setState(44);
			match(PREAMBLE);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==DOUBLE_STRING) {
				{
				{
				setState(45);
				variableDecl();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
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
		public TerminalNode FROM() { return getToken(LangParser.FROM, 0); }
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
		public TerminalNode LPAR() { return getToken(LangParser.LPAR, 0); }
		public List<TerminalNode> BRANCH() { return getTokens(LangParser.BRANCH); }
		public TerminalNode BRANCH(int i) {
			return getToken(LangParser.BRANCH, i);
		}
		public List<TerminalNode> SLPAR() { return getTokens(LangParser.SLPAR); }
		public TerminalNode SLPAR(int i) {
			return getToken(LangParser.SLPAR, i);
		}
		public List<TerminalNode> SRPAR() { return getTokens(LangParser.SRPAR); }
		public TerminalNode SRPAR(int i) {
			return getToken(LangParser.SRPAR, i);
		}
		public TerminalNode RPAR() { return getToken(LangParser.RPAR, 0); }
		public List<RateContext> rate() {
			return getRuleContexts(RateContext.class);
		}
		public RateContext rate(int i) {
			return getRuleContext(RateContext.class,i);
		}
		public InternalActionContext internalAction() {
			return getRuleContext(InternalActionContext.class,0);
		}
		public IfThenElseContext ifThenElse() {
			return getRuleContext(IfThenElseContext.class,0);
		}
		public ProtocolIDContext protocolID() {
			return getRuleContext(ProtocolIDContext.class,0);
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
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(53);
				role();
				setState(54);
				match(FROM);
				setState(55);
				role();
				setState(56);
				match(COLON);
				setState(57);
				message();
				setState(58);
				match(DOT);
				setState(59);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				role();
				setState(62);
				match(FROM);
				setState(63);
				role();
				setState(64);
				match(LPAR);
				setState(65);
				match(BRANCH);
				setState(66);
				match(SLPAR);
				setState(67);
				((StatementContext)_localctx).rate = rate();
				((StatementContext)_localctx).rateValues.add(((StatementContext)_localctx).rate);
				setState(68);
				match(SRPAR);
				setState(69);
				message();
				setState(70);
				match(COLON);
				setState(71);
				statement();
				setState(80); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(72);
					match(BRANCH);
					setState(73);
					match(SLPAR);
					setState(74);
					((StatementContext)_localctx).rate = rate();
					((StatementContext)_localctx).rateValues.add(((StatementContext)_localctx).rate);
					setState(75);
					match(SRPAR);
					setState(76);
					message();
					setState(77);
					match(COLON);
					setState(78);
					statement();
					}
					}
					setState(82); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BRANCH );
				setState(84);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(86);
				internalAction();
				setState(89);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(87);
					match(DOT);
					setState(88);
					statement();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(91);
				ifThenElse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(92);
				protocolID();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(93);
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
			setState(96);
			match(IF);
			setState(97);
			cond();
			setState(98);
			match(AT);
			setState(99);
			role();
			setState(100);
			match(THEN);
			setState(101);
			match(CLPAR);
			setState(102);
			((IfThenElseContext)_localctx).thenStat = statement();
			setState(103);
			match(CRPAR);
			setState(111);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE) {
				{
				{
				setState(104);
				match(ELSE);
				setState(105);
				match(CLPAR);
				setState(106);
				((IfThenElseContext)_localctx).elseStat = statement();
				setState(107);
				match(CRPAR);
				}
				}
				setState(113);
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
		enterRule(_localctx, 8, RULE_internalAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(CLPAR);
			setState(115);
			match(DOUBLE_STRING);
			setState(116);
			match(CRPAR);
			setState(117);
			match(AT);
			setState(118);
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
		enterRule(_localctx, 10, RULE_protocolID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
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
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
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
		enterRule(_localctx, 12, RULE_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
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
	public static class MessageContext extends ParserRuleContext {
		public TerminalNode DOUBLE_STRING() { return getToken(LangParser.DOUBLE_STRING, 0); }
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
		enterRule(_localctx, 14, RULE_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
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
	public static class RoleDefContext extends ParserRuleContext {
		public RoleGroupContext roleGroup() {
			return getRuleContext(RoleGroupContext.class,0);
		}
		public TerminalNode FROM() { return getToken(LangParser.FROM, 0); }
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
		enterRule(_localctx, 16, RULE_roleDef);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			roleGroup();
			setState(127);
			match(FROM);
			setState(129); 
			_errHandler.sync(this);
			_alt = 1;
			do {
				switch (_alt) {
				case 1:
					{
					{
					setState(128);
					roleSpec();
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(131); 
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
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
		public TerminalNode SEMICOLON() { return getToken(LangParser.SEMICOLON, 0); }
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
		enterRule(_localctx, 18, RULE_roleSpec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(133);
			role();
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(134);
				match(COLON);
				{
				setState(135);
				roleVar();
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(136);
					match(COMMA);
					setState(137);
					roleVar();
					}
					}
					setState(140); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				}
				}
			}

			setState(144);
			match(SEMICOLON);
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
		enterRule(_localctx, 20, RULE_roleGroup);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
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
	public static class RoleContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(LangParser.ID, 0); }
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
		enterRule(_localctx, 22, RULE_role);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
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
		enterRule(_localctx, 24, RULE_roleVar);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
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
		enterRule(_localctx, 26, RULE_variableDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(152);
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
		enterRule(_localctx, 28, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
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
		enterRule(_localctx, 30, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
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
		"\u0004\u0001\u001b\u009f\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001"+
		"\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004"+
		"\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007"+
		"\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b"+
		"\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007"+
		"\u000f\u0001\u0000\u0003\u0000\"\b\u0000\u0001\u0000\u0004\u0000%\b\u0000"+
		"\u000b\u0000\f\u0000&\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0005\u0001/\b\u0001\n\u0001\f\u00012\t\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0004\u0002"+
		"Q\b\u0002\u000b\u0002\f\u0002R\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0003\u0002Z\b\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0003\u0002_\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001"+
		"\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0005\u0003n\b\u0003\n\u0003"+
		"\f\u0003q\t\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001"+
		"\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001\u0006\u0001"+
		"\u0007\u0001\u0007\u0001\b\u0001\b\u0001\b\u0004\b\u0082\b\b\u000b\b\f"+
		"\b\u0083\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0004\t\u008b\b\t\u000b"+
		"\t\f\t\u008c\u0003\t\u008f\b\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\u000b"+
		"\u0001\u000b\u0001\f\u0001\f\u0001\r\u0001\r\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u000f\u0000\u0000\u0010\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a\u001c\u001e\u0000\u0000"+
		"\u009c\u0000!\u0001\u0000\u0000\u0000\u0002,\u0001\u0000\u0000\u0000\u0004"+
		"^\u0001\u0000\u0000\u0000\u0006`\u0001\u0000\u0000\u0000\br\u0001\u0000"+
		"\u0000\u0000\nx\u0001\u0000\u0000\u0000\fz\u0001\u0000\u0000\u0000\u000e"+
		"|\u0001\u0000\u0000\u0000\u0010~\u0001\u0000\u0000\u0000\u0012\u0085\u0001"+
		"\u0000\u0000\u0000\u0014\u0092\u0001\u0000\u0000\u0000\u0016\u0094\u0001"+
		"\u0000\u0000\u0000\u0018\u0096\u0001\u0000\u0000\u0000\u001a\u0098\u0001"+
		"\u0000\u0000\u0000\u001c\u009a\u0001\u0000\u0000\u0000\u001e\u009c\u0001"+
		"\u0000\u0000\u0000 \"\u0003\u0002\u0001\u0000! \u0001\u0000\u0000\u0000"+
		"!\"\u0001\u0000\u0000\u0000\"$\u0001\u0000\u0000\u0000#%\u0003\u0010\b"+
		"\u0000$#\u0001\u0000\u0000\u0000%&\u0001\u0000\u0000\u0000&$\u0001\u0000"+
		"\u0000\u0000&\'\u0001\u0000\u0000\u0000\'(\u0001\u0000\u0000\u0000()\u0003"+
		"\n\u0005\u0000)*\u0005\u0007\u0000\u0000*+\u0003\u0004\u0002\u0000+\u0001"+
		"\u0001\u0000\u0000\u0000,0\u0005\u0015\u0000\u0000-/\u0003\u001a\r\u0000"+
		".-\u0001\u0000\u0000\u0000/2\u0001\u0000\u0000\u00000.\u0001\u0000\u0000"+
		"\u000001\u0001\u0000\u0000\u000013\u0001\u0000\u0000\u000020\u0001\u0000"+
		"\u0000\u000034\u0005\u0016\u0000\u00004\u0003\u0001\u0000\u0000\u0000"+
		"56\u0003\u0016\u000b\u000067\u0005\u0006\u0000\u000078\u0003\u0016\u000b"+
		"\u000089\u0005\u0002\u0000\u00009:\u0003\u000e\u0007\u0000:;\u0005\u0003"+
		"\u0000\u0000;<\u0003\u0004\u0002\u0000<_\u0001\u0000\u0000\u0000=>\u0003"+
		"\u0016\u000b\u0000>?\u0005\u0006\u0000\u0000?@\u0003\u0016\u000b\u0000"+
		"@A\u0005\n\u0000\u0000AB\u0005\u0005\u0000\u0000BC\u0005\f\u0000\u0000"+
		"CD\u0003\f\u0006\u0000DE\u0005\r\u0000\u0000EF\u0003\u000e\u0007\u0000"+
		"FG\u0005\u0002\u0000\u0000GP\u0003\u0004\u0002\u0000HI\u0005\u0005\u0000"+
		"\u0000IJ\u0005\f\u0000\u0000JK\u0003\f\u0006\u0000KL\u0005\r\u0000\u0000"+
		"LM\u0003\u000e\u0007\u0000MN\u0005\u0002\u0000\u0000NO\u0003\u0004\u0002"+
		"\u0000OQ\u0001\u0000\u0000\u0000PH\u0001\u0000\u0000\u0000QR\u0001\u0000"+
		"\u0000\u0000RP\u0001\u0000\u0000\u0000RS\u0001\u0000\u0000\u0000ST\u0001"+
		"\u0000\u0000\u0000TU\u0005\u000b\u0000\u0000U_\u0001\u0000\u0000\u0000"+
		"VY\u0003\b\u0004\u0000WX\u0005\u0003\u0000\u0000XZ\u0003\u0004\u0002\u0000"+
		"YW\u0001\u0000\u0000\u0000YZ\u0001\u0000\u0000\u0000Z_\u0001\u0000\u0000"+
		"\u0000[_\u0003\u0006\u0003\u0000\\_\u0003\n\u0005\u0000]_\u0005\u0014"+
		"\u0000\u0000^5\u0001\u0000\u0000\u0000^=\u0001\u0000\u0000\u0000^V\u0001"+
		"\u0000\u0000\u0000^[\u0001\u0000\u0000\u0000^\\\u0001\u0000\u0000\u0000"+
		"^]\u0001\u0000\u0000\u0000_\u0005\u0001\u0000\u0000\u0000`a\u0005\u0011"+
		"\u0000\u0000ab\u0003\u001c\u000e\u0000bc\u0005\u0010\u0000\u0000cd\u0003"+
		"\u0016\u000b\u0000de\u0005\u0012\u0000\u0000ef\u0005\u000e\u0000\u0000"+
		"fg\u0003\u0004\u0002\u0000go\u0005\u000f\u0000\u0000hi\u0005\u0013\u0000"+
		"\u0000ij\u0005\u000e\u0000\u0000jk\u0003\u0004\u0002\u0000kl\u0005\u000f"+
		"\u0000\u0000ln\u0001\u0000\u0000\u0000mh\u0001\u0000\u0000\u0000nq\u0001"+
		"\u0000\u0000\u0000om\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000"+
		"p\u0007\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000rs\u0005\u000e"+
		"\u0000\u0000st\u0005\u0019\u0000\u0000tu\u0005\u000f\u0000\u0000uv\u0005"+
		"\u0010\u0000\u0000vw\u0003\u0016\u000b\u0000w\t\u0001\u0000\u0000\u0000"+
		"xy\u0005\u001b\u0000\u0000y\u000b\u0001\u0000\u0000\u0000z{\u0005\u001b"+
		"\u0000\u0000{\r\u0001\u0000\u0000\u0000|}\u0005\u0019\u0000\u0000}\u000f"+
		"\u0001\u0000\u0000\u0000~\u007f\u0003\u0014\n\u0000\u007f\u0081\u0005"+
		"\u0006\u0000\u0000\u0080\u0082\u0003\u0012\t\u0000\u0081\u0080\u0001\u0000"+
		"\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0081\u0001\u0000"+
		"\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0011\u0001\u0000"+
		"\u0000\u0000\u0085\u008e\u0003\u0016\u000b\u0000\u0086\u0087\u0005\u0002"+
		"\u0000\u0000\u0087\u008a\u0003\u0018\f\u0000\u0088\u0089\u0005\u0004\u0000"+
		"\u0000\u0089\u008b\u0003\u0018\f\u0000\u008a\u0088\u0001\u0000\u0000\u0000"+
		"\u008b\u008c\u0001\u0000\u0000\u0000\u008c\u008a\u0001\u0000\u0000\u0000"+
		"\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u008f\u0001\u0000\u0000\u0000"+
		"\u008e\u0086\u0001\u0000\u0000\u0000\u008e\u008f\u0001\u0000\u0000\u0000"+
		"\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0091\u0005\u0001\u0000\u0000"+
		"\u0091\u0013\u0001\u0000\u0000\u0000\u0092\u0093\u0005\u001b\u0000\u0000"+
		"\u0093\u0015\u0001\u0000\u0000\u0000\u0094\u0095\u0005\u001b\u0000\u0000"+
		"\u0095\u0017\u0001\u0000\u0000\u0000\u0096\u0097\u0005\u0019\u0000\u0000"+
		"\u0097\u0019\u0001\u0000\u0000\u0000\u0098\u0099\u0005\u0019\u0000\u0000"+
		"\u0099\u001b\u0001\u0000\u0000\u0000\u009a\u009b\u0005\u0019\u0000\u0000"+
		"\u009b\u001d\u0001\u0000\u0000\u0000\u009c\u009d\u0005\u001b\u0000\u0000"+
		"\u009d\u001f\u0001\u0000\u0000\u0000\n!&0RY^o\u0083\u008c\u008e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}