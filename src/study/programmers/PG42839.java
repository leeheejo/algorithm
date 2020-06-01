package study.programmers;

import java.util.HashMap;

public class PG42839 {
	static int ans = 0;
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	static boolean[] visited;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String numbers = "011";
		System.out.println(solution(numbers));
	}

	public static void dfs(int index, int size, int[] answer, int[] arr, boolean[] visited) {
		if (index == size) {
			String s = "";
			for (int i = 0; i < answer.length; i++) {
				s += answer[i] + "";
			}

			int a = Integer.parseInt(s);
			int cnt = 0;
			for (int i = 1; i <= a; i++) {
				if (a % i == 0)
					cnt++;
				if (cnt > 2)
					return;
			}

			if (cnt == 2) {
				map.put(a, 0);
			}

			return;
		}

		for (int i = 0; i < arr.length; i++) {
			if (visited[i])
				continue;
			if (index == 0 && arr[i] == 0)
				continue;
			answer[index] = arr[i];
			visited[i] = true;
			dfs(index + 1, size, answer, arr, visited);
			visited[i] = false;

		}

	}

	public static int solution(String numbers) {
		int[] arr = new int[numbers.length()];
		for (int i = 0; i < numbers.length(); i++) {
			int a = numbers.charAt(i) - '0';
			arr[i] = a;
		}

		for (int i = 1; i <= numbers.length(); i++) {
			boolean[] visited = new boolean[numbers.length()];
			int[] a = new int[i];
			dfs(0, i, a, arr, visited);

		}

		return map.size();
	}
}
