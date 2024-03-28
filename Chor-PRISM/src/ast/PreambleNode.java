package ast;

import lib.ListPair;
import lib.Matrix;
import lib.Pair;
import lib.Triplet;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class PreambleNode implements Node {

	private ArrayList<String> vars ;
	private boolean isCtmc ;
	
	public PreambleNode(ArrayList<String> _vars, boolean _ctmc) {
		vars = new ArrayList<String>();
		for(String el : _vars) {
			vars.add(el);
		}
		isCtmc = _ctmc;
	}

	public ArrayList<String> getVars(){
		return vars;
	}

	public boolean isCtmc() {
		return isCtmc;
	}
	
	@Override
	public String toPrint() {
		return null;
	}
	


	@Override
	public String generateCode(ArrayList<Node> mods, int index, int maxIndex, boolean isCtmc, ArrayList<String> labels, String prot) {
		String code = "";
		for(String el : vars) {
			code = code + el.substring(1,el.length()-1) + "\n";
		}
		return code;
	}

	@Override
	public ArrayList<String> getRoles() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pair<ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>>,ListPair> generateStates(ArrayList<Node> mods, ListPair states, ArrayList<Pair<String,ArrayList<Pair<String,Integer>>>> recValues, ArrayList<String> moduleNames, ArrayList<Pair<String,ArrayList<Node>>> stms, ArrayList<Pair<String,Integer>> lastUpdate, ArrayList<Pair<String,String>> consts){
		ArrayList<String> rate = new ArrayList<>();
		ArrayList<String> rateValue = new ArrayList<>();
		consts = new ArrayList<>();
		for(String var : vars) {
			if (var.contains("=")) { // TODO : fix that
				String[] tmp = var.substring(0, var.indexOf("=")).split(" ");
				for (String t : tmp) {
					if (!t.contains("const") && !t.contains("double") && !t.contains("int") && !t.contains("bool")) {
						rate.add(t);
						rateValue.add(var.substring(var.indexOf("= ") + 2, var.length() - 2));
					}
				}
			}
		}


		for (Triplet<String, ArrayList<Pair<String, Integer>>, ArrayList<Pair<String, Integer>>> el : states.getList()) {
			if(el.getFirst().length()>1) {
				String rate1 = el.getFirst().substring(0, el.getFirst().indexOf("*"));
				String rate2 = el.getFirst().substring(el.getFirst().indexOf("*") + 1, el.getFirst().length());

				String val1 = "";
				String val2 = "";
				for (int i =0; i<rate.size(); i++) {
					if (rate1.equals(rate.get(i))) {
						val1 = rateValue.get(i);
					}
					if (rate2.equals(rate.get(i))) {
						val2 = rateValue.get(i);
					}
				}
				String decimalPattern = "([0-9]*)\\.([0-9]*)";
				if(rate1.equals("1")){
					val1 = "1";
				}
				else if(Pattern.matches(decimalPattern, rate1)){
					val1 = rate1;
				}
				if(rate2.equals("1")){
					val2 = "1";
				}
				else if(Pattern.matches(decimalPattern, rate2)){
					val2 = rate2;
				}
				String res = Double.toString(Double.parseDouble(val1)*Double.parseDouble(val2));
				el.setFirst(res);
			}

		}

		return new Pair(recValues,states);
	}

	@Override
	public Matrix generateMarkovChain(ArrayList<Node> mods) {
		return null;
	}

}
