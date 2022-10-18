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