package study.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N14502_200807 {

	static int N, M;
	static int ans = Integer.MIN_VALUE;
	static int[][] map, cp;

	public static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		dfs(0, 0);
		System.out.println(ans);

	}

	public static void dfs(int cnt, int n) {
		if (cnt == 3) {
			copy();
//			print(map);
			bfs();
			ans = Math.max(ans, calc());
			return;
		}
		for (int i = n; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0)
					continue;
				map[i][j] = 1;
				dfs(cnt + 1, i);
				map[i][j] = 0;
			}
		}

	}

	public static void bfs() {
		int[] ax = { -1, 1, 0, 0 };
		int[] ay = { 0, 0, -1, 1 };
		Queue<Point> q = new LinkedList<Point>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cp[i][j] == 2) {
					q.add(new Point(i, j));
				}
			}
		}

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + ax[i];
				int ny = p.y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (cp[nx][ny] == 0) {
					q.add(new Point(nx, ny));
					cp[nx][ny] = 2;
				}
			}
		}

	}

	public static int calc() {
		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cp[i][j] == 0)
					answer++;
			}
		}
		return answer;
	}

	public static void copy() {
		cp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cp[i][j] = map[i][j];
			}
		}
	}

	public static void print(int[][] cp) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(cp[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}

}
