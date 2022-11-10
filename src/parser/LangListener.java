// Generated from java-escape by ANTLR 4.11.1
package parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LangParser}.
 */
public interface LangListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LangParser#protocol}.
	 * @param ctx the parse tree
	 */
	void enterProtocol(LangParser.ProtocolContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#protocol}.
	 * @param ctx the parse tree
	 */
	void exitProtocol(LangParser.ProtocolContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#preamble}.
	 * @param ctx the parse tree
	 */
	void enterPreamble(LangParser.PreambleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#preamble}.
	 * @param ctx the parse tree
	 */
	void exitPreamble(LangParser.PreambleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LangParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LangParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#ifThenElse}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElse(LangParser.IfThenElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#ifThenElse}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElse(LangParser.IfThenElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#internalAction}.
	 * @param ctx the parse tree
	 */
	void enterInternalAction(LangParser.InternalActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#internalAction}.
	 * @param ctx the parse tree
	 */
	void exitInternalAction(LangParser.InternalActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#protocolID}.
	 * @param ctx the parse tree
	 */
	void enterProtocolID(LangParser.ProtocolIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#protocolID}.
	 * @param ctx the parse tree
	 */
	void exitProtocolID(LangParser.ProtocolIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#rate}.
	 * @param ctx the parse tree
	 */
	void enterRate(LangParser.RateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#rate}.
	 * @param ctx the parse tree
	 */
	void exitRate(LangParser.RateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#message}.
	 * @param ctx the parse tree
	 */
	void enterMessage(LangParser.MessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#message}.
	 * @param ctx the parse tree
	 */
	void exitMessage(LangParser.MessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#roleDef}.
	 * @param ctx the parse tree
	 */
	void enterRoleDef(LangParser.RoleDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#roleDef}.
	 * @param ctx the parse tree
	 */
	void exitRoleDef(LangParser.RoleDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#roleSpec}.
	 * @param ctx the parse tree
	 */
	void enterRoleSpec(LangParser.RoleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#roleSpec}.
	 * @param ctx the parse tree
	 */
	void exitRoleSpec(LangParser.RoleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#roleGroup}.
	 * @param ctx the parse tree
	 */
	void enterRoleGroup(LangParser.RoleGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#roleGroup}.
	 * @param ctx the parse tree
	 */
	void exitRoleGroup(LangParser.RoleGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#role}.
	 * @param ctx the parse tree
	 */
	void enterRole(LangParser.RoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#role}.
	 * @param ctx the parse tree
	 */
	void exitRole(LangParser.RoleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void enterForLoop(LangParser.ForLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#forLoop}.
	 * @param ctx the parse tree
	 */
	void exitForLoop(LangParser.ForLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#roleVar}.
	 * @param ctx the parse tree
	 */
	void enterRoleVar(LangParser.RoleVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#roleVar}.
	 * @param ctx the parse tree
	 */
	void exitRoleVar(LangParser.RoleVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableDecl(LangParser.VariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableDecl(LangParser.VariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(LangParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(LangParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link LangParser#id}.
	 * @param ctx the parse tree
	 */
	void enterId(LangParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by {@link LangParser#id}.
	 * @param ctx the parse tree
	 */
	void exitId(LangParser.IdContext ctx);
}