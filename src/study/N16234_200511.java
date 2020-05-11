package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N16234_200511 {

	static int N, d1, d2;
	static int[][] map;
	static int[][] temp;
	static int[][] count;
	static int[] ax = { 0, 0, -1, 1 };
	static int[] ay = { 1, -1, 0, 0 };
	static int total = 0;

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
		d1 = sc.nextInt();
		d2 = sc.nextInt();
		map = new int[N][N];
		temp = new int[N][N];
		count = new int[2][N + 1]; // 0: total, 1: count;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int ans = 0;
		while (true) {
			int index = 1;
			temp = new int[N][N];
			count = new int[2][N * N + 1]; // 0: total, 1: count;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (temp[i][j] > 0)
						continue;
					bfs(index, i, j);
					index++;
				}
			}
			move();
			ans++;
			if (index == N * N + 1)
				break;
		}

		System.out.println(ans - 1);
	}

	public static void bfs(int index, int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y));
		temp[x][y] = index;
		count[0][index] += map[x][y];
		count[1][index]++;

		int cnt = 0;
		while (!q.isEmpty()) {
			Point cur = q.poll();
			for (int i = 0; i < 4; i++) {
				int nx = cur.x + ax[i];
				int ny = cur.y + ay[i];

				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;

				if (temp[nx][ny] > 0)
					continue;

				if (Math.abs(map[cur.x][cur.y] - map[nx][ny]) >= d1 && Math.abs(map[cur.x][cur.y] - map[nx][ny]) <= d2) {
					temp[nx][ny] = index;
					count[0][index] += map[nx][ny];
					count[1][index]++;
					q.add(new Point(nx, ny));
				}
			}

		}

	}

	public static void move() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int idx = temp[i][j];
				map[i][j] = count[0][idx] / count[1][idx];
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
