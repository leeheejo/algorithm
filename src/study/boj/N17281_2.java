package study.boj;

import java.util.Scanner;

public class N17281_2 {

	static int[] playerOrder = new int[9];
	static boolean[] visited = new boolean[9];
	static int[][] order;
	static int[] roo;

	static int N;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		order = new int[N][9];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 9; j++) {
				order[i][j] = sc.nextInt();
			}
		}

		dfs(0);
		System.out.println(ans);
	}

	public static void dfs(int index) {
		if (index == 9) {
			play();
			return;
		}

		if (index == 3) {
			dfs(index + 1);
		} else {
			for (int i = 1; i < 9; i++) {
				if (visited[i])
					continue;
				playerOrder[index] = i;
				visited[i] = true;
				dfs(index + 1);
				visited[i] = false;
			}
		}
	}

	public static void play() {
		int index = 0;
		int score = 0;
		for (int i = 0; i < N; i++) {
			int out = 0;
			roo = new int[4];
			while (true) {
				int p = playerOrder[index];
				int s = order[i][p];
				if (s == 0) {
					out++;
				} else {
					roo[0]++;
					score += score(s);
				}

				index = (index + 1) % 9;

				if (out == 3)
					break;
			}
		}

		ans = Math.max(ans, score);
	}

	public static int score(int s) {
		int score = 0;
		if (s == 1) {
			score += roo[3];
			for (int i = 2; i >= 0; i--) {
				roo[i + 1] = roo[i];
			}
			roo[0] = 0;
		} else if (s == 2) {
			score += roo[3] + roo[2];
			for (int i = 1; i >= 0; i--) {
				roo[i + 2] = roo[i];
			}
			roo[0] = roo[1] = 0;
		} else if (s == 3) {
			score += roo[3] + roo[2] + roo[1];
			for (int i = 0; i >= 0; i--) {
				roo[i + 3] = roo[i];
			}
			roo[0] = roo[1] = roo[2] = 0;
		} else if (s == 4) {
			score += roo[3] + roo[2] + roo[1] + roo[0];
			roo[0] = roo[1] = roo[2] = roo[3] = 0;
		}

		return score;
	}
}
