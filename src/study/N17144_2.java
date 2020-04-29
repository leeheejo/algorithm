
package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N17144_2 {

	public static class Dust {
		int x, y, v;

		public Dust(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}

	static int R, C, T;
	static int[][] map;
	static Queue<Dust> q = new LinkedList<Dust>();
	static int[] ax = { 0, 0, 1, -1 };
	static int[] ay = { 1, -1, 0, 0 };
	static Dust[] af = new Dust[2];

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
				if (map[i][j] > 0)
					q.add(new Dust(i, j, map[i][j]));
				if (map[i][j] == -1) {
					if (cnt == 0) {
						af[0] = new Dust(i, j, -1);
						cnt++;
					} else
						af[1] = new Dust(i, j, -1);

				}

			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			wind();
			addDust();
		}

		System.out.println(calc());

	}

	public static void spread() {
		Queue<Dust> nq = new LinkedList<Dust>();
		int[][] m = new int[R][C];
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

		while (!nq.isEmpty()) {
			Dust d = nq.poll();
			m[d.x][d.y] += d.v;
		}
		for (int i = 0; i < 2; i++) {
			m[af[i].x][af[i].y] = af[i].v;
		}

		map = m.clone();
	}

	public static void wind() {
		Dust af1 = af[0];
		for (int i = af1.x - 1; i >= 1; i--) {
			map[i][0] = map[i - 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}
		for (int i = 0; i < af1.x; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			map[af1.x][i] = map[af1.x][i - 1];
		}
		map[af1.x][1] = 0;

		Dust af2 = af[1];
		for (int i = af2.x + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}
		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}
		for (int i = R - 1; i > af2.x - 1; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}
		for (int i = C - 1; i > 1; i--) {
			map[af2.x][i] = map[af2.x][i - 1];
		}
		map[af2.x][1] = 0;

	}

	public static int calc() {
		int cnt = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					cnt += map[i][j];
			}
		}
		return cnt;
	}

	public static void addDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] > 0)
					q.add(new Dust(i, j, map[i][j]));
			}
		}
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
