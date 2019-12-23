// Generated from MigrationsParser.g4 by ANTLR 4.7.2
package com.purbon.kafka-migrations.grammar;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MigrationsParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, BODY=2, ASSIGN=3, OP_SCHEMA=4, OP_TOPIC=5, FUNCTION_DEF=6, FUNCTION_UP=7, 
		FUNCTION_DOWN=8, FUNCTION_OPEN_CODE_BLOCK=9, FUNCTION_CLOSE_CODE_BLOCK=10;
	public static final int
		RULE_migration_type = 0, RULE_operation = 1, RULE_op_body = 2, RULE_function = 3;
	private static String[] makeRuleNames() {
		return new String[] {
			"migration_type", "operation", "op_body", "function"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, "'='", "'schema'", "'topic'", "'def'", "'up'", "'down'", 
			"'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "WS", "BODY", "ASSIGN", "OP_SCHEMA", "OP_TOPIC", "FUNCTION_DEF", 
			"FUNCTION_UP", "FUNCTION_DOWN", "FUNCTION_OPEN_CODE_BLOCK", "FUNCTION_CLOSE_CODE_BLOCK"
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
	public String getGrammarFileName() { return "MigrationsParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public MigrationsParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class Migration_typeContext extends ParserRuleContext {
		public TerminalNode OP_SCHEMA() { return getToken(MigrationsParser.OP_SCHEMA, 0); }
		public TerminalNode OP_TOPIC() { return getToken(MigrationsParser.OP_TOPIC, 0); }
		public Migration_typeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_migration_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).enterMigration_type(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).exitMigration_type(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MigrationsParserVisitor ) return ((MigrationsParserVisitor<? extends T>)visitor).visitMigration_type(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Migration_typeContext migration_type() throws RecognitionException {
		Migration_typeContext _localctx = new Migration_typeContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_migration_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(8);
			_la = _input.LA(1);
			if ( !(_la==OP_SCHEMA || _la==OP_TOPIC) ) {
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

	public static class OperationContext extends ParserRuleContext {
		public TerminalNode FUNCTION_DOWN() { return getToken(MigrationsParser.FUNCTION_DOWN, 0); }
		public TerminalNode FUNCTION_UP() { return getToken(MigrationsParser.FUNCTION_UP, 0); }
		public OperationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_operation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).enterOperation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).exitOperation(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MigrationsParserVisitor ) return ((MigrationsParserVisitor<? extends T>)visitor).visitOperation(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OperationContext operation() throws RecognitionException {
		OperationContext _localctx = new OperationContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_operation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			_la = _input.LA(1);
			if ( !(_la==FUNCTION_UP || _la==FUNCTION_DOWN) ) {
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

	public static class Op_bodyContext extends ParserRuleContext {
		public TerminalNode FUNCTION_OPEN_CODE_BLOCK() { return getToken(MigrationsParser.FUNCTION_OPEN_CODE_BLOCK, 0); }
		public TerminalNode BODY() { return getToken(MigrationsParser.BODY, 0); }
		public TerminalNode FUNCTION_CLOSE_CODE_BLOCK() { return getToken(MigrationsParser.FUNCTION_CLOSE_CODE_BLOCK, 0); }
		public Op_bodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_op_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).enterOp_body(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).exitOp_body(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MigrationsParserVisitor ) return ((MigrationsParserVisitor<? extends T>)visitor).visitOp_body(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Op_bodyContext op_body() throws RecognitionException {
		Op_bodyContext _localctx = new Op_bodyContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_op_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			match(FUNCTION_OPEN_CODE_BLOCK);
			setState(13);
			match(BODY);
			setState(14);
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

	public static class FunctionContext extends ParserRuleContext {
		public TerminalNode FUNCTION_DEF() { return getToken(MigrationsParser.FUNCTION_DEF, 0); }
		public OperationContext operation() {
			return getRuleContext(OperationContext.class,0);
		}
		public Op_bodyContext op_body() {
			return getRuleContext(Op_bodyContext.class,0);
		}
		public FunctionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).enterFunction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof MigrationsParserListener ) ((MigrationsParserListener)listener).exitFunction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof MigrationsParserVisitor ) return ((MigrationsParserVisitor<? extends T>)visitor).visitFunction(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FunctionContext function() throws RecognitionException {
		FunctionContext _localctx = new FunctionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_function);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(16);
			match(FUNCTION_DEF);
			setState(17);
			operation();
			setState(18);
			op_body();
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\f\27\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\3\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3"+
		"\5\2\2\6\2\4\6\b\2\4\3\2\6\7\3\2\t\n\2\22\2\n\3\2\2\2\4\f\3\2\2\2\6\16"+
		"\3\2\2\2\b\22\3\2\2\2\n\13\t\2\2\2\13\3\3\2\2\2\f\r\t\3\2\2\r\5\3\2\2"+
		"\2\16\17\7\13\2\2\17\20\7\4\2\2\20\21\7\f\2\2\21\7\3\2\2\2\22\23\7\b\2"+
		"\2\23\24\5\4\3\2\24\25\5\6\4\2\25\t\3\2\2\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}