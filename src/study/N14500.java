package study;

import java.util.Scanner;

public class N14500 {

	static int N, M;
	static int[][] map;
	static int[] ax = { 0, 0, 1, -1 };
	static int[] ay = { 1, -1, 0, 0 };
	static boolean[][] visited;
	static int MAX = Integer.MIN_VALUE;

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
				dfs(i, j, map[i][j], 0);
				exception(i, j);
				visited[i][j] = false;
			}
		}
//		visited[0][4] = true;
//		dfs(0, 4, map[0][4], 0);
//		visited[0][4] = false;
		System.out.println(MAX);
	}

	public static void dfs(int x, int y, int sum, int index) {
		if (index == 3) {
//			System.out.println(index + ": " + "(" + x + " " + y + ")" + sum);
			MAX = Math.max(MAX, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nextX = x + ax[i];
			int nextY = y + ay[i];

			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M)
				continue;
			if (visited[nextX][nextY])
				continue;

			visited[nextX][nextY] = true;
			dfs(nextX, nextY, sum + map[nextX][nextY], index + 1);
			visited[nextX][nextY] = false;

		}

	}

	public static void exception(int x, int y) {

		int wing = 4;
		int min = Integer.MAX_VALUE;
		int sum = map[x][y];
		for (int i = 0; i < 4; i++) {
			int nextX = x + ax[i];
			int nextY = y + ay[i];
			if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
				wing--;
				continue;
			}

			if (wing <= 2)
				return;

			sum += map[nextX][nextY];
			min = Math.min(min, map[nextX][nextY]);
		}

		if (wing == 4)
			sum -= min;

		MAX = Math.max(sum, MAX);

	}
}
