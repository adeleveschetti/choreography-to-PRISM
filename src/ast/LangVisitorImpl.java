package ast;

import java.util.ArrayList;
import java.util.HashMap;

import parser.LangBaseVisitor;
import parser.LangParser.*;

public class LangVisitorImpl extends LangBaseVisitor<Node>{

	@Override
	public Node visitProtocol(ProtocolContext ctx) {
		ArrayList<String> roles = new ArrayList<String>();
		HashMap<String,ArrayList<String>> rolesVars = new HashMap<String,ArrayList<String>>();
		ArrayList<ArrayList<String>> rolesGroups = new ArrayList<ArrayList<String>>();

		for(int i = 0; i<ctx.roleDef().size(); i++) {
			ArrayList<String> tmpVar = new ArrayList<String>();
			ArrayList<String> tmpRoles = new ArrayList<String>();
			for(int j=0; j<ctx.roleDef().get(i).roleSpec().size(); j++) {
				tmpRoles.add(ctx.roleDef().get(i).roleSpec().get(j).role().getText());
				if(ctx.roleDef().get(i).roleSpec().get(j).roleVar().size()>0) {
					tmpVar = new ArrayList<String>();
					for(RoleVarContext el : ctx.roleDef().get(i).roleSpec().get(j).roleVar()) {
						tmpVar.add(el.DOUBLE_STRING().getText());
					}
					rolesVars.put(ctx.roleDef().get(i).roleSpec().get(j).role().getText(),tmpVar);
				}
			}
			rolesGroups.add(tmpRoles);
		}
		

		Node preamble = null;
		if(ctx.preamble()!=null) {
			preamble = visitPreamble(ctx.preamble());
		}
		return new ProtocolNode(ctx.protocolID().getText(),visitStatement(ctx.statement()), rolesGroups, preamble,rolesVars);
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
		if(ctx.rate().size()>0) {
			ArrayList<String> messages = new ArrayList<String>();
			for(MessageContext el : ctx.message()) {
				messages.add(el.getText().substring(1,el.getText().length()-1));
			}
			ArrayList<String> rates = new ArrayList<String>();
			for(RateContext el : ctx.rateValues) {
				rates.add(el.getText());
			}
			ArrayList<Node> stats = new ArrayList<Node>();
			for(StatementContext el : ctx.statement()) {
				stats.add(visitStatement(el));
			}
			return new BranchNode(ctx.role(0).ID().getText(),ctx.role(1).ID().getText(),rates,messages,stats);
		}
		else if(ctx.internalAction()!=null) {
			Node stat = null;
			if (ctx.statement()!=null) {
				stat = visitStatement(ctx.statement(0));
			}
			return new InternalActionNode(ctx.internalAction().DOUBLE_STRING().getText().substring(1,ctx.internalAction().DOUBLE_STRING().getText().length()-1),ctx.internalAction().role().ID().getText(),stat);
		}
		else if(ctx.ifThenElse()!=null) {
			return visitIfThenElse(ctx.ifThenElse());
		}
		else if(ctx.protocolID()!=null) {
			return new ProtocolIDNode(ctx.protocolID().ID().getText());
		}
		return new CommunicationNode(ctx.role(0).ID().getText(), ctx.role(1).ID().getText(), ctx.message(0).getText().substring(1,ctx.message(0).getText().length()-1), visitStatement(ctx.statement(0)));
	}

	public Node visitIfThenElse(IfThenElseContext ctx) {
		Node elseStatement = null;
		if(ctx.elseStat!=null) {
			elseStatement = visitStatement(ctx.elseStat);
		}
		return new IfThenElseNode(ctx.role().getText(),ctx.cond().getText().substring(1,ctx.cond().getText().length()-1),visitStatement(ctx.thenStat),elseStatement);
	}

}
