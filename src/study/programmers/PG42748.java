package study.programmers;

import java.util.Arrays;

public class PG42748 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };

		int[] answer = solution(array, commands);
		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
		}
	}

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];

		for (int i = 0; i < commands.length; i++) {
			int[] k = new int[commands[i][1] - commands[i][0] + 1];
			for (int j = 0; j < k.length; j++) {
				k[j] = array[commands[i][0] + j - 1];
			}
			Arrays.sort(k);
			answer[i] = k[commands[i][2] - 1];
		}
		return answer;
	}
}
