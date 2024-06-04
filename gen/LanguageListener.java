// Generated from /Users/adele/IdeaProjects/Ultimate/choreography-to-PRISM/Chor-PRISM/Language.g4 by ANTLR 4.12.0

    package parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link LanguageParser}.
 */
public interface LanguageListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link LanguageParser#protocol}.
	 * @param ctx the parse tree
	 */
	void enterProtocol(LanguageParser.ProtocolContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#protocol}.
	 * @param ctx the parse tree
	 */
	void exitProtocol(LanguageParser.ProtocolContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#protocolID}.
	 * @param ctx the parse tree
	 */
	void enterProtocolID(LanguageParser.ProtocolIDContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#protocolID}.
	 * @param ctx the parse tree
	 */
	void exitProtocolID(LanguageParser.ProtocolIDContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void enterBlockStatement(LanguageParser.BlockStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#blockStatement}.
	 * @param ctx the parse tree
	 */
	void exitBlockStatement(LanguageParser.BlockStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(LanguageParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(LanguageParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(LanguageParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(LanguageParser.BranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#branchStat}.
	 * @param ctx the parse tree
	 */
	void enterBranchStat(LanguageParser.BranchStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#branchStat}.
	 * @param ctx the parse tree
	 */
	void exitBranchStat(LanguageParser.BranchStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#commStat}.
	 * @param ctx the parse tree
	 */
	void enterCommStat(LanguageParser.CommStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#commStat}.
	 * @param ctx the parse tree
	 */
	void exitCommStat(LanguageParser.CommStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#ifThenElse}.
	 * @param ctx the parse tree
	 */
	void enterIfThenElse(LanguageParser.IfThenElseContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#ifThenElse}.
	 * @param ctx the parse tree
	 */
	void exitIfThenElse(LanguageParser.IfThenElseContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#rec}.
	 * @param ctx the parse tree
	 */
	void enterRec(LanguageParser.RecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#rec}.
	 * @param ctx the parse tree
	 */
	void exitRec(LanguageParser.RecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#end}.
	 * @param ctx the parse tree
	 */
	void enterEnd(LanguageParser.EndContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#end}.
	 * @param ctx the parse tree
	 */
	void exitEnd(LanguageParser.EndContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#internalAction}.
	 * @param ctx the parse tree
	 */
	void enterInternalAction(LanguageParser.InternalActionContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#internalAction}.
	 * @param ctx the parse tree
	 */
	void exitInternalAction(LanguageParser.InternalActionContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#updates}.
	 * @param ctx the parse tree
	 */
	void enterUpdates(LanguageParser.UpdatesContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#updates}.
	 * @param ctx the parse tree
	 */
	void exitUpdates(LanguageParser.UpdatesContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#message}.
	 * @param ctx the parse tree
	 */
	void enterMessage(LanguageParser.MessageContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#message}.
	 * @param ctx the parse tree
	 */
	void exitMessage(LanguageParser.MessageContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#actions}.
	 * @param ctx the parse tree
	 */
	void enterActions(LanguageParser.ActionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#actions}.
	 * @param ctx the parse tree
	 */
	void exitActions(LanguageParser.ActionsContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#loop}.
	 * @param ctx the parse tree
	 */
	void enterLoop(LanguageParser.LoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#loop}.
	 * @param ctx the parse tree
	 */
	void exitLoop(LanguageParser.LoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#preamble}.
	 * @param ctx the parse tree
	 */
	void enterPreamble(LanguageParser.PreambleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#preamble}.
	 * @param ctx the parse tree
	 */
	void exitPreamble(LanguageParser.PreambleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#varDef}.
	 * @param ctx the parse tree
	 */
	void enterVarDef(LanguageParser.VarDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#varDef}.
	 * @param ctx the parse tree
	 */
	void exitVarDef(LanguageParser.VarDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#modelType}.
	 * @param ctx the parse tree
	 */
	void enterModelType(LanguageParser.ModelTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#modelType}.
	 * @param ctx the parse tree
	 */
	void exitModelType(LanguageParser.ModelTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#roleDef}.
	 * @param ctx the parse tree
	 */
	void enterRoleDef(LanguageParser.RoleDefContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#roleDef}.
	 * @param ctx the parse tree
	 */
	void exitRoleDef(LanguageParser.RoleDefContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#roleSpec}.
	 * @param ctx the parse tree
	 */
	void enterRoleSpec(LanguageParser.RoleSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#roleSpec}.
	 * @param ctx the parse tree
	 */
	void exitRoleSpec(LanguageParser.RoleSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#roleGroup}.
	 * @param ctx the parse tree
	 */
	void enterRoleGroup(LanguageParser.RoleGroupContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#roleGroup}.
	 * @param ctx the parse tree
	 */
	void exitRoleGroup(LanguageParser.RoleGroupContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#roleIndex}.
	 * @param ctx the parse tree
	 */
	void enterRoleIndex(LanguageParser.RoleIndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#roleIndex}.
	 * @param ctx the parse tree
	 */
	void exitRoleIndex(LanguageParser.RoleIndexContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#indexSpec}.
	 * @param ctx the parse tree
	 */
	void enterIndexSpec(LanguageParser.IndexSpecContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#indexSpec}.
	 * @param ctx the parse tree
	 */
	void exitIndexSpec(LanguageParser.IndexSpecContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#role}.
	 * @param ctx the parse tree
	 */
	void enterRole(LanguageParser.RoleContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#role}.
	 * @param ctx the parse tree
	 */
	void exitRole(LanguageParser.RoleContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#roleVar}.
	 * @param ctx the parse tree
	 */
	void enterRoleVar(LanguageParser.RoleVarContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#roleVar}.
	 * @param ctx the parse tree
	 */
	void exitRoleVar(LanguageParser.RoleVarContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void enterVariableDecl(LanguageParser.VariableDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#variableDecl}.
	 * @param ctx the parse tree
	 */
	void exitVariableDecl(LanguageParser.VariableDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#rate}.
	 * @param ctx the parse tree
	 */
	void enterRate(LanguageParser.RateContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#rate}.
	 * @param ctx the parse tree
	 */
	void exitRate(LanguageParser.RateContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#cond}.
	 * @param ctx the parse tree
	 */
	void enterCond(LanguageParser.CondContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#cond}.
	 * @param ctx the parse tree
	 */
	void exitCond(LanguageParser.CondContext ctx);
	/**
	 * Enter a parse tree produced by {@link LanguageParser#index}.
	 * @param ctx the parse tree
	 */
	void enterIndex(LanguageParser.IndexContext ctx);
	/**
	 * Exit a parse tree produced by {@link LanguageParser#index}.
	 * @param ctx the parse tree
	 */
	void exitIndex(LanguageParser.IndexContext ctx);
}