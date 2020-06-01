package study.programmers;

import java.util.Arrays;

public class PG42748_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = { 1, 5, 2, 6, 3, 7, 4 };
		int[][] commands = { { 2, 5, 3 }, { 4, 4, 1 }, { 1, 7, 3 } };
		int[] ans = solution(array, commands);
		for (int i = 0; i < ans.length; i++) {
			System.out.println(ans[i]);
		}
	}

	public static int[] solution(int[] array, int[][] commands) {
		int[] answer = new int[commands.length];
		int idxForAnswer = 0;
		for (int i = 0; i < commands.length; i++) {
			int begin = commands[i][0] - 1;
			int end = commands[i][1] - 1;
			int target = commands[i][2];
			int[] a = new int[end - begin + 1];
			int index = 0;
			for (int j = begin; j <= end; j++) {
				a[index++] = array[j];
			}
			Arrays.sort(a);
			answer[idxForAnswer++] = a[target - 1];
		}
		return answer;
	}
}
