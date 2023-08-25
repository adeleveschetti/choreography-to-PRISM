package ast;

import java.util.ArrayList;

public class MessageNode implements Node {

	Node actions;
	ArrayList<Node> loop;
	String message;
	boolean beforeAction;

	public MessageNode(Node _actions, ArrayList<Node> _loop, String _message, boolean _beforeAction) {
		actions = _actions;
		loop = _loop;
		message = _message;
		beforeAction = _beforeAction;
	}

	@Override
	public String toPrint() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String projection(int index, int totIndex, ArrayList<Node> modules) {
		return null;
	}
	
	@Override
	public String generateCode(String code, int index, int totIndex, ArrayList<Node> modules, ArrayList<String> labels, String protocolName) {
		if(loop==null) {
			return actions.generateCode("",index,totIndex,modules,labels,protocolName);
		}
		else if(actions==null && loop.size()>1) {

			String ret = "";
			for(int i=0; i<loop.size(); i++) {
				ret = ret + loop.get(i).generateCode("",index,totIndex,modules,labels,protocolName);
				if(i!=loop.size()-1) {
					ret = ret + "&&";
				}
			}
			return ret;
		}
		String ret = "";
		String loopMessage = loop.get(0).generateCode("",index,totIndex,modules,labels,protocolName);
		if(beforeAction) {
			ret = message + "&&" + loopMessage;
		}
		else {
			ret = loopMessage + "&&" + message;
		}
		return ret;
	}

}
