package study.programmers;

import java.util.Stack;

public class PG42584 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] prices = { 1, 2, 3, 2, 3 };
		int[] answer = solution(prices);

		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i]);
		}

	}

	public static int[] solution(int[] prices) {
		int[] answer = new int[prices.length];
		int index = 0;
		int j;
		for (int i = 0; i < prices.length - 1; i++) {
			for (j = i + 1; j < prices.length - 1; j++) {
				if (prices[j] < prices[i])
					break;
			}
			answer[index++] = j - i;
		}

		answer[index] = 0;
		return answer;
	}

}
