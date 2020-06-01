package study.boj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N15685_200509 {

	public static class Point {
		int x, y, d;

		public Point(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
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
			play(x, y, d, g);
		}

		System.out.println(calc());

	}

	public static void play(int x, int y, int d, int g) {
		map[y][x] = true;
		ArrayList<Integer> q = new ArrayList<>();
		q.add(d);
		for (int i = 0; i < g; i++) {
			int t = q.size();
			ArrayList<Integer> nq = new ArrayList<Integer>();
			for (int j = t - 1; j >= 0; j--) {
				int cur = q.get(j);
				int nd = (cur + 1) % 4;
				nq.add(nd);
			}
			for (int j = 0; j < nq.size(); j++) {
				q.add(nq.get(j));
			}
		}

		int nx = x;
		int ny = y;
		map[ny][nx] = true;
		for (int i = 0; i < q.size(); i++) {
			nx += ax[q.get(i)];
			ny += ay[q.get(i)];
			map[ny][nx] = true;
		}
	}

	public static int calc() {
		int sum = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] && map[i + 1][j + 1] && map[i + 1][j] && map[i][j + 1])
					sum++;
			}
		}
		return sum;
	}

	public static void print() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
