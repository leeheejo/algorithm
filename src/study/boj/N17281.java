package study.boj;

import java.util.Scanner;

public class N17281 {
	static int N;
	static int[][] map;
	static int[] player; // 순서
	static boolean[] visited;
	static int[] location;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][10];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j < 10; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		player = new int[10];
		visited = new boolean[10];
		visited[1] = true;
		player[4] = 1;

		dfs(1);
//		play();
		System.out.println(ans);
	}

	public static void dfs(int index) { // 타자 순서
		if (index > 9) {
			ans = Math.max(ans, play());
			return;
		}
		if (index == 4) { // 4번 선수는 정해져있음.
			dfs(index + 1);
		} else {
			for (int i = 2; i < 10; i++) {
				if (visited[i])
					continue;
				player[index] = i;
				visited[i] = true;
				dfs(index + 1);
				visited[i] = false;
			}

		}

	}

	public static int play() {
		int score = 0;
		int i = 1;

		for (int n = 1; n <= N; n++) {
			int out = 0;
			location = new int[5];
			while (out < 3) {
				if (i == 0) {
					i = (i + 1) % 10;
					continue;
				}

				int p = player[i];
				int res = map[n][p];// 해당 이닝에서 해당 주자의결과
				if (res == 0) {
					out++;
				} else {
					location[0]++;
					score += move(res);
				}

				i = (i + 1) % 10;
			}
		}

		return score;

	}

	public static int move(int res) {
		int score = 0;
		if (res == 1) {
			score += location[3];
			for (int i = 2; i >= 0; i--) {
				location[i + 1] = location[i];
			}
			location[0] = 0;
		} else if (res == 2) {
			score += location[3] + location[2];
			for (int i = 1; i >= 0; i--) {
				location[i + 2] = location[i];
			}

			location[1] = location[0] = 0;

		} else if (res == 3) {

			score += location[3] + location[2] + location[1];
			location[3] = location[0];
			location[2] = location[1] = location[0] = 0;

		} else if (res == 4) {
			score += location[3] + location[2] + location[1] + location[0];
			location[3] = location[2] = location[1] = location[0] = 0;
		}

		return score;
	}

}
