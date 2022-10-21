package ast;

import java.util.ArrayList;

import parser.LangBaseVisitor;
import parser.LangParser.*;

public class LangVisitorImpl extends LangBaseVisitor<Node>{

	@Override
	public Node visitProtocol(ProtocolContext ctx) {
		return new ProtocolNode(ctx.protocolID().getText(),visitStatement(ctx.statement()));
	}

	@Override
	public Node visitStatement(StatementContext ctx) {
	
		if(ctx.END()!=null) {
			return null;
		}
		else if(ctx.rate().size()>0) {
			ArrayList<String> messages = new ArrayList<String>();
			for(MessageContext el : ctx.message()) {
				messages.add(el.getText());
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
			return new InternalActionNode(ctx.internalAction().DOUBLE_STRING().getText(),ctx.internalAction().role().ID().getText(),stat);
		}
		else if(ctx.ifThenElse()!=null) {
			return visitIfThenElse(ctx.ifThenElse());
		}
		else if(ctx.protocolID()!=null) {
			return new ProtocolIDNode(ctx.protocolID().ID().getText());
		}
		return new CommunicationNode(ctx.role(0).ID().getText(), ctx.role(1).ID().getText(), ctx.message(0).getText(), visitStatement(ctx.statement(0)));
	}
	
	public Node visitIfThenElse(IfThenElseContext ctx) {
		return new IfThenElseNode(ctx.cond().getText(),visitStatement(ctx.thenStat),visitStatement(ctx.elseStat));
	}
	
}
