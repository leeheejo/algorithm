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

	}

	static int N, M;
	static int[][] map;
	static ArrayList<Virus> virus;
	static boolean[][] visited;
	static Virus[] active;

	static int[] ar = { 1, -1, 0, 0 };
	static int[] ac = { 0, 0, 1, -1 };

	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		visited = new boolean[N][N];
		active = new Virus[M];
		virus = new ArrayList<Virus>();
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				map[c][r] = sc.nextInt();
				if (map[c][r] == 2) {
					virus.add(new Virus(c, r));
				}
			}
		}
//		dfs(0);
		System.out.println(spread());

		System.out.println(ans);
	}

	public static void dfs(int index) {
		if (index == M) {
			int a = spread();
			ans = Math.min(a, ans);
			return;
		}

		for (int i = index; i < virus.size(); i++) {
			active[index] = virus.get(i);
			dfs(index + 1);
		}
	}

	public static int spread() {
		int count = 0;
		for (int i = 0; i < N; i++)
			Arrays.fill(visited[i], false);

		Queue<Virus> q = new LinkedList<Virus>();
//		for (int i = 0; i < M; i++) {
//			q.add(active[i]);
//		}

		q.add(new Virus(0, 0));
		q.add(new Virus(1, 6));
		q.add(new Virus(4, 3));

		while (!q.isEmpty()) {
			Virus v = q.poll();
			visited[v.x][v.y] = true;

			for (int i = 0; i < 4; i++) {
				int nx = v.x + ac[i];
				int ny = v.y + ar[i];
				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] == 1)
					continue;

				print();
				map[nx][ny] = 2;
				q.add(new Virus(nx, ny, v.count + 1));
				count = v.count + 1;

			}
		}
		System.out.println(count);

		return count;
	}

	static void print() {
		System.out.println();
		System.out.println();
		for (int c = 0; c < N; c++) {
			for (int r = 0; r < N; r++) {
				System.out.print(map[c][r] + " ");
			}
			System.out.println();
		}
	}
}
