package ast;

import java.util.ArrayList;

import lib.Functions;
import lib.Pair;

public class ProgramNode implements Node{

	private ArrayList<Node> modules ;
	private Node preamble ;
	private ArrayList<Pair<Node,ArrayList<Node>>> protocols;
	private int max;

	public ProgramNode(int _max, ArrayList<Node> _modules, Node _preamble, ArrayList<Pair<Node,ArrayList<Node>>> _protocols) {
		max = _max;
		modules = _modules;
		preamble = _preamble;
		protocols = _protocols;
	}

	@Override
	public String toPrint() {
		String toRet = "";
		if(preamble!=null) {
			toRet = toRet + preamble.toPrint();
		}
		for(Node el : modules) {
			toRet = toRet + el.toPrint();
		}
		for(Pair<Node,ArrayList<Node>> el : protocols) {
			for(Node el2 : el.getSecond()) {
				toRet = toRet + el2.toPrint();
			}
		}
		return toRet;
	}

	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}

	@Override
	public String generateCode(String code, int index, int totIndex,  ArrayList<Node> modules, ArrayList<String> labels, String protocolName, int counter) {
		totIndex = max;
		if(preamble!=null) {
			code = preamble.generateCode(code,1,totIndex,this.modules,labels,"",counter);
		}
		for(Node el : this.modules) {
			code = el.generateCode(code,1,totIndex,this.modules,labels,"",counter);
		}
		Functions fun = new Functions();
		for(Pair<Node,ArrayList<Node>> pair : protocols) {
			for(Node el : pair.getSecond()) {
				for(int i=1;i<=totIndex; i++) {
					for(Node mod : this.modules) {
						if(el instanceof BranchNode && mod.toPrint().equals(Functions.changeIndex(((BranchNode) el).getRoleA(),i,totIndex))) {
							((RecNode) pair.getFirst()).setState(((ModuleNode) mod).getState());
						}
					}
					
					code = el.generateCode(code,i,totIndex,this.modules,labels,((RecNode) pair.getFirst()).getName(),counter);
					for(Node mod : this.modules) {
						((RecNode) pair.getFirst()).setGenerated(true);
						if(el instanceof BranchNode && mod.toPrint().equals(Functions.changeIndex(((BranchNode) el).getRoleA(),i,totIndex)) && ((RecNode) pair.getFirst()).getState()==-1) {
							((RecNode) pair.getFirst()).setState(((ModuleNode) mod).getState());

						}
					}

				}
			}
		}
		for(Node el : this.modules) {
			code = ((ModuleNode) el).addStateVariable(code);
		}
		for(Pair<Node,ArrayList<Node>> pair : protocols) {
			code = ((RecNode) pair.getFirst()).changeState(code);
		}
		return code;
	}



}
