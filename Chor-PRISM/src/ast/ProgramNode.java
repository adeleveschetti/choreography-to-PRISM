package ast;

import java.util.ArrayList;

import lib.Functions;
import lib.Pair;

public class ProgramNode implements Node{

	private ArrayList<Node> modules ;
	private Node preamble ;
	private ArrayList<Pair<Node,Node>> protocols;
	private int max;

	public ProgramNode(int _max, ArrayList<Node> _modules, Node _preamble, ArrayList<Pair<Node,Node>> _protocols) {
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
		for(Pair<Node,Node> el : protocols) {
			toRet = toRet + el.getSecond().toPrint();
		}
		return toRet;
	}
	
	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		totIndex = max;
		String code = "";
		if(preamble!=null) {
			code = preamble.projection(1,totIndex,this.modules);
		}
		for(Node el : this.modules) {
			code = code + el.projection(1,totIndex,this.modules);
		}
		Functions fun = new Functions();
		for(Pair<Node,Node> pair : protocols) {
			for(int i=1;i<=totIndex; i++) {
				for(Node mod : this.modules) {
					if(mod.toPrint().equals(Functions.changeIndex(((BranchNode) pair.getSecond()).getRoleA(),i,totIndex))) {
						((RecNode) pair.getFirst()).setState(((ModuleNode) mod).getState());
					}
				}
			}
			
		}
		return code;
	}

	@Override
	public String generateCode(String code, int index, int totIndex,  ArrayList<Node> modules, ArrayList<String> labels, String protocolName) {
		totIndex = max;
		if(preamble!=null) {
			code = preamble.generateCode(code,1,totIndex,this.modules,labels,"");
		}
		for(Node el : this.modules) {
			code = el.generateCode(code,1,totIndex,this.modules,labels,"");
		}
		Functions fun = new Functions();
		for(Pair<Node,Node> pair : protocols) {
			for(int i=1;i<=totIndex; i++) {
				for(Node mod : this.modules) {
					if(mod.toPrint().equals(Functions.changeIndex(((BranchNode) pair.getSecond()).getRoleA(),i,totIndex))) {
						((RecNode) pair.getFirst()).setState(((ModuleNode) mod).getState());
					}
				}
				code = pair.getSecond().generateCode(code,i,totIndex,this.modules,labels,((RecNode) pair.getFirst()).getName());
				for(Node mod : this.modules) {
					((RecNode) pair.getFirst()).setGenerated(true);
					if(mod.toPrint().equals(Functions.changeIndex(((BranchNode) pair.getSecond()).getRoleA(),i,totIndex)) && ((RecNode) pair.getFirst()).getState()==-1) {
						((RecNode) pair.getFirst()).setState(((ModuleNode) mod).getState());
					}
				}
			}
		}
		for(Node el : this.modules) {
			code = ((ModuleNode) el).addStateVariable(code);
		}
		for(Pair<Node,Node> pair : protocols) {
			code = ((RecNode) pair.getFirst()).changeState(code);
		}
		return code;
	}



}
