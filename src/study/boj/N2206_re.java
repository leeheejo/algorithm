package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N2206_re {
	static int N, M;
	static int[][] map = new int[1001][1001];
	static boolean[][][] visited = new boolean[2][1001][1001];
	static int[] am = { 0, 0, 1, -1 };
	static int[] an = { 1, -1, 0, 0 };
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int n = 1; n <= N; n++) {
			String s = bf.readLine();
			for (int m = 1; m <= M; m++) {
				map[m][n] = s.charAt(m - 1) - '0';
			}
		}

		bfs();
		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);
	}

	static class Point {
		int m;
		int n;
		int count;
		boolean flag; // 벽 부순건지 true 안부순건지 false

		public int getM() {
			return m;
		}

		public void setM(int m) {
			this.m = m;
		}

		public int getN() {
			return n;
		}

		public void setN(int n) {
			this.n = n;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

		public Point(int m, int n, int count, boolean flag) {
			super();
			this.m = m;
			this.n = n;
			this.count = count;
			this.flag = flag;
		}

		public Point() {
			super();
		}
	}

	public static void bfs() {

		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(1, 1, 1, false));
		visited[0][1][1] = true;

		while (!q.isEmpty()) {
			Point tmp = q.poll();

			if (tmp.m == M && tmp.n == N) {
				ans = Math.min(ans, tmp.count);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nextM = tmp.m + am[i];
				int nextN = tmp.n + an[i];

				if (nextM < 1 || nextN < 1 || nextM > M || nextN > N)
					continue;

				if (tmp.flag == false) { // 벽을 안부순 경우
					if (visited[0][nextM][nextN] == true)
						continue;

					if (map[nextM][nextN] == 0) { // 벽X
						visited[0][nextM][nextN] = true;
						q.add(new Point(nextM, nextN, tmp.count + 1, tmp.flag));
					} else {
						visited[1][nextM][nextN] = true;
						q.add(new Point(nextM, nextN, tmp.count + 1, true));
					}
				} else { // 벽을 부순 경우
					if (map[nextM][nextN] == 1)
						continue;
					
					if (visited[1][nextM][nextN] == true)
						continue;


					visited[1][nextM][nextN] = true;
					q.add(new Point(nextM, nextN, tmp.count + 1, tmp.flag));
				}
			}
		}

	}
}
