package study;

import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Eval {
	static Stack<Integer> nums;
	static Stack<String> ops;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		nums = new Stack<Integer>();
		ops = new Stack<String>();
		
		System.out.println(eval("3 + 2"));
	}

	public static int eval(String expr) {
		StringTokenizer st = new StringTokenizer(expr, " ");
		while (st.hasMoreElements()) {
			String token = st.nextToken();
			if (token.equalsIgnoreCase("(")) {
				ops.push(token);
			} else if (token.equalsIgnoreCase("+") || token.equalsIgnoreCase("-")) {
				reduce("+-*/");
				ops.push(token);
			} else if (token.equalsIgnoreCase("*") || token.equalsIgnoreCase("/")) {
				reduce("*/");
				ops.push(token);
			} else if (token.equalsIgnoreCase(")")) {
				reduce("+-*/(");
			} else {
				nums.push(Integer.parseInt(token));
			}

		}
		reduce("+-*/");
		return nums.pop();
	}

	public static void reduce(String higher) {
		while (ops.size() > 0) {
			String op = ops.pop();
			if (!higher.contains(op)) {
				return;
			}

			if (op.equalsIgnoreCase("(")) {
				return;
			}

			int b = nums.pop();
			int a = nums.pop();

			if (op.equalsIgnoreCase("+")) {
				nums.push(b + a);
			} else if (op.equalsIgnoreCase("-")) {
				nums.push(b - a);
			} else if (op.equalsIgnoreCase("*")) {
				nums.push(b * a);
			} else if (op.equalsIgnoreCase("/")) {
				nums.push(b / a);
			}
		}
	}

}
