// Generated from KafkaMigrations.g4 by ANTLR 4.7.2
package com.purbon.kafka.migrations.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KafkaMigrationsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, WS=8, ID=9, ASSIGN=10, 
		SEMICOLON=11, SCHEMA_MIGRATION_LITERAL=12, TOPIC_MIGRATION_LITERAL=13, 
		OP_LITERAL=14, FUNCTION_OPEN_CODE_BLOCK=15, FUNCTION_CLOSE_CODE_BLOCK=16;
	public static final int
		RULE_migration = 0, RULE_apply_function = 1, RULE_revert_function = 2, 
		RULE_code_block = 3, RULE_method = 4, RULE_params_with_comma = 5, RULE_last_param = 6, 
		RULE_param = 7;
	private static String[] makeRuleNames() {
		return new String[] {
			"migration", "apply_function", "revert_function", "code_block", "method", 
			"params_with_comma", "last_param", "param"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'def'", "'up'", "'down'", "'('", "')'", "','", "'\"'", null, null, 
			"'='", "';'", "'SchemaMigration'", "'TopicMigration'", null, "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "WS", "ID", "ASSIGN", 
			"SEMICOLON", "SCHEMA_MIGRATION_LITERAL", "TOPIC_MIGRATION_LITERAL", "OP_LITERAL", 
			"FUNCTION_OPEN_CODE_BLOCK", "FUNCTION_CLOSE_CODE_BLOCK"
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
	public String getGrammarFileName() { return "KafkaMigrations.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public KafkaMigrationsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class MigrationContext extends ParserRuleContext {
		public TerminalNode OP_LITERAL() { return getToken(KafkaMigrationsParser.OP_LITERAL, 0); }
		public Apply_functionContext apply_function() {
			return getRuleContext(Apply_functionContext.class,0);
		}
		public Revert_functionContext revert_function() {
			return getRuleContext(Revert_functionContext.class,0);
		}
		public MigrationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_migration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterMigration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitMigration(this);
		}
	}

	public final MigrationContext migration() throws RecognitionException {
		MigrationContext _localctx = new MigrationContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_migration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(OP_LITERAL);
			setState(17);
			apply_function();
			setState(18);
			revert_function();
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

	public static class Apply_functionContext extends ParserRuleContext {
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(KafkaMigrationsParser.SEMICOLON, 0); }
		public Apply_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_apply_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterApply_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitApply_function(this);
		}
	}

	public final Apply_functionContext apply_function() throws RecognitionException {
		Apply_functionContext _localctx = new Apply_functionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_apply_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(20);
			match(T__0);
			setState(21);
			match(T__1);
			setState(22);
			code_block();
			setState(23);
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

	public static class Revert_functionContext extends ParserRuleContext {
		public Code_blockContext code_block() {
			return getRuleContext(Code_blockContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(KafkaMigrationsParser.SEMICOLON, 0); }
		public Revert_functionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_revert_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterRevert_function(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitRevert_function(this);
		}
	}

	public final Revert_functionContext revert_function() throws RecognitionException {
		Revert_functionContext _localctx = new Revert_functionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_revert_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(25);
			match(T__0);
			setState(26);
			match(T__2);
			setState(27);
			code_block();
			setState(28);
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

	public static class Code_blockContext extends ParserRuleContext {
		public TerminalNode FUNCTION_OPEN_CODE_BLOCK() { return getToken(KafkaMigrationsParser.FUNCTION_OPEN_CODE_BLOCK, 0); }
		public TerminalNode FUNCTION_CLOSE_CODE_BLOCK() { return getToken(KafkaMigrationsParser.FUNCTION_CLOSE_CODE_BLOCK, 0); }
		public List<MethodContext> method() {
			return getRuleContexts(MethodContext.class);
		}
		public MethodContext method(int i) {
			return getRuleContext(MethodContext.class,i);
		}
		public Code_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_code_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterCode_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitCode_block(this);
		}
	}

	public final Code_blockContext code_block() throws RecognitionException {
		Code_blockContext _localctx = new Code_blockContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_code_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(FUNCTION_OPEN_CODE_BLOCK);
			setState(32); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(31);
				method();
				}
				}
				setState(34); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(36);
			match(FUNCTION_CLOSE_CODE_BLOCK);
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

	public static class MethodContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(KafkaMigrationsParser.ID, 0); }
		public Params_with_commaContext params_with_comma() {
			return getRuleContext(Params_with_commaContext.class,0);
		}
		public Last_paramContext last_param() {
			return getRuleContext(Last_paramContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(KafkaMigrationsParser.SEMICOLON, 0); }
		public MethodContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_method; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitMethod(this);
		}
	}

	public final MethodContext method() throws RecognitionException {
		MethodContext _localctx = new MethodContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(38);
			match(ID);
			setState(39);
			match(T__3);
			setState(40);
			params_with_comma();
			setState(41);
			last_param();
			setState(42);
			match(T__4);
			setState(43);
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

	public static class Params_with_commaContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public Params_with_commaContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_params_with_comma; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterParams_with_comma(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitParams_with_comma(this);
		}
	}

	public final Params_with_commaContext params_with_comma() throws RecognitionException {
		Params_with_commaContext _localctx = new Params_with_commaContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_params_with_comma);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(45);
					param();
					setState(46);
					match(T__5);
					}
					} 
				}
				setState(52);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
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

	public static class Last_paramContext extends ParserRuleContext {
		public ParamContext param() {
			return getRuleContext(ParamContext.class,0);
		}
		public Last_paramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_last_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterLast_param(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitLast_param(this);
		}
	}

	public final Last_paramContext last_param() throws RecognitionException {
		Last_paramContext _localctx = new Last_paramContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_last_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__6) {
				{
				setState(53);
				param();
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

	public static class ParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(KafkaMigrationsParser.ID, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__6);
			setState(57);
			match(ID);
			setState(58);
			match(T__6);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\22?\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\6\5#\n\5\r\5\16\5$\3\5\3"+
		"\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\7\7\63\n\7\f\7\16\7\66\13\7"+
		"\3\b\5\b9\n\b\3\t\3\t\3\t\3\t\3\t\2\2\n\2\4\6\b\n\f\16\20\2\2\29\2\22"+
		"\3\2\2\2\4\26\3\2\2\2\6\33\3\2\2\2\b \3\2\2\2\n(\3\2\2\2\f\64\3\2\2\2"+
		"\168\3\2\2\2\20:\3\2\2\2\22\23\7\20\2\2\23\24\5\4\3\2\24\25\5\6\4\2\25"+
		"\3\3\2\2\2\26\27\7\3\2\2\27\30\7\4\2\2\30\31\5\b\5\2\31\32\7\r\2\2\32"+
		"\5\3\2\2\2\33\34\7\3\2\2\34\35\7\5\2\2\35\36\5\b\5\2\36\37\7\r\2\2\37"+
		"\7\3\2\2\2 \"\7\21\2\2!#\5\n\6\2\"!\3\2\2\2#$\3\2\2\2$\"\3\2\2\2$%\3\2"+
		"\2\2%&\3\2\2\2&\'\7\22\2\2\'\t\3\2\2\2()\7\13\2\2)*\7\6\2\2*+\5\f\7\2"+
		"+,\5\16\b\2,-\7\7\2\2-.\7\r\2\2.\13\3\2\2\2/\60\5\20\t\2\60\61\7\b\2\2"+
		"\61\63\3\2\2\2\62/\3\2\2\2\63\66\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65"+
		"\r\3\2\2\2\66\64\3\2\2\2\679\5\20\t\28\67\3\2\2\289\3\2\2\29\17\3\2\2"+
		"\2:;\7\t\2\2;<\7\13\2\2<=\7\t\2\2=\21\3\2\2\2\5$\648";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}