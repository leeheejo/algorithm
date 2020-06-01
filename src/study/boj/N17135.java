package study.boj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class N17135 {
	static int N, M, D;
	static int[][] map, temp;
	static Point[] archer = new Point[3];
	static Point[] attack;
	static int ans = 0;
	static ArrayList<Point> enemy = new ArrayList<Point>();

	static class Point implements Comparable<Point> {
		int x, y, d;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			// TODO Auto-generated method stub
			if (this.d < o.d) {
				return -1;
			} else if (this.d > o.d) {
				return 1;
			} else if (this.d == o.d) {
				return this.y < o.y ? -1 : 1;
			}
			return 0;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();

		map = new int[N + 1][M];
		temp = new int[N + 1][M];
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					enemy.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					archer[index] = new Point(i, j);
					index++;
				}
			}
		}
		dfs(0, 0);
		System.out.println(ans);
	}

	public static void dfs(int index, int c) {
		if (index == 3) {
			play();
			return;
		}

		for (int j = c; j < M; j++) {
			if (map[N][j] == 0) {
				map[N][j] = 2;
				archer[index] = new Point(N, j);
				dfs(index + 1, j + 1);
				map[N][j] = 0;
			}
		}
	}

	public static void play() {
		init();
		int count = 0;
		while (true) {
			if (enemy.size() == 0)
				break;
			attack = new Point[3];
			for (int i = 0; i < attack.length; i++) { // 거리계산
				Point a = archer[i];
				for (int j = 0; j < enemy.size(); j++) {
					Point e = enemy.get(j);
					e.d = Math.abs(a.x - e.x) + Math.abs(a.y - e.y);
				}
				Collections.sort(enemy);
				if (enemy.get(0).d <= D) {
					attack[i] = enemy.get(0);
				}
			}

			for (int i = 0; i < attack.length; i++) { // 공격
				Point p = attack[i];
				if (attack[i] != null && temp[p.x][p.y] == 1) {
					count++;
					temp[p.x][p.y] = 0;
				}
			}

			enemy = new ArrayList<Point>();
			for (int i = N - 2; i >= 0; i--) {
				for (int j = 0; j < M; j++) {
					if (temp[i][j] == 1) {
						temp[i][j] = 0;
						if (i + 1 < N) {
							temp[i + 1][j] = 1;
							enemy.add(new Point(i + 1, j));
						}
					}
				}
			}

			if (enemy.size() == 0) {
				ans = Math.max(count, ans);
				break;
			}
		}
	}

	public static void init() {
		enemy = new ArrayList<Point>();
		temp = new int[N + 1][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				temp[i][j] = map[i][j];
				if (map[i][j] == 1) {
					enemy.add(new Point(i, j));
				}
			}
		}
	}

	public static void print() {
		for (int i = 0; i < N + 1; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(temp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
