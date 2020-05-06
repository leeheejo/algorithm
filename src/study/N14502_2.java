package study;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N14502_2 {
	public static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static int N, M;
	static int[][] map;
	static int[][] copy;
	static int ans = Integer.MIN_VALUE;
	static ArrayList<Point> virus = new ArrayList<Point>();
	static int ZERO = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 0)
					ZERO++;
				if (map[i][j] == 2)
					virus.add(new Point(i, j));
			}
		}
		dfs(0, 0);

		System.out.println(ans);
	}

	public static void dfs(int index, int c) {
		if (index == 3) {
			bfs();
			return;
		}
		for (int i = c; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					map[i][j] = 1;
					dfs(index + 1, i);
					map[i][j] = 0;
				}

			}
		}

	}

	public static void bfs() {
		int total = ZERO - 3;
		int[][] temp = new int[N][M];
		int[] ax = { 0, 0, 1, -1 };
		int[] ay = { 1, -1, 0, 0 };
		cloneMap(temp, map);

		Queue<Point> q = new LinkedList<Point>();
		for (int i = 0; i < virus.size(); i++) {
			q.add(new Point(virus.get(i).x, virus.get(i).y));
		}

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = p.x + ax[i];
				int ny = p.y + ay[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= M)
					continue;
				if (temp[nx][ny] == 0) {
					temp[nx][ny] = 2;
					q.add(new Point(nx, ny));
					total--;
				}
			}
		}

		ans = Math.max(total, ans);
	}

	public static void cloneMap(int[][] dist, int[][] origin) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				dist[i][j] = origin[i][j];
			}
		}
	}

	public static void print() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(copy[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}
