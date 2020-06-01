package study.test;

import java.util.Scanner;
import java.util.Stack;

public class Test3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		getMin(s);

		System.out.println(getMin(s));

	}

	public static int getMin(String s) {
		int ans = 0;
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == '(') {
				stack.push(c);
			} else {
				if (stack.isEmpty()) {
					ans++;
				} else {
					stack.pop();
				}
			}
		}
		
		if(!stack.isEmpty()) {
			ans += stack.size();
		}

		return ans;
	}

}
