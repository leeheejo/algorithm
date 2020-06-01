package study.boj;

import java.util.Scanner;

public class N17070 {
	static int N;
	static int[][] map;
	static int ans = 0;
	static int[] ax = { 0, 1, 1 };
	static int[] ay = { 1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(0, 1, 1);
		System.out.println(ans);
	}

	public static void dfs(int x, int y, int d) {
		if (x == N - 1 && y == N - 1) {
			ans++;
			return;
		}

		int nx = 0;
		int ny = 0;
		if (d == 1) { // 가로인 경우
			// 1) 가로
			nx = x;
			ny = y + 1;
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (map[nx][ny] != 1) {
					dfs(nx, ny, d);
				}
			}

			// 대각선
			boolean flag = true;
			for (int i = 0; i < 3; i++) {
				nx = x + ax[i];
				ny = y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1)
					flag = false;
			}
			if (flag) {
				dfs(x + 1, y + 1, 3);
			}

		} else if (d == 2) { // 세로인 경우
			nx = x + 1;
			ny = y;
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (map[nx][ny] != 1) {
					dfs(nx, ny, d);
				}
			}
			// 대각선
			boolean flag = true;
			for (int i = 0; i < 3; i++) {
				nx = x + ax[i];
				ny = y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1)
					flag = false;
			}
			if (flag) {
				dfs(x + 1, y + 1, 3);
			}

		} else if (d == 3) { // 대각선인 경우
			// 1) 가로
			nx = x;
			ny = y + 1;
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (map[nx][ny] != 1) {
					dfs(nx, ny, 1);
				}
			}
			// 2) 세로
			nx = x + 1;
			ny = y;
			if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
				if (map[nx][ny] != 1) {
					dfs(nx, ny, 2);
				}
			}
			// 3) 대각선
			boolean flag = true;
			for (int i = 0; i < 3; i++) {
				nx = x + ax[i];
				ny = y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == 1)
					flag = false;
			}
			if (flag) {
				dfs(x + 1, y + 1, 3);
			}
		}

	}
}
