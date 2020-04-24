package study;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N17142 {
	public static class Virus {
		int x, y;
		boolean activate;
		int count;

		public Virus(int x, int y) {
			super();
			this.x = x;
			this.y = y;
			this.activate = false;
			this.count = 0;
		}

		public Virus(int x, int y, int count) {
			super();
			this.x = x;
			this.y = y;
			this.activate = false;
			this.count = count;
		}

		@Override
		public String toString() {
			return "Virus [x=" + x + ", y=" + y + "]";
		}

	}

	static int N, M;
	static int[][] map;
	static ArrayList<Virus> virus;
	static Virus[] active;

	static int[] ar = { 1, -1, 0, 0 };
	static int[] ac = { 0, 0, 1, -1 };
	static int ZERO = 0;

	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		active = new Virus[M];
		virus = new ArrayList<Virus>();
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				map[c][r] = sc.nextInt();
				if (map[c][r] == 2) {
					virus.add(new Virus(c, r));
				}
				if (map[c][r] == 0) {
					ZERO++;
				}
			}
		}
		if (ZERO == 0) {
			System.out.println(0);
			System.exit(0);
		}
		dfs(0, 0);
		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	public static void dfs(int index, int c) {
		if (index == M) {
			int a = spread();
//			System.out.println(a + "s");
//			for (int i = 0; i < active.length; i++) {
//				System.out.print(active[i].toString() + " ");
//			}
//			System.out.println();
			ans = Math.min(a, ans);
			return;
		}

		for (int i = c; i < virus.size(); i++) {
			active[index] = virus.get(i);
			dfs(index + 1, i+1);
		}
	}

	public static int spread() {
		int z = ZERO;
		int count = 0;
		int[][] visited = new int[N][N];

		Queue<Virus> q = new LinkedList<Virus>();
		for (int i = 0; i < M; i++) {
			q.add(active[i]);
			visited[active[i].x][active[i].y] = 1;
		}

		while (!q.isEmpty()) {
			Virus v = q.poll();
			count = v.count;

			for (int i = 0; i < 4; i++) {
				int nx = v.x + ac[i];
				int ny = v.y + ar[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (visited[nx][ny] != 0)
					continue;
				if (map[nx][ny] == 1)
					continue;

				visited[nx][ny] = v.count + 1;

				q.add(new Virus(nx, ny, v.count + 1));
				if (map[nx][ny] == 0) {
					z--;
					if (z == 0) {
						return v.count + 1;
					}
				}
			}
		}

		return Integer.MAX_VALUE;
	}
//
//	static void print() {
//		System.out.println();
//		System.out.println();
//		for (int c = 0; c < N; c++) {
//			for (int r = 0; r < N; r++) {
//				System.out.print(visited[c][r] + " ");
//			}
//			System.out.println();
//		}
//	}
}
