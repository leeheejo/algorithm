package study.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N14502 {

	public static int N, M;
	public static int[][] map;
	public static int[][] temp;
	public static int[][] cp;
	public static int answer = Integer.MIN_VALUE;
	public static int[] ax = { 0, 0, 1, -1 };
	public static int[] ay = { 1, -1, 0, 0 };

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
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
		temp = new int[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		cloneMap(temp, map);
		dfs(0, 0, 0);

		System.out.println(answer);

	}

	public static void dfs(int index, int c, int r) {
		if (index == 3) {
			bfs();
			return;
		}

		for (int i = c; i < N; i++) {
			for (int j = r; j < M; j++) {
				if (temp[i][j] == 0) {
					temp[i][j] = 1;
					dfs(index + 1, i, j);
					temp[i][j] = 0;
				}
			}
		}
	}

	public static void bfs() {
		Queue<Point> q = new LinkedList<Point>();
		cp = new int[N][M];
		cloneMap(cp, temp);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cp[i][j] == 2)
					q.add(new Point(i, j));
			}
		}

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + ax[i];
				int ny = p.y + ay[i];
				if (nx < 0 | ny < 0 || nx >= N || ny >= M)
					continue;
				if (cp[nx][ny] != 0)
					continue;

				cp[nx][ny] = 2;
				q.add(new Point(nx, ny));
			}
		}

		int ans = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (cp[i][j] == 0)
					ans++;
			}
		}
		answer = Math.max(answer, ans);

	}

	public static void cloneMap(int[][] copy, int[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				copy[i][j] = origin[i][j];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
