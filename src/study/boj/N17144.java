package study.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N17144 {
	static int R, C, T;
	static int[][] map;
	static int[] ax = { 0, 0, 1, -1 };
	static int[] ay = { 1, -1, 0, 0 };
	static Dust[] airfresher = new Dust[2];

	public static class Dust {
		int x, y, v;

		public Dust(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();
		map = new int[R][C];

		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1) {
					if (cnt == 0) {
						airfresher[0] = new Dust(i, j, -1);
						cnt++;
					} else {
						airfresher[1] = new Dust(i, j, -1);
					}

				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
//			wind();
			Refresh();

//			System.out.println();
//
//			print();

		}
//		System.out.println();
//
//		print();

		System.out.println(calc());

	}

	public static void spread() {
		Queue<Dust> q = new LinkedList<>();
		Queue<Dust> nq = new LinkedList<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					q.add(new Dust(i, j, map[i][j]));
			}
		}

		while (!q.isEmpty()) {
			Dust d = q.poll();
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nx = d.x + ax[i];
				int ny = d.y + ay[i];
				if (nx < 0 || ny < 0 || nx >= R || ny >= C)
					continue;
				if (map[nx][ny] == -1)
					continue;
				int nv = d.v / 5;
				cnt++;
				nq.add(new Dust(nx, ny, nv));
			}
			int v = d.v - (d.v / 5) * cnt;
			nq.add(new Dust(d.x, d.y, v));
		}

		int[][] tmp = new int[R][C];
		for (int i = 0; i < R; i++) {
			if (map[i][0] == -1)
				tmp[i][0] = -1;
		}

		while (!nq.isEmpty()) {
			Dust d = nq.poll();
			tmp[d.x][d.y] += d.v;
		}

		map = tmp.clone();
	}

	public static void wind() {
		Dust a1 = airfresher[0];
		Dust a2 = airfresher[1];
		int[][] m = new int[R][C];

		for (int i = 1; i < C - 1; i++) {
			m[a1.x][i + 1] = map[a1.x][i];
		}
		m[a1.x - 1][C - 1] = map[a1.x][C - 1];

		for (int i = 0; i < a1.x - 1; i++) {
			m[i][C - 1] = map[i + 1][C - 1];
		}

		m[0][C - 2] = map[0][C - 1];
		for (int i = 0; i < C - 2; i++) {
			m[0][i] = map[0][i + 1];
		}

		for (int i = a1.x - 1; i > 0; i--) {
			m[i][0] = map[i - 1][0];
		}
		m[0][0] = map[0][1];

		// 2

		for (int i = 1; i < C - 1; i++) {
			m[a2.x][i + 1] = map[a2.x][i];
		}
		m[a2.x + 1][C - 1] = map[a2.x][C - 1];

		for (int i = R - 1; i > a2.x; i--) {
			m[i][C - 1] = map[i - 1][C - 1];
		}
		m[R - 1][C - 2] = map[R - 1][C - 1];

		for (int i = 0; i < C - 2; i++) {
			m[R - 1][i] = map[R - 1][i + 1];
		}
		m[R - 1][C - 2] = map[R - 1][C - 1];

		for (int i = a2.x; i < R - 2; i++) {
			m[i][0] = map[i + 1][0];
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (j == 0 || j == C - 1 || i == 0 || i == R - 1 || i == a1.x || i == a2.x)
					continue;
				m[i][j] = map[i][j];
			}
		}
		map = m.clone();

	}

	public static void Refresh() {
		// 공청기1
		// 아래
		for (int i = airfresher[0].x - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}
		// 왼쪽
		for (int j = 0; j < C - 1; j++) {
			map[0][j] = map[0][j + 1];
		}
		// 위쪽
		for (int i = 0; i < airfresher[0].x; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		// 오른쪽
		for (int j = C - 1; j > 0; j--) {
			map[airfresher[0].x][j] = map[airfresher[0].x][j - 1];
		}
		map[airfresher[0].x][1] = 0;

		// 공청기2
		// 위
		for (int i = airfresher[1].x + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}

		// 왼쪽
		for (int j = 0; j < C - 1; j++) {
			map[R - 1][j] = map[R - 1][j + 1];
		}
		// 아래
		for (int i = R - 1; i > airfresher[1].x; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		// 오른쪽
		for (int j = C - 1; j > 0; j--) {
			map[airfresher[1].x][j] = map[airfresher[1].x][j - 1];
		}
		map[airfresher[1].x][1] = 0;

	}

	public static int calc() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == -1)
					continue;
				cnt += map[i][j];
			}

		}
		return cnt;

	}

	public static void print() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
