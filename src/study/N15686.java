package study;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import study.N1003_constructor.P;

public class N15686 {
	static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	static class Dis {
		int x, y, v;

		public Dis(int x, int y, int v) {
			super();
			this.x = x;
			this.y = y;
			this.v = v;
		}
	}

	static int N, M;
	static int[][] map;
	static int[][] tmp;
	static Point[] sel;
	static boolean[] visited;
	static ArrayList<Point> home;
	static ArrayList<Point> chicken;

	static int[] ax = { 0, 0, -1, 1 };
	static int[] ay = { 1, -1, 0, 0 };

	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		home = new ArrayList<Point>();
		chicken = new ArrayList<Point>();

		sel = new Point[M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == 1) {
					home.add(new Point(i, j));
				} else if (map[i][j] == 2) {
					chicken.add(new Point(i, j));
				}
			}
		}

		visited = new boolean[chicken.size()];
		dfs(0, 0);

		System.out.println(ans);

	}

	public static void dfs(int index, int c) {
		if (index == M) {
			dist();
			return;
		}

		for (int i = c; i < chicken.size(); i++) { // 조합 문제 정확히 다시 풀고 넘어가자!!
			if (visited[i])
				continue;
			
			sel[index] = chicken.get(i);
			visited[i] = true;
			dfs(index + 1, i);
			visited[i] = false;
		}
	}

	public static void dist() {
		int sum = 0;
		for (int i = 0; i < home.size(); i++) {
			Point homeInfo = home.get(i);
			int v = Integer.MAX_VALUE;
			for (int j = 0; j < sel.length; j++) {
				Point chickInfo = sel[j];
				int chickDist = Math.abs(homeInfo.x - chickInfo.x) + Math.abs(homeInfo.y - chickInfo.y);
				v = Math.min(v, chickDist);
			}
			sum += v;
		}
		ans = Math.min(ans, sum);
	}
	
	// 시간초과 ㅠ
//	public static int bfs(int index) {
//		Queue<Dis> q = new LinkedList<Dis>();
//		boolean[][] visited = new boolean[N][N];
//		Point p = home.get(index);
//		q.add(new Dis(p.x, p.y, 0));
//		visited[p.x][p.y] = true;
//		while (!q.isEmpty()) {
//			Dis d = q.poll();
//			for (int k = 0; k < 4; k++) {
//				int nx = d.x + ax[k];
//				int ny = d.y + ay[k];
//				if (nx < 0 || ny < 0 || nx >= N || ny >= N)
//					continue;
//				if (visited[nx][ny])
//					continue;
//
//				if (tmp[nx][ny] == 2)
//					return d.v + 1;
//
//				q.add(new Dis(nx, ny, d.v + 1));
//			}
//
//		}
//		return Integer.MAX_VALUE;
//	}

	public static void draw() {
		tmp = new int[N][N];
		for (int i = 0; i < M; i++) {
			Point p = sel[i];
			tmp[p.x][p.y] = 2;
		}
	}

}
