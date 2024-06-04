// Generated from /Users/adele/IdeaProjects/Ultimate/choreography-to-PRISM/Chor-PRISM/Language.g4 by ANTLR 4.12.0

    package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LanguageParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LanguageVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LanguageParser#protocol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocol(LanguageParser.ProtocolContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#protocolID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocolID(LanguageParser.ProtocolIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(LanguageParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LanguageParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(LanguageParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#branchStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranchStat(LanguageParser.BranchStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#commStat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCommStat(LanguageParser.CommStatContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#ifThenElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElse(LanguageParser.IfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#rec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRec(LanguageParser.RecContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#end}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEnd(LanguageParser.EndContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#internalAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInternalAction(LanguageParser.InternalActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#updates}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUpdates(LanguageParser.UpdatesContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(LanguageParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#actions}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitActions(LanguageParser.ActionsContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#loop}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoop(LanguageParser.LoopContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#preamble}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPreamble(LanguageParser.PreambleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#varDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDef(LanguageParser.VarDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#modelType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModelType(LanguageParser.ModelTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#roleDef}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleDef(LanguageParser.RoleDefContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#roleSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleSpec(LanguageParser.RoleSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#roleGroup}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleGroup(LanguageParser.RoleGroupContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#roleIndex}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleIndex(LanguageParser.RoleIndexContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#indexSpec}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndexSpec(LanguageParser.IndexSpecContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#role}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRole(LanguageParser.RoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#roleVar}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRoleVar(LanguageParser.RoleVarContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#variableDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDecl(LanguageParser.VariableDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#rate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRate(LanguageParser.RateContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(LanguageParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link LanguageParser#index}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIndex(LanguageParser.IndexContext ctx);
}