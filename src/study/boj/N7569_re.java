package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N7569_re {
	static int[][][] map = new int[100][100][100];
	static int M, N, H;// 가로 세로 높이
	static int[] am = { 1, -1, 0, 0, 0, 0 };
	static int[] an = { 0, 0, 1, -1, 0, 0 };
	static int[] ah = { 0, 0, 0, 0, 1, -1 };
	static Queue<Point> q = new LinkedList<Point>();

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				String s = bf.readLine();
				st = new StringTokenizer(s);
				for (int m = 0; m < M; m++) {
					map[m][n][h] = Integer.parseInt(st.nextToken());
					if (map[m][n][h] == 1)
						q.add(new Point(m, n, h, 0));

				}
			}
		}

		BFS();
	}

	static void BFS() {
		int day = 0;
		while (!q.isEmpty()) {
			Point tmp = q.poll();
			for (int i = 0; i < 6; i++) {
				int nextM = tmp.m + am[i];
				int nextN = tmp.n + an[i];
				int nextH = tmp.h + ah[i];

				if (nextM < 0 || nextN < 0 || nextH < 0 || nextM >= M || nextN >= N || nextH >= H)
					continue;
				if (map[nextM][nextN][nextH] != 0)
					continue;

				map[nextM][nextN][nextH] = 1;
				q.add(new Point(nextM, nextN, nextH, tmp.day + 1));
				day = tmp.day + 1;
			}
		}

		if (!check())
			System.out.println(-1);
		else
			System.out.println(day);

	}

	static boolean check() {
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (map[m][n][h] == 0)
						return false;
				}
			}
		}

		return true;
	}

	static class Point {
		int m;
		int n;
		int h;
		int day;

		public Point(int m, int n, int h, int day) {
			super();
			this.m = m;
			this.n = n;
			this.h = h;
			this.day = day;
		}

	}

}
