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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, WS=9, 
		ID=10, NUM=11, ASSIGN=12, SEMICOLON=13, SCHEMA_MIGRATION_LITERAL=14, TOPIC_MIGRATION_LITERAL=15, 
		OP_LITERAL=16, FUNCTION_OPEN_CODE_BLOCK=17, FUNCTION_CLOSE_CODE_BLOCK=18;
	public static final int
		RULE_migration = 0, RULE_apply_function = 1, RULE_revert_function = 2, 
		RULE_code_block = 3, RULE_variable = 4, RULE_method = 5, RULE_params_with_comma = 6, 
		RULE_last_param = 7, RULE_param = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"migration", "apply_function", "revert_function", "code_block", "variable", 
			"method", "params_with_comma", "last_param", "param"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'def'", "'up'", "'down'", "'var'", "'\"'", "'('", "')'", "','", 
			null, null, null, "'='", "';'", "'SchemaMigration'", "'TopicMigration'", 
			null, "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "WS", "ID", "NUM", 
			"ASSIGN", "SEMICOLON", "SCHEMA_MIGRATION_LITERAL", "TOPIC_MIGRATION_LITERAL", 
			"OP_LITERAL", "FUNCTION_OPEN_CODE_BLOCK", "FUNCTION_CLOSE_CODE_BLOCK"
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
			setState(18);
			match(OP_LITERAL);
			setState(19);
			apply_function();
			setState(20);
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
			setState(22);
			match(T__0);
			setState(23);
			match(T__1);
			setState(24);
			code_block();
			setState(25);
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
			setState(27);
			match(T__0);
			setState(28);
			match(T__2);
			setState(29);
			code_block();
			setState(30);
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
		public List<VariableContext> variable() {
			return getRuleContexts(VariableContext.class);
		}
		public VariableContext variable(int i) {
			return getRuleContext(VariableContext.class,i);
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
			setState(32);
			match(FUNCTION_OPEN_CODE_BLOCK);
			setState(35); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				setState(35);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case ID:
					{
					setState(33);
					method();
					}
					break;
				case T__3:
					{
					setState(34);
					variable();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(37); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==T__3 || _la==ID );
			setState(39);
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

	public static class VariableContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(KafkaMigrationsParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(KafkaMigrationsParser.ID, i);
		}
		public TerminalNode ASSIGN() { return getToken(KafkaMigrationsParser.ASSIGN, 0); }
		public TerminalNode SEMICOLON() { return getToken(KafkaMigrationsParser.SEMICOLON, 0); }
		public TerminalNode NUM() { return getToken(KafkaMigrationsParser.NUM, 0); }
		public VariableContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variable; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).enterVariable(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof KafkaMigrationsListener ) ((KafkaMigrationsListener)listener).exitVariable(this);
		}
	}

	public final VariableContext variable() throws RecognitionException {
		VariableContext _localctx = new VariableContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_variable);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(41);
			match(T__3);
			setState(42);
			match(ID);
			setState(43);
			match(ASSIGN);
			setState(48);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				{
				setState(44);
				match(T__4);
				setState(45);
				match(ID);
				setState(46);
				match(T__4);
				}
				break;
			case NUM:
				{
				setState(47);
				match(NUM);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(50);
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
		enterRule(_localctx, 10, RULE_method);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(ID);
			setState(53);
			match(T__5);
			setState(54);
			params_with_comma();
			setState(55);
			last_param();
			setState(56);
			match(T__6);
			setState(57);
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
		enterRule(_localctx, 12, RULE_params_with_comma);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(59);
					param();
					setState(60);
					match(T__7);
					}
					} 
				}
				setState(66);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
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
		enterRule(_localctx, 14, RULE_last_param);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__4 || _la==ID) {
				{
				setState(67);
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
		enterRule(_localctx, 16, RULE_param);
		try {
			setState(74);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(70);
				match(T__4);
				setState(71);
				match(ID);
				setState(72);
				match(T__4);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(73);
				match(ID);
				}
				break;
			default:
				throw new NoViableAltException(this);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\24O\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\6\5&\n\5\r\5"+
		"\16\5\'\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\63\n\6\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\7\bA\n\b\f\b\16\bD\13\b\3\t\5\tG\n\t"+
		"\3\n\3\n\3\n\3\n\5\nM\n\n\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\2\2K\2\24"+
		"\3\2\2\2\4\30\3\2\2\2\6\35\3\2\2\2\b\"\3\2\2\2\n+\3\2\2\2\f\66\3\2\2\2"+
		"\16B\3\2\2\2\20F\3\2\2\2\22L\3\2\2\2\24\25\7\22\2\2\25\26\5\4\3\2\26\27"+
		"\5\6\4\2\27\3\3\2\2\2\30\31\7\3\2\2\31\32\7\4\2\2\32\33\5\b\5\2\33\34"+
		"\7\17\2\2\34\5\3\2\2\2\35\36\7\3\2\2\36\37\7\5\2\2\37 \5\b\5\2 !\7\17"+
		"\2\2!\7\3\2\2\2\"%\7\23\2\2#&\5\f\7\2$&\5\n\6\2%#\3\2\2\2%$\3\2\2\2&\'"+
		"\3\2\2\2\'%\3\2\2\2\'(\3\2\2\2()\3\2\2\2)*\7\24\2\2*\t\3\2\2\2+,\7\6\2"+
		"\2,-\7\f\2\2-\62\7\16\2\2./\7\7\2\2/\60\7\f\2\2\60\63\7\7\2\2\61\63\7"+
		"\r\2\2\62.\3\2\2\2\62\61\3\2\2\2\63\64\3\2\2\2\64\65\7\17\2\2\65\13\3"+
		"\2\2\2\66\67\7\f\2\2\678\7\b\2\289\5\16\b\29:\5\20\t\2:;\7\t\2\2;<\7\17"+
		"\2\2<\r\3\2\2\2=>\5\22\n\2>?\7\n\2\2?A\3\2\2\2@=\3\2\2\2AD\3\2\2\2B@\3"+
		"\2\2\2BC\3\2\2\2C\17\3\2\2\2DB\3\2\2\2EG\5\22\n\2FE\3\2\2\2FG\3\2\2\2"+
		"G\21\3\2\2\2HI\7\7\2\2IJ\7\f\2\2JM\7\7\2\2KM\7\f\2\2LH\3\2\2\2LK\3\2\2"+
		"\2M\23\3\2\2\2\b%\'\62BFL";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}