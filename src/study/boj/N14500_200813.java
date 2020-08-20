package study.boj;

import java.util.Scanner;

public class N14500_200813 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] ax = { 0, 0, -1, 1 };
	static int[] ay = { 1, -1, 0, 0 };
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];
		visited = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				visited[i][j] = true;
				dfs(0, i, j, 0);
				ans = Math.max(ans, wing(i, j));
				visited[i][j] = false;
			}
		}

		System.out.println(ans);

	}

	public static void dfs(int count, int x, int y, int total) {
		if (count == 4) {
			ans = Math.max(ans, total);

			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + ax[i];
			int ny = y + ay[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny])
				continue;
			visited[nx][ny] = true;
			dfs(count + 1, x + ax[i], y + ay[i], total + map[x][y]);
			visited[nx][ny] = false;
		}

	}

	public static int wing(int x, int y) {
		int minWing = 1001;
		int total = 0;
		int wingCount = 0;
		for (int i = 0; i < 4; i++) {
			int nx = x + ax[i];
			int ny = y + ay[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			minWing = Math.min(map[nx][ny], minWing);
			total += map[nx][ny];
			wingCount++;
		}
		if (wingCount == 4)
			total -= minWing;

		return total + map[x][y];
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j]) {
					System.out.print("1 ");
				} else {
					System.out.print("0 ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}

}
