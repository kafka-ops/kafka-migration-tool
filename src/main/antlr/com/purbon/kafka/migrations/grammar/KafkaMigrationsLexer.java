// Generated from KafkaMigrations.g4 by ANTLR 4.7.2
package com.purbon.kafka.migrations.grammar;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class KafkaMigrationsLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, WS=9, 
		ID=10, ASSIGN=11, SEMICOLON=12, SCHEMA_MIGRATION_LITERAL=13, TOPIC_MIGRATION_LITERAL=14, 
		ACCESS_MIGRATION_LITERAL=15, OP_LITERAL=16, FUNCTION_OPEN_CODE_BLOCK=17, 
		FUNCTION_CLOSE_CODE_BLOCK=18;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "WS", 
			"ID", "ASSIGN", "SEMICOLON", "SCHEMA_MIGRATION_LITERAL", "TOPIC_MIGRATION_LITERAL", 
			"ACCESS_MIGRATION_LITERAL", "OP_LITERAL", "FUNCTION_OPEN_CODE_BLOCK", 
			"FUNCTION_CLOSE_CODE_BLOCK"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'def'", "'up'", "'down'", "'var'", "'\"'", "'('", "')'", "','", 
			null, null, "'='", "';'", "'SchemaMigration'", "'TopicMigration'", "'AccessMigration'", 
			null, "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, "WS", "ID", "ASSIGN", 
			"SEMICOLON", "SCHEMA_MIGRATION_LITERAL", "TOPIC_MIGRATION_LITERAL", "ACCESS_MIGRATION_LITERAL", 
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


	public KafkaMigrationsLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "KafkaMigrations.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\24\u0089\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\5\3"+
		"\5\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\6\nA\n\n\r\n\16\nB\3\n"+
		"\3\n\3\13\6\13H\n\13\r\13\16\13I\3\f\3\f\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3"+
		"\20\3\21\3\21\3\21\5\21\u0082\n\21\3\21\3\21\3\22\3\22\3\23\3\23\2\2\24"+
		"\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20"+
		"\37\21!\22#\23%\24\3\2\4\5\2\13\f\17\17\"\"\5\2\62;C\\c|\2\u008c\2\3\3"+
		"\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2"+
		"\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3"+
		"\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2"+
		"%\3\2\2\2\3\'\3\2\2\2\5+\3\2\2\2\7.\3\2\2\2\t\63\3\2\2\2\13\67\3\2\2\2"+
		"\r9\3\2\2\2\17;\3\2\2\2\21=\3\2\2\2\23@\3\2\2\2\25G\3\2\2\2\27K\3\2\2"+
		"\2\31M\3\2\2\2\33O\3\2\2\2\35_\3\2\2\2\37n\3\2\2\2!\u0081\3\2\2\2#\u0085"+
		"\3\2\2\2%\u0087\3\2\2\2\'(\7f\2\2()\7g\2\2)*\7h\2\2*\4\3\2\2\2+,\7w\2"+
		"\2,-\7r\2\2-\6\3\2\2\2./\7f\2\2/\60\7q\2\2\60\61\7y\2\2\61\62\7p\2\2\62"+
		"\b\3\2\2\2\63\64\7x\2\2\64\65\7c\2\2\65\66\7t\2\2\66\n\3\2\2\2\678\7$"+
		"\2\28\f\3\2\2\29:\7*\2\2:\16\3\2\2\2;<\7+\2\2<\20\3\2\2\2=>\7.\2\2>\22"+
		"\3\2\2\2?A\t\2\2\2@?\3\2\2\2AB\3\2\2\2B@\3\2\2\2BC\3\2\2\2CD\3\2\2\2D"+
		"E\b\n\2\2E\24\3\2\2\2FH\t\3\2\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2"+
		"\2J\26\3\2\2\2KL\7?\2\2L\30\3\2\2\2MN\7=\2\2N\32\3\2\2\2OP\7U\2\2PQ\7"+
		"e\2\2QR\7j\2\2RS\7g\2\2ST\7o\2\2TU\7c\2\2UV\7O\2\2VW\7k\2\2WX\7i\2\2X"+
		"Y\7t\2\2YZ\7c\2\2Z[\7v\2\2[\\\7k\2\2\\]\7q\2\2]^\7p\2\2^\34\3\2\2\2_`"+
		"\7V\2\2`a\7q\2\2ab\7r\2\2bc\7k\2\2cd\7e\2\2de\7O\2\2ef\7k\2\2fg\7i\2\2"+
		"gh\7t\2\2hi\7c\2\2ij\7v\2\2jk\7k\2\2kl\7q\2\2lm\7p\2\2m\36\3\2\2\2no\7"+
		"C\2\2op\7e\2\2pq\7e\2\2qr\7g\2\2rs\7u\2\2st\7u\2\2tu\7O\2\2uv\7k\2\2v"+
		"w\7i\2\2wx\7t\2\2xy\7c\2\2yz\7v\2\2z{\7k\2\2{|\7q\2\2|}\7p\2\2} \3\2\2"+
		"\2~\u0082\5\33\16\2\177\u0082\5\35\17\2\u0080\u0082\5\37\20\2\u0081~\3"+
		"\2\2\2\u0081\177\3\2\2\2\u0081\u0080\3\2\2\2\u0082\u0083\3\2\2\2\u0083"+
		"\u0084\5\31\r\2\u0084\"\3\2\2\2\u0085\u0086\7}\2\2\u0086$\3\2\2\2\u0087"+
		"\u0088\7\177\2\2\u0088&\3\2\2\2\6\2BI\u0081\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}