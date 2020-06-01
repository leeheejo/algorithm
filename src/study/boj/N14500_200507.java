package study.boj;

import java.util.Scanner;

public class N14500_200507 {

	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int[] ax = { 0, 0, 1, -1 };
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
				dfs(1, i, j, map[i][j]);
				visited[i][j] = false;
				ans = Math.max(ans, cross(i, j));
			}
		}

		System.out.println(ans);
	}

	public static void dfs(int index, int x, int y, int sum) {
		if (index == 4) {
			ans = Math.max(ans, sum);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nx = x + ax[i];
			int ny = y + ay[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			if (visited[nx][ny])
				continue;
			visited[nx][ny] = true;
			dfs(index + 1, nx, ny, sum + map[nx][ny]);
			visited[nx][ny] = false;
		}

	}

	public static int cross(int x, int y) {
		int sum = map[x][y];
		int nx = x;
		int ny = y;
		int wing = 4;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 4; i++) {
			nx = x + ax[i];
			ny = y + ay[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				wing--;
				continue;
			}
			if (visited[nx][ny])
				continue;
			visited[nx][ny] = true;
			sum += map[nx][ny];
			min = Math.min(min, map[nx][ny]);
		}
		for (int i = 0; i < 4; i++) {
			nx = x + ax[i];
			ny = y + ay[i];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;
			visited[nx][ny] = false;
		}

		if (wing == 4) {
			sum -= min;
		} else if (wing < 3) {
			sum = -1;
		}

		return sum;
	}

}
