package ast;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.v4.runtime.Token;

import ast.*;
import lib.Pair;
import parser.LangBaseVisitor;
import parser.LangParser;
import parser.LangParser.*;

public class LangVisitorImpl extends LangBaseVisitor<Node>{

	@Override
	public Node visitProtocol(ProtocolContext ctx) {
		Node preamble = null;
		int valueVar = -1;
		if(ctx.preamble()!=null) {
			preamble = visitPreamble(ctx.preamble());
		}

		ArrayList<Pair<String,Integer>> vars = null;
		if(ctx.varDef()!=null) {
			valueVar = Integer.valueOf(ctx.varDef().INTEGER().getText());
		}

		ArrayList<Pair<String,String>> roles = null;
		HashMap<String,ArrayList<String>> varRoles = null;

		if(ctx.roleDef()!=null) {
			roles = new ArrayList<Pair<String,String>>();
			varRoles = new HashMap<String,ArrayList<String>>();
			for(RoleDefContext el : ctx.roleDef()) {
				String role = "";
				if(el.role().roleGroup()!=null) {
					role = el.role().roleGroup().ID().getText();
					roles.add(new Pair<String,String>(el.role().roleGroup().ID().getText(),"1"));
				}
				if(el.role().roleIndex()!=null) { 

					roles.add(new Pair<String,String>(el.role().roleIndex().ID().getText()+"["+el.role().roleIndex().index().CHAR().getText()+"]",el.indexSpec().upperBound.getText()));
					role = el.role().roleIndex().ID().getText()+"["+el.role().roleIndex().index().CHAR().getText()+"]";
				}
				ArrayList<String> tmpVars = null;
				if(el.roleSpec()!=null && el.roleSpec().size()>0) {
					tmpVars = new ArrayList<String>();
					for(RoleVarContext tmp : el.roleSpec(0).roleVar()) {
						tmpVars.add(tmp.DOUBLE_STRING().getText());
					}
				}
				varRoles.put(role,tmpVars);
			}
		}

		return new ProtocolNode(ctx.protocolID().getText(),visitStatement(ctx.statement()), roles, preamble,varRoles, valueVar);
	}

	@Override 
	public Node visitPreamble(PreambleContext ctx){
		ArrayList<String> vars = new ArrayList<String>();
		for(VariableDeclContext el : ctx.variableDecl()) {
			vars.add(el.getText());
		}
		return new PreambleNode(vars);
	}

	@Override
	public Node visitStatement(StatementContext ctx) {
		if(ctx.ifElse()!=null) {
			String id = ctx.ifElse().condIf().index().getText();
			String op = ctx.ifElse().condIf().op.getText();
			if(ctx.ifElse().condIf().INTEGER()!=null) {
				int integer =  Integer.parseInt(ctx.ifElse().condIf().INTEGER().getText());
				return new IfElseNode(id,op,integer,visitStatement(ctx.ifElse().thenStat),visitStatement(ctx.ifElse().elseStat));
			}
			else {
				String par =  ctx.ifElse().condIf().ID().getText();
				return new IfElseNode(id,op,par,visitStatement(ctx.ifElse().thenStat),visitStatement(ctx.ifElse().elseStat));
			}

		}
		else if(ctx.BRANCH()!=null  && ctx.BRANCH().size()>0 ) {
			ArrayList<ArrayList<String>> messages = new ArrayList<ArrayList<String>>();
			ArrayList<ArrayList<Node>> loops = new ArrayList<ArrayList<Node>>();
			ArrayList<String> actions = null;
			ArrayList<ArrayList<String>> totActions = null;

			if(ctx.actions()!=null ) {
				totActions = new ArrayList<ArrayList<String>>();
				for(int i = 0; i<ctx.actions().size(); i++) {
					actions = new ArrayList<String>();
					for(Token el2 : ctx.actions().get(i).action) {
						actions.add(el2.getText().substring(1,el2.getText().length()-1));
					}
					totActions.add(actions);
				}
			}
			
			for(MessageContext el : ctx.message()) {
				ArrayList<String> tmp = new ArrayList<String>();
				for(Token el2 : el.actions().action) {
					tmp.add(el2.getText().substring(1,el2.getText().length()-1));
				}
				messages.add(tmp);
				ArrayList<Node> tmpLoop = new ArrayList<Node>();
				for(ForLoopContext el2 : el.forLoop()) {
					if(el2!=null) {
						tmpLoop.add(visitForLoop(el2));
					}
					else {
						tmpLoop.add(null);
					}
				}
				loops.add(tmpLoop);
			}
			ArrayList<String> rates = new ArrayList<String>();
			for(RateContext el : ctx.rateValues) {
				rates.add(el.getText().substring(1,el.getText().length()-1));
			}
			ArrayList<Node> stats = new ArrayList<Node>();
			for(StatementContext el : ctx.statement()) {
				stats.add(visitStatement(el));
			}
			String roleA = "";
			ArrayList<String> roleB = new ArrayList<String>();
			if(ctx.role(0).roleIndex()!=null) {
				roleA = ctx.role(0).roleIndex().ID().getText()+"["+ctx.role(0).roleIndex().index().CHAR().getText()+"]";
			}
			else {
				roleA = ctx.role(0).roleGroup().ID().getText();
			}

			for(int i=1; i<ctx.role().size(); i++) {
				String tmpRole = "";
				if(ctx.role(i).roleIndex()!=null) {
					if(ctx.role(i).roleIndex().BRANCH()!=null) {
						tmpRole = ctx.role(i).roleIndex().ID().getText()+"["+ctx.role(i).roleIndex().index().CHAR().getText()+"+"+ctx.role(i).roleIndex().INTEGER().toString()+"]";
					}
					else {
						tmpRole = ctx.role(i).roleIndex().ID().getText()+"["+ctx.role(i).roleIndex().index().CHAR().getText()+"]";
					}
				}
				else {
					tmpRole = ctx.role(i).roleGroup().ID().getText();

				}

				roleB.add(tmpRole);
			}
			/*
			if(ctx.role(1).roleIndex()!=null) {

				if(ctx.role(1).roleIndex().BRANCH()!=null) {
					roleB = ctx.role(1).roleIndex().ID().getText()+"["+ctx.role(1).roleIndex().index().CHAR().getText()+"+"+ctx.role(1).roleIndex().INTEGER().toString()+"]";
				}
				else {
					roleB = ctx.role(1).roleIndex().ID().getText()+"["+ctx.role(1).roleIndex().index().CHAR().getText()+"]";
				}
			}
			else {
				roleB = ctx.role(1).roleGroup().ID().getText();
			}*/
			return new BranchNode(roleA,roleB,rates,messages,stats,loops,totActions);
		}
		else if(ctx.internalAction()!=null) {
			Node stat = null;
			if (ctx.statement()!=null) {
				stat = visitStatement(ctx.statement(0));
			}
			String role = "";
			if(ctx.internalAction().role().roleIndex()!=null) {
				if(ctx.internalAction().role().roleIndex().BRANCH()!=null) {
					role = ctx.internalAction().role().roleIndex().ID().getText()+"["+ctx.internalAction().role().roleIndex().index().CHAR().getText()+"+"+ctx.internalAction().role().roleIndex().INTEGER().toString()+"]";
				}
				else {
					role = ctx.internalAction().role().roleIndex().ID().getText()+"["+ctx.internalAction().role().roleIndex().index().CHAR().getText()+"]";
				}			
			}
			else {
				role = ctx.internalAction().role().roleGroup().ID().getText();
			}
			if(ctx.rate()!=null && ctx.rate().size()>0) {
				String rate = ctx.rate().get(0).getText().substring(1,ctx.rate().get(0).getText().length()-1);
				return new InternalActionNode(ctx.internalAction().DOUBLE_STRING().getText().substring(1,ctx.internalAction().DOUBLE_STRING().getText().length()-1),role,stat,rate);
			}
			return new InternalActionNode(ctx.internalAction().DOUBLE_STRING().getText().substring(1,ctx.internalAction().DOUBLE_STRING().getText().length()-1),role,stat);
		}
		else if(ctx.ifThenElse()!=null) {
			return visitIfThenElse(ctx.ifThenElse());
		}
		else if(ctx.protocolID()!=null) {
			return new ProtocolIDNode(ctx.protocolID().ID().getText());
		}
		String rate = "";
		if(ctx.rate()!=null && ctx.rate().size()==1) {
			rate = ctx.rate().get(0).getText().substring(1,ctx.rate().get(0).getText().length()-1);
		}
		String roleA = "";
		ArrayList<String> roleB = new ArrayList<String>();
		if(ctx.role(0).roleIndex()!=null) {
			if(ctx.role(0).roleIndex().BRANCH()!=null) {
				roleA = ctx.role(0).roleIndex().ID().getText()+"["+ctx.role(0).roleIndex().index().CHAR().getText()+"+"+ctx.role(0).roleIndex().INTEGER().toString()+"]";
			}
			else {
				roleA = ctx.role(0).roleIndex().ID().getText()+"["+ctx.role(0).roleIndex().index().CHAR().getText()+"]";
			}
		}
		else {
			roleA = ctx.role(0).roleGroup().ID().getText();
		}
		for(int i=1; i<ctx.role().size(); i++) {
			String tmpRole = "";
			if(ctx.role(i).roleIndex()!=null) {
				if(ctx.role(i).roleIndex().BRANCH()!=null) {
					tmpRole = ctx.role(i).roleIndex().ID().getText()+"["+ctx.role(i).roleIndex().index().CHAR().getText()+"+"+ctx.role(i).roleIndex().INTEGER().toString()+"]";
				}
				else {
					tmpRole = ctx.role(i).roleIndex().ID().getText()+"["+ctx.role(i).roleIndex().index().CHAR().getText()+"]";
				}
			}
			else {
				tmpRole = ctx.role(i).roleGroup().ID().getText();

			}

			roleB.add(tmpRole);
		}
		/*if(ctx.role(1).roleIndex()!=null) {
			if(ctx.role(1).roleIndex().BRANCH()!=null) {
				roleB = ctx.role(1).roleIndex().ID().getText()+"["+ctx.role(1).roleIndex().index().CHAR().getText()+"+"+ctx.role(1).roleIndex().INTEGER().toString()+"]";
			}
			else {
				roleB = ctx.role(1).roleIndex().ID().getText()+"["+ctx.role(1).roleIndex().index().CHAR().getText()+"]";
			}		}
		else {
			roleB = ctx.role(1).roleGroup().ID().getText();
		}*/
		ArrayList<String> actions = null;
		if(ctx.actions()!=null ) {
			actions = new ArrayList<String>();
			for(ActionsContext el : ctx.actions()) {
				for(Token el2 : el.action) {
					actions.add(el2.getText().substring(1,el2.getText().length()-1));
				}
			}
		}
		ArrayList<String> message = new ArrayList<String>();
		for(Token el : ctx.message(0).actions().action) {
			message.add(el.getText().substring(1,el.getText().length()-1));
		}
		if(ctx.message(0).forLoop()!=null) {
			ArrayList<Node> loops = new ArrayList<Node>();
			for(ForLoopContext el2 : ctx.message(0).forLoop()) {
				if(el2!=null) {
					loops.add(visitForLoop(el2));
				}
				else {
					loops.add(null);
				}
			}
			return new CommunicationNode(roleA,roleB, message, visitStatement(ctx.statement(0)), loops,rate,actions);
		}
		return new CommunicationNode(roleA,roleB, message, visitStatement(ctx.statement(0)),rate,actions);

	}

	public Node visitIfThenElse(IfThenElseContext ctx) {
		Node elseStatement = null;
		if(ctx.elseStat!=null) {
			elseStatement = visitStatement(ctx.elseStat);
		}
		return new IfThenElseNode(ctx.role().getText(),ctx.cond().getText().substring(1,ctx.cond().getText().length()-1),visitStatement(ctx.thenStat),elseStatement);
	}

	public Node visitForLoop(ForLoopContext ctx) { // TODO : sistemare forloop con la nuova grammatica
		String role = "";
		if(ctx.role().roleIndex()!=null) {
			role = ctx.role().roleIndex().ID().getText()+"["+ctx.role().roleIndex().index().CHAR().getText()+"]";
		}
		else {
			role = ctx.role().roleGroup().ID().getText();
		}
		String message = "";
		for(Token el : ctx.actions().action) {
			message = message + el.getText().substring(1,el.getText().length()-1);
		}
		return new ForLoopNode(message,ctx.indexIteration.CHAR().getText(),ctx.upperBound.CHAR().getText(),ctx.op.getText(),role);
	}

}
