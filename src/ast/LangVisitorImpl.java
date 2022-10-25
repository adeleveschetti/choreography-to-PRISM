package ast;

import java.util.ArrayList;

import parser.LangBaseVisitor;
import parser.LangParser.*;

public class LangVisitorImpl extends LangBaseVisitor<Node>{

	@Override
	public Node visitProtocol(ProtocolContext ctx) {
		ArrayList<String> roles = new ArrayList<String>();
		for(RoleContext el : ctx.role()) {
			roles.add(el.getText());
		}
		return new ProtocolNode(ctx.protocolID().getText(),visitStatement(ctx.statement()), roles);
	}

	@Override
	public Node visitStatement(StatementContext ctx) {
	
		if(ctx.END()!=null) {
			return null;
		}
		else if(ctx.rate().size()>0) {
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
		return new IfThenElseNode(ctx.role().getText(),ctx.cond().getText().substring(1,ctx.cond().getText().length()-1),visitStatement(ctx.thenStat),visitStatement(ctx.elseStat));
	}
	
}
