package study;

import java.util.*;

public class PG42588 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] heights = { 6, 9, 5, 7, 4 };

		int[] ans = solution(heights);
		for (int i = 0; i < ans.length; i++)
			System.out.println(ans[i]);
	}

	public static int[] solution(int[] heights) {
		Stack<Integer> q = new Stack<Integer>();
		for (int i = heights.length - 1; i >= 0; i--) {
			int num = 0;
			for (int j = i - 1; j >= 0; j--) {
				if (heights[i] < heights[j]) {
					num = j + 1;
					break;
				}
			}
			q.add(num);
		}
		int[] answer = new int[q.size()];
		int i = 0;
		while (!q.isEmpty()) {
			int num = q.pop();
			answer[i++] = num;
		}
		return answer;
	}
}
