package study.programmers;

public class PG42840 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = { 1, 3, 2, 4, 2 };
		System.out.println(solution(a).toString());
		int[] ans = solution(a);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}

	}

	public static int[] solution(int[] answers) {
		int[] answer = {};
		int[] a1 = { 1, 2, 3, 4, 5 };
		int[] a2 = { 2, 1, 2, 3, 2, 4, 2, 5 };
		int[] a3 = { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };
		int[] score = { 0, 0, 0 };

		for (int i = 0; i < answers.length; i++) {
			if (a1[i % 5] == answers[i]) {
				score[0]++;
			}
			if (a2[i % 8] == answers[i]) {
				score[1]++;
			}
			if (a3[i % 10] == answers[i]) {
				score[2]++;
			}
		}

		int max = 0;
		for (int i = 0; i < 3; i++) {
			max = Math.max(score[i], max);
		}

		int cnt = 0;
		for (int i = 0; i < 3; i++) {
			if (max == score[i]) {
				cnt++;
			}
		}

		answer = new int[cnt];
		int k = 0;
		for (int i = 0; i < 3; i++) {
			if (max == score[i]) {
				answer[k++] = i + 1;
			}
		}

		return answer;
	}
}
