package study.programmers;

import java.util.Collections;
import java.util.LinkedList;

public class PG42628 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] operations = { "I 7", "I 5", "I -5", "D -1" };
		int[] ans = solution(operations);
		for (int i = 0; i < 2; i++) {
			System.out.println(ans[i]);
		}
	}

	public static int[] solution(String[] operations) {
		int[] answer = { 0, 0 };
		LinkedList<Integer> up = new LinkedList<Integer>();

		for (int i = 0; i < operations.length; i++) {
			String[] op = operations[i].split(" ");
			if (op[0].equals("I")) {
				up.add(Integer.parseInt(op[1]));
				Collections.sort(up);
			} else if (op[0].equals("D")) {
				if (up.size() != 0) {
					if (op[1].equals("1")) {
						up.remove(up.size() - 1);
					} else if (op[1].equals("-1")) {
						up.remove(0);
					}
				}
			}
		}

		if (up.size() != 0) {
			answer[0] = up.get(up.size() - 1);
			answer[1] = up.get(0);
		}

		return answer;
	}
}
