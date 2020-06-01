package study.boj;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N16234 {
	static int N, L, R;
	static int[][] map;
	static int[][] tmp;
	static int[] ax = { 0, 0, 1, -1 };
	static int[] ay = { 1, -1, 0, 0 };
	static int cnt;
	static int answer = 0;

	public static class Point {
		int x, y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}

	}

	public static class Union {
		int country, total;

		public Union(int country, int total) {
			super();
			this.country = country;
			this.total = total;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		R = sc.nextInt();
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		while (true) {
			if (!bfs())
				break;
			answer++;
		}

		System.out.println(answer);
	}

	public static boolean bfs() {
		tmp = new int[N][N];
		cnt = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (tmp[i][j] > 0)
					continue;
				tmp[i][j] = cnt;
				Queue<Point> q = new LinkedList<Point>();
				q.add(new Point(i, j));
				while (!q.isEmpty()) {
					Point p = q.poll();
					for (int k = 0; k < 4; k++) {
						int nx = p.x + ax[k];
						int ny = p.y + ay[k];
						if (nx < 0 || ny < 0 || nx >= N || ny >= N)
							continue;
						if (tmp[nx][ny] > 0)
							continue;
						if (Math.abs(map[p.x][p.y] - map[nx][ny]) <= R && Math.abs(map[p.x][p.y] - map[nx][ny]) >= L) {
							tmp[nx][ny] = cnt;
							q.add(new Point(nx, ny));
						}
					}

				}
				cnt++;
			}
		}

		if (cnt == N * N + 1)
			return false;

		calc();

		return true;
	}

	public static void calc() {
		Union[] uni = new Union[cnt];
		for (int i = 0; i < cnt; i++)
			uni[i] = new Union(0, 0);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				uni[tmp[i][j]].total += map[i][j];
				uni[tmp[i][j]].country++;
			}
		}  
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = uni[tmp[i][j]].total / uni[tmp[i][j]].country;
			}
		}

	}

	public static void print() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}

	}
}
