package study.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N14503 {

	public static class Point {
		int x, y, d;

		public Point(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

	}

	static int[][] map;
	static boolean[][] visited;
	static Queue<Point> q = new LinkedList<Point>();
	static int N, M;
	static int[] ax = { -1, 0, 1, 0 };
	static int[] ay = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();

		map = new int[N][M];
		visited = new boolean[N][M];

		int x = sc.nextInt();
		int y = sc.nextInt();
		int d = sc.nextInt();

		q.add(new Point(x, y, d));
		visited[x][y] = true;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		System.out.println(bfs());

	}

	public static int bfs() {
		int ans = 1;

		while (!q.isEmpty()) {
			Point p = q.poll();
			int nd = p.d;
			int nx = p.x;
			int ny = p.y;
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				nd = (nd + 3) % 4;
				nx = p.x + ax[nd];
				ny = p.y + ay[nd];
				if (!visited[nx][ny] && map[nx][ny] == 0) {
					visited[nx][ny] = true;
					flag = true;
					ans++;
					q.add(new Point(nx, ny, nd));
					break;
				}
			}
			if (!flag) { // 후진
				nx = p.x + ax[(p.d + 2) % 4];
				ny = p.y + ay[(p.d + 2) % 4];
				if (map[nx][ny] == 1)
					return ans;
				else
					q.add(new Point(nx, ny, p.d));
			}
		}

		return ans;
	}

}
