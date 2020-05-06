package study;

import java.util.ArrayList;
import java.util.Scanner;

public class N15685_2 {
	public static class Point {
		int x, y, dir;

		public Point(int x, int y, int dir) {
			super();
			this.x = x;
			this.y = y;
			this.dir = dir;
		}

	}

	static boolean[][] map = new boolean[101][101];
	static int N;
	static int[] ax = { 1, 0, -1, 0 };
	static int[] ay = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();
			move(new Point(x, y, d), g);
			map[y][x] = true;
		}

		System.out.println(calc());

	}

	public static void move(Point p, int g) {
		ArrayList<Integer> q = new ArrayList<Integer>();
		ArrayList<Integer> temp = new ArrayList<Integer>();
		q.add(p.dir);
		for (int gen = 1; gen <= g; gen++) {

			for (int i = 0; i < q.size(); i++) {
				temp.add(q.get(i));
			}
			for (int i = q.size() - 1; i >= 0; i--) {
				int nd = (q.get(i) + 1) % 4;
				temp.add(nd);
			}

			q.clear();
			for (int i = 0; i < temp.size(); i++) {
				q.add(temp.get(i));
			}
		}
		int nx = p.x;
		int ny = p.y;
		for (int i = 0; i < q.size(); i++) {
			int dir = q.get(i);
			nx += ax[dir];
			ny += ay[dir];
			map[ny][nx] = true;
		}
		temp.clear();
	}

	public static int calc() {
		int ans = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == true && map[i + 1][j] == true && map[i][j + 1] == true && map[i + 1][j + 1] == true)
					ans++;
			}
		}
		return ans;
	}

	public static void print() {

		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}

}
