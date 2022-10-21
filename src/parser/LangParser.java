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
		SEMICOLON=1, COLON=2, DOT=3, BRANCH=4, FROM=5, ASSIGN=6, UNDERSCORE=7, 
		STAR=8, LPAR=9, RPAR=10, SLPAR=11, SRPAR=12, CLPAR=13, CRPAR=14, IF=15, 
		THEN=16, ELSE=17, END=18, WS=19, DOUBLE_STRING=20, INTEGER=21, ID=22;
	public static final int
		RULE_protocol = 0, RULE_statement = 1, RULE_ifThenElse = 2, RULE_internalAction = 3, 
		RULE_protocolID = 4, RULE_rate = 5, RULE_message = 6, RULE_role = 7, RULE_cond = 8, 
		RULE_id = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"protocol", "statement", "ifThenElse", "internalAction", "protocolID", 
			"rate", "message", "role", "cond", "id"
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
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			protocolID();
			setState(21);
			match(ASSIGN);
			setState(22);
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
		enterRule(_localctx, 2, RULE_statement);
		int _la;
		try {
			setState(65);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(24);
				role();
				setState(25);
				match(FROM);
				setState(26);
				role();
				setState(27);
				match(COLON);
				setState(28);
				message();
				setState(29);
				match(DOT);
				setState(30);
				statement();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(32);
				role();
				setState(33);
				match(FROM);
				setState(34);
				role();
				setState(35);
				match(LPAR);
				setState(36);
				match(BRANCH);
				setState(37);
				match(SLPAR);
				setState(38);
				((StatementContext)_localctx).rate = rate();
				((StatementContext)_localctx).rateValues.add(((StatementContext)_localctx).rate);
				setState(39);
				match(SRPAR);
				setState(40);
				message();
				setState(41);
				match(COLON);
				setState(42);
				statement();
				setState(51); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(43);
					match(BRANCH);
					setState(44);
					match(SLPAR);
					setState(45);
					((StatementContext)_localctx).rate = rate();
					((StatementContext)_localctx).rateValues.add(((StatementContext)_localctx).rate);
					setState(46);
					match(SRPAR);
					setState(47);
					message();
					setState(48);
					match(COLON);
					setState(49);
					statement();
					}
					}
					setState(53); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==BRANCH );
				setState(55);
				match(RPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(57);
				internalAction();
				setState(60);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==DOT) {
					{
					setState(58);
					match(DOT);
					setState(59);
					statement();
					}
				}

				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(62);
				ifThenElse();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(63);
				protocolID();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(64);
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
		enterRule(_localctx, 4, RULE_ifThenElse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(IF);
			setState(68);
			cond();
			setState(69);
			match(THEN);
			setState(70);
			match(CLPAR);
			setState(71);
			((IfThenElseContext)_localctx).thenStat = statement();
			setState(72);
			match(CRPAR);
			setState(80);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==ELSE) {
				{
				{
				setState(73);
				match(ELSE);
				setState(74);
				match(CLPAR);
				setState(75);
				((IfThenElseContext)_localctx).elseStat = statement();
				setState(76);
				match(CRPAR);
				}
				}
				setState(82);
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
		enterRule(_localctx, 6, RULE_internalAction);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(CLPAR);
			setState(84);
			match(DOUBLE_STRING);
			setState(85);
			match(CRPAR);
			setState(86);
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
		enterRule(_localctx, 8, RULE_protocolID);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
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
		enterRule(_localctx, 10, RULE_rate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
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
		enterRule(_localctx, 12, RULE_message);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
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
		enterRule(_localctx, 14, RULE_role);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(94);
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
		enterRule(_localctx, 16, RULE_cond);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
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
		enterRule(_localctx, 18, RULE_id);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(98);
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
		"\u0004\u0001\u0016e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0000"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0004\u00014\b\u0001\u000b\u0001"+
		"\f\u00015\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0003\u0001=\b\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001"+
		"B\b\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002O\b\u0002\n\u0002\f\u0002R\t\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0000\u0000\n\u0000\u0002\u0004\u0006\b\n\f"+
		"\u000e\u0010\u0012\u0000\u0000b\u0000\u0014\u0001\u0000\u0000\u0000\u0002"+
		"A\u0001\u0000\u0000\u0000\u0004C\u0001\u0000\u0000\u0000\u0006S\u0001"+
		"\u0000\u0000\u0000\bX\u0001\u0000\u0000\u0000\nZ\u0001\u0000\u0000\u0000"+
		"\f\\\u0001\u0000\u0000\u0000\u000e^\u0001\u0000\u0000\u0000\u0010`\u0001"+
		"\u0000\u0000\u0000\u0012b\u0001\u0000\u0000\u0000\u0014\u0015\u0003\b"+
		"\u0004\u0000\u0015\u0016\u0005\u0006\u0000\u0000\u0016\u0017\u0003\u0002"+
		"\u0001\u0000\u0017\u0001\u0001\u0000\u0000\u0000\u0018\u0019\u0003\u000e"+
		"\u0007\u0000\u0019\u001a\u0005\u0005\u0000\u0000\u001a\u001b\u0003\u000e"+
		"\u0007\u0000\u001b\u001c\u0005\u0002\u0000\u0000\u001c\u001d\u0003\f\u0006"+
		"\u0000\u001d\u001e\u0005\u0003\u0000\u0000\u001e\u001f\u0003\u0002\u0001"+
		"\u0000\u001fB\u0001\u0000\u0000\u0000 !\u0003\u000e\u0007\u0000!\"\u0005"+
		"\u0005\u0000\u0000\"#\u0003\u000e\u0007\u0000#$\u0005\t\u0000\u0000$%"+
		"\u0005\u0004\u0000\u0000%&\u0005\u000b\u0000\u0000&\'\u0003\n\u0005\u0000"+
		"\'(\u0005\f\u0000\u0000()\u0003\f\u0006\u0000)*\u0005\u0002\u0000\u0000"+
		"*3\u0003\u0002\u0001\u0000+,\u0005\u0004\u0000\u0000,-\u0005\u000b\u0000"+
		"\u0000-.\u0003\n\u0005\u0000./\u0005\f\u0000\u0000/0\u0003\f\u0006\u0000"+
		"01\u0005\u0002\u0000\u000012\u0003\u0002\u0001\u000024\u0001\u0000\u0000"+
		"\u00003+\u0001\u0000\u0000\u000045\u0001\u0000\u0000\u000053\u0001\u0000"+
		"\u0000\u000056\u0001\u0000\u0000\u000067\u0001\u0000\u0000\u000078\u0005"+
		"\n\u0000\u00008B\u0001\u0000\u0000\u00009<\u0003\u0006\u0003\u0000:;\u0005"+
		"\u0003\u0000\u0000;=\u0003\u0002\u0001\u0000<:\u0001\u0000\u0000\u0000"+
		"<=\u0001\u0000\u0000\u0000=B\u0001\u0000\u0000\u0000>B\u0003\u0004\u0002"+
		"\u0000?B\u0003\b\u0004\u0000@B\u0005\u0012\u0000\u0000A\u0018\u0001\u0000"+
		"\u0000\u0000A \u0001\u0000\u0000\u0000A9\u0001\u0000\u0000\u0000A>\u0001"+
		"\u0000\u0000\u0000A?\u0001\u0000\u0000\u0000A@\u0001\u0000\u0000\u0000"+
		"B\u0003\u0001\u0000\u0000\u0000CD\u0005\u000f\u0000\u0000DE\u0003\u0010"+
		"\b\u0000EF\u0005\u0010\u0000\u0000FG\u0005\r\u0000\u0000GH\u0003\u0002"+
		"\u0001\u0000HP\u0005\u000e\u0000\u0000IJ\u0005\u0011\u0000\u0000JK\u0005"+
		"\r\u0000\u0000KL\u0003\u0002\u0001\u0000LM\u0005\u000e\u0000\u0000MO\u0001"+
		"\u0000\u0000\u0000NI\u0001\u0000\u0000\u0000OR\u0001\u0000\u0000\u0000"+
		"PN\u0001\u0000\u0000\u0000PQ\u0001\u0000\u0000\u0000Q\u0005\u0001\u0000"+
		"\u0000\u0000RP\u0001\u0000\u0000\u0000ST\u0005\r\u0000\u0000TU\u0005\u0014"+
		"\u0000\u0000UV\u0005\u000e\u0000\u0000VW\u0003\u000e\u0007\u0000W\u0007"+
		"\u0001\u0000\u0000\u0000XY\u0005\u0016\u0000\u0000Y\t\u0001\u0000\u0000"+
		"\u0000Z[\u0005\u0016\u0000\u0000[\u000b\u0001\u0000\u0000\u0000\\]\u0005"+
		"\u0014\u0000\u0000]\r\u0001\u0000\u0000\u0000^_\u0005\u0016\u0000\u0000"+
		"_\u000f\u0001\u0000\u0000\u0000`a\u0005\u0014\u0000\u0000a\u0011\u0001"+
		"\u0000\u0000\u0000bc\u0005\u0016\u0000\u0000c\u0013\u0001\u0000\u0000"+
		"\u0000\u00045<AP";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}