package lib;

public class Functions {

	public static String changeIndex(String string, int index, int max) {
		int needChange = string.indexOf("[");
		if(needChange!=-1) {
			String answer = string.substring(string.indexOf("[")+1, string.indexOf("]"));
			if(answer.length()==1) {
				answer = Integer.toString(index);
			}
			else {
				char op = answer.charAt(1);
				char el = answer.charAt(2);
				int val = 0;
				if(op=='+') {
					val = index + Character.getNumericValue(el); 
				}
				else {
					val = index + Character.getNumericValue(el); 
				}
				if(val<=max) {
					answer = Integer.toString(val);
				}
				else {
					answer = Integer.toString(val%max);
				}
			}
			string = string.substring(0,string.indexOf("["))+answer;
		}
		return string;
	}

	public static String retNewIndex(String string, int index, int max) {
		int needChange = string.indexOf("[");
		if(needChange!=-1) {
			String answer = string.substring(string.indexOf("[")+1, string.indexOf("]"));
			if(answer.length()==1) {
				answer = Integer.toString(index);
			}
			else {
				char op = answer.charAt(1);
				char el = answer.charAt(2);
				int val = 0;
				if(op=='+') {
					val = index + Character.getNumericValue(el); 
				}
				else {
					val = index + Character.getNumericValue(el); 
				}
				if(val<=max) {
					answer = Integer.toString(val);
				}
				else {
					answer = Integer.toString(val%max);
				}
			}
			return answer;
		}
		return string;

	}

	public static String returnStringNewIndex(String string, int index, int max) {
		String newVal = retNewIndex(string,index,max);
		string = string.replaceAll("\\[.*?\\]",newVal);
		return string;
	}

}