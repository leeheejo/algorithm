package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class N17140 {
	public static class Point implements Comparable<Point> {
		int idx, cnt;
		
		public Point(int idx, int cnt) {
			super();
			this.idx = idx;
			this.cnt = cnt;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			if (this.cnt > o.cnt)
				return 1;
			else if (this.cnt < o.cnt)
				return -1;
			else if (this.cnt == o.cnt) {
				if (this.idx > o.idx) {
					return 1;
				}
				return -1;
			}

			return 0;
		}
	}

	static int r, c, k;
	static int[][] v = new int[101][101];
	static int[][] map = new int[101][101];
	static int nr = 3; // 행의 길이..
	static int nc = 3; // 열의 길이..

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		r = sc.nextInt();
		c = sc.nextInt();
		k = sc.nextInt();

		for (int i = 1; i <= 3; i++) {
			for (int j = 1; j <= 3; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		play();
	}

	public static void play() {
		if (map[r][c] == k) {
			System.out.println(0);
			return;
		}

		for (int i = 1; i <= 100; i++) {
			int point = -1;
			if (nr <= nc) {
				point = doR();
			} else {
				point = doC();
			}

			if (point == k) {
				System.out.println(i);
				System.exit(0);
			}
		}

		System.out.println(-1);
		return;
	}

	public static int doR() {
		int len = 0;
		for (int j = 1; j <= nc; j++) {
			int[] count = new int[101];
			List<Point> p = new ArrayList<>();
			for (int i = 1; i <= nr; i++) {
				count[map[j][i]]++;
			}
			for (int i = 1; i <= 100; i++) {
				if (count[i] == 0)
					continue;
				p.add(new Point(i, count[i]));
			}
			Collections.sort(p);

			int t = 2;

			for (int i = 0; i < p.size(); i++) {
				Point tmp = p.get(i);
				v[j][t - 1] = tmp.idx;
				v[j][t] = tmp.cnt;
				t += 2;
			}

			len = Math.max(len, t - 2);
		}

		// nr갱신 필요
		nr = len;
		map = v.clone();
		v = new int[101][101];
		return map[r][c];
	}

	public static int doC() {
		int len = 0;
		for (int j = 1; j <= nr; j++) {
			int[] count = new int[101];
			List<Point> p = new ArrayList<>();
			for (int i = 1; i <= nc; i++) {
				count[map[i][j]]++; // 맵기준.....
			}
			for (int i = 1; i <= 100; i++) {
				if (count[i] == 0)
					continue;
				p.add(new Point(i, count[i]));
			}
			Collections.sort(p);

			int t = 2;

			for (int i = 0; i < p.size(); i++) {
				Point tmp = p.get(i);
				v[t - 1][j] = tmp.idx;
				v[t][j] = tmp.cnt;
				t += 2;
			}
			len = Math.max(len, t - 2);
		}

		nc = len;
		map = v.clone();
		v = new int[101][101];
		return map[r][c];
	}

	public static void print() {
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				System.out.print(v[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
