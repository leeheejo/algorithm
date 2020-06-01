package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N17136 {

	static int[][] map = new int[10][10];
	static int[] papers = { 5, 5, 5, 5, 5 };
	static boolean flag = false;

	static public class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	static ArrayList<Point> points = new ArrayList<Point>();
	static int min = Integer.MAX_VALUE;
	static int total = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					points.add(new Point(i, j));
					total++;

				}
			}
		}

		if (points.size() == 0) {
			System.out.println(0);
			System.exit(0);
		}

		dfs(0, 0, 0);
		System.out.println(flag ? min : -1);
	}

	public static void dfs(int cnt, int p, int idx) {
		if (min <= cnt) // 이미 나온 min 값보다 크면 리턴
			return;

		if (p == total) {
			min = Math.min(cnt, min);
			flag = true;
			return;
		}
		if (cnt > 25) {
			return;
		}
		if (idx == points.size())
			return;

		Point cur = points.get(idx);
		if (map[cur.x][cur.y] != 0) { // 이미 다른 종이로 0으로 변경한 경우 패스
		for (int k = 4; k >= 0; k--) {
			if (papers[k] == 0)
				continue;
			if (check(cur.x, cur.y, k)) {
				fill(cur.x, cur.y, k, 0);
				papers[k]--;
				dfs(cnt + 1, p + (k + 1) * (k + 1), idx + 1);
				fill(cur.x, cur.y, k, 1);
				papers[k]++;
			}

		}
		}else {
			dfs(cnt, p, idx + 1);

	}

	}

	public static boolean check(int r, int c, int size) {
		if (r + size >= 10 || c + size >= 10)
			return false;
		for (int i = 0; i <= size; i++) {
			for (int j = 0; j <= size; j++) {
				if (map[r + i][c + j] != 1)
					return false;
			}
		}
		return true;
	}

	public static void fill(int x, int y, int size, int state) {
		for (int c = 0; c <= size; c++) {
			for (int r = 0; r <= size; r++) {
				map[x + c][y + r] = state;
			}
		}
	}
}
