package study.programmers;

import java.util.Stack;

public class PG42585 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String arrangement = "()(((()())(())()))(())";
		int ans = solution(arrangement);
		System.out.println(ans);

	}

	public static int solution(String arrangement) {
		int answer = 0;
		Stack<Character> stack = new Stack<Character>();

		for (int i = 0; i < arrangement.length(); i++) {
			if (arrangement.charAt(i) == '(')
				stack.add(arrangement.charAt(i));
			else {
				stack.pop();
				if (arrangement.charAt(i - 1) == '(')
					answer += stack.size();
				else
					answer++;
			}
		}
		return answer;
	}
}
