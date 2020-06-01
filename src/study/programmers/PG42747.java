package study.programmers;

import java.util.Arrays;

public class PG42747 {

	public static void main(String[] args) {
		int[] citations = { 2, 2, 2, 2, 2 };
		System.out.println(solution(citations));
	}

	public static int solution(int[] citations) {
		int answer = 0;
		Arrays.sort(citations);
		for (int h = 0; h <= citations[citations.length - 1]; h++) {
			int min = 0;
			int max = 0;
			for (int i = 0; i < citations.length; i++) {
				if (citations[i] >= h)
					max++;
				else
					min++;
			}
			if (h <= max) {
				answer = h;
			}

		}
		return answer;
	}
}
