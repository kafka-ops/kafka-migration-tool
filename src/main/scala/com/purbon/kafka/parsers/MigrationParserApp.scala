package com.purbon.kafka.parsers

import com.purbon.kafka.migrations.grammar.{KafkaMigrationsListener, KafkaMigrationsParser}
import org.antlr.v4.runtime.ParserRuleContext
import org.antlr.v4.runtime.tree.{ErrorNode, TerminalNode}

import scala.collection.mutable

abstract class MigrationsParserAppBase extends KafkaMigrationsListener {

  // Generic grammar functions

  override def visitTerminal(node: TerminalNode): Unit = ???
  override def visitErrorNode(node: ErrorNode): Unit = ???
  override def enterEveryRule(ctx: ParserRuleContext): Unit = ???
  override def exitEveryRule(ctx: ParserRuleContext): Unit = ???

  override def exitMigration(ctx: KafkaMigrationsParser.MigrationContext): Unit = ???
  override def exitCode_block(ctx: KafkaMigrationsParser.Code_blockContext): Unit = ???
  override def exitMethod(ctx: KafkaMigrationsParser.MethodContext): Unit = ???

}
class MigrationParserApp extends MigrationsParserAppBase {

  var stack = new mutable.HashMap[String, String]

  def asChangeRequest: ChangeRequest = {
    new DummyChangeRequest
  }

  private class DummyChangeRequest extends ChangeRequest {
    override def up(): Unit = println("up")
    override def down(): Unit = println("down")
  }

  /**
   * Enter a parse tree produced by {@link KafkaMigrationsParser#migration}.
   *
   * @param ctx the parse tree
   */
  override def enterMigration(ctx: KafkaMigrationsParser.MigrationContext): Unit = {
    val exprText = ctx.getText
    println(s"Expression after tokenization = $exprText")

    val operationLiteral = ctx.OP_LITERAL().getText
    println(s"operationLiteral = $operationLiteral")
  }

  override def enterCode_block(ctx: KafkaMigrationsParser.Code_blockContext): Unit = ???
  override def enterMethod(ctx: KafkaMigrationsParser.MethodContext): Unit = ???


  override def enterApply_function(ctx: KafkaMigrationsParser.Apply_functionContext): Unit = ???
  override def exitApply_function(ctx: KafkaMigrationsParser.Apply_functionContext): Unit = ???

  override def enterRevert_function(ctx: KafkaMigrationsParser.Revert_functionContext): Unit = ???
  override def exitRevert_function(ctx: KafkaMigrationsParser.Revert_functionContext): Unit = ???
}
