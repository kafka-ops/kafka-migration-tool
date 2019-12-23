// Generated from MigrationsParser.g4 by ANTLR 4.7.2
package com.purbon.kafka-migrations.grammar;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link MigrationsParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface MigrationsParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link MigrationsParser#migration_type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMigration_type(MigrationsParser.Migration_typeContext ctx);
	/**
	 * Visit a parse tree produced by {@link MigrationsParser#operation}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOperation(MigrationsParser.OperationContext ctx);
	/**
	 * Visit a parse tree produced by {@link MigrationsParser#op_body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOp_body(MigrationsParser.Op_bodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link MigrationsParser#function}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction(MigrationsParser.FunctionContext ctx);
}