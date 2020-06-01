package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N17136_2 {

	static int[][] map = new int[10][10];
	static ArrayList<Point> points = new ArrayList<Point>();
	static int total = 0;
	static int[] papers = { 5, 5, 5, 5, 5 };
	static int min = Integer.MAX_VALUE;

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
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					points.add(new Point(i, j));
					total++;
				}
			}
		}

		if (points.size() == 0)
			min = 0;
		dfs(0, 0, 0);
		System.out.print(min == Integer.MAX_VALUE ? -1 : min);
	}

	public static void dfs(int index, int cnt, int paper) {// 1인 지점, 현재까지 가린 종이, 사용한 종이 수;
		if (cnt == total) {
			min = Math.min(min, paper);
			return;
		}
		if (paper >= min) {
			return;
		}

		Point cur = points.get(index);
		if (map[cur.x][cur.y] == 1) {
			for (int i = 4; i >= 0; i--) {
				if (papers[i] == 0)
					continue;
				if (!check(cur.x, cur.y, i))
					continue;

				fill(cur.x, cur.y, i, 0);
				papers[i]--;
				dfs(index + 1, cnt + (i + 1) * (i + 1), paper + 1);
				papers[i]++;
				fill(cur.x, cur.y, i, 1);
			}
		} else {
			dfs(index + 1, cnt, paper);
		}
	}

	public static boolean check(int x, int y, int p) {

		for (int i = 0; i <= p; i++) {
			for (int j = 0; j <= p; j++) {
				if (x + i >= 10 || y + j >= 10 || map[x + i][y + j] == 0)
					return false;
			}
		}

		return true;
	}

	public static void fill(int x, int y, int p, int state) {
		for (int i = 0; i <= p; i++) {
			for (int j = 0; j <= p; j++) {
				map[x + i][y + j] = state;
			}
		}
	}
}
