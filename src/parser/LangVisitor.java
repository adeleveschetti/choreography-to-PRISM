// Generated from java-escape by ANTLR 4.11.1
package parser;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link LangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface LangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link LangParser#protocol}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocol(LangParser.ProtocolContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(LangParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#ifThenElse}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfThenElse(LangParser.IfThenElseContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#internalAction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInternalAction(LangParser.InternalActionContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#protocolID}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProtocolID(LangParser.ProtocolIDContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#rate}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRate(LangParser.RateContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#message}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMessage(LangParser.MessageContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#role}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRole(LangParser.RoleContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#cond}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCond(LangParser.CondContext ctx);
	/**
	 * Visit a parse tree produced by {@link LangParser#id}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitId(LangParser.IdContext ctx);
}