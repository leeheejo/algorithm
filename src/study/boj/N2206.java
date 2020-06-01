package study.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//�ݷ�
//8 8
//01000100
//01010100
//01010100
//01010100
//01010100
//01010100
//01010100
//00010100
public class N2206 {
	static int N, M;
	static int[][] arr = new int[1001][1001];
	static boolean[][][] visited = new boolean[2][1001][1001];
	static int[] ax = { 0, 0, -1, 1 };
	static int[] ay = { 1, -1, 0, 0 };
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 1; i <= N; i++) {
			String s = bf.readLine();
			for (int j = 1; j <= M; j++) {
				arr[j][i] = s.charAt(j - 1) - '0';
			}
		}
//		visited[1][1] = 1;
		run(1, 1, 1, false);

		if (ans == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(ans);

	}

	static class Point {
		int x;
		int y;
		int count;
		boolean flag;

		public Point(int x, int y, int count, boolean flag) {
			super();
			this.x = x;
			this.y = y;
			this.count = count;
			this.flag = flag;
		}
	}

	static void run(int x, int y, int count, boolean flag) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y, count, flag));
		visited[0][x][y] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();

			if (p.x == M && p.y == N) {
				ans = Math.min(ans, p.count);
				break;
			}

			for (int i = 0; i < 4; i++) {
				int nx = p.x + ax[i];
				int ny = p.y + ay[i];

				if (nx < 1 || ny < 1 || nx > M || ny > N)
					continue;

				if (p.flag == true) {// ���� �̹� �μ� ���

					if (arr[nx][ny] != 0)
						continue;
					if (visited[1][nx][ny] == true)
						continue;

					visited[1][nx][ny] = true;
					q.add(new Point(nx, ny, p.count + 1, p.flag));

				} else { // ���� �� �μ� ���
					if (visited[0][nx][ny] == true)
						continue;

					if (arr[nx][ny] == 1) { // �� ����
						visited[1][nx][ny] = true;
						q.add(new Point(nx, ny, p.count + 1, true));
					} else {
						visited[0][nx][ny] = true;
						q.add(new Point(nx, ny, p.count + 1, p.flag));
					}

				}

			}

		}

	}

}
