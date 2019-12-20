// Generated from MigrationsParser.g4 by ANTLR 4.7.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MigrationsParser}.
 */
public interface MigrationsParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link MigrationsParser#migration_type}.
	 * @param ctx the parse tree
	 */
	void enterMigration_type(MigrationsParser.Migration_typeContext ctx);
	/**
	 * Exit a parse tree produced by {@link MigrationsParser#migration_type}.
	 * @param ctx the parse tree
	 */
	void exitMigration_type(MigrationsParser.Migration_typeContext ctx);
	/**
	 * Enter a parse tree produced by {@link MigrationsParser#operation}.
	 * @param ctx the parse tree
	 */
	void enterOperation(MigrationsParser.OperationContext ctx);
	/**
	 * Exit a parse tree produced by {@link MigrationsParser#operation}.
	 * @param ctx the parse tree
	 */
	void exitOperation(MigrationsParser.OperationContext ctx);
	/**
	 * Enter a parse tree produced by {@link MigrationsParser#op_body}.
	 * @param ctx the parse tree
	 */
	void enterOp_body(MigrationsParser.Op_bodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link MigrationsParser#op_body}.
	 * @param ctx the parse tree
	 */
	void exitOp_body(MigrationsParser.Op_bodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link MigrationsParser#function}.
	 * @param ctx the parse tree
	 */
	void enterFunction(MigrationsParser.FunctionContext ctx);
	/**
	 * Exit a parse tree produced by {@link MigrationsParser#function}.
	 * @param ctx the parse tree
	 */
	void exitFunction(MigrationsParser.FunctionContext ctx);
}