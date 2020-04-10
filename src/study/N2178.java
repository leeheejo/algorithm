package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N2178 {
	static int[][] arr = new int[101][101];
	static boolean[][] visited = new boolean[101][101];
	static int[] ax = { 1, -1, 0, 0 };
	static int[] ay = { 0, 0, 1, -1 };
	static int N, L;

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		L = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		for (int i = 0; i < L; i++) {
			String s = bf.readLine();
			for (int j = 0; j < N; j++) {
				arr[j + 1][i + 1] = s.charAt(j) - '0';
			}
		}
		BFS(1, 1);

		System.out.println(arr[N][L]);
	}

	public static class Point

	{
		int x;
		int y;

		public Point(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	public static void BFS(int x, int y) {
		Queue<Point> q = new LinkedList<Point>();
		q.add(new Point(x, y));
		visited[x][y] = true;

		while (!q.isEmpty()) {
			Point p = q.poll();
			for (int i = 0; i < 4; i++) {
				int nextX = p.x + ax[i];
				int nextY = p.y + ay[i];

				if (nextX < 1 || nextX > N || nextY < 1 || nextY > L)
					continue;
				if (arr[nextX][nextY] == 0)
					continue;
				if (visited[nextX][nextY])
					continue;

				arr[nextX][nextY] = arr[p.x][p.y] + 1;

				// 메모리 초과남
				// 이거를 while 시작할때 true로 변경하지 않고 큐에 넣기 전에 변경하는게 중요한듯?
				// 정확한 이유는 모르겠음...
				visited[nextX][nextY] = true;
				q.add(new Point(nextX, nextY));

			}

		}

	}

}
