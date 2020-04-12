package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class N7569 {

	static int M, N, H;
	static int[][][] arr;
	static int[] ax = { 1, -1, 0, 0, 0, 0 };
	static int[] ay = { 0, 0, 1, -1, 0, 0 };
	static int[] az = { 0, 0, 0, 0, 1, -1 };
	static int day = 0;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		arr = new int[H][N][M];

		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(bf.readLine());
				for (int m = 0; m < M; m++) {
					arr[h][n][m] = Integer.parseInt(st.nextToken());
				}
			}
		}

		run();
	}

	static class Point {
		int x;
		int y;
		int z;
		int day;

		public Point(int x, int y, int z, int day) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.day = day;
		}
	}

	static void run() {
		Queue<Point> q = new LinkedList<Point>();
		int day = 0;
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (arr[h][n][m] == 1)
						q.add(new Point(m, n, h, 0));
				}
			}
		}

		while (!q.isEmpty()) {
			Point p = q.poll();
			day = p.day;

			for (int i = 0; i < 6; i++) {
				int nextX = p.x + ax[i];
				int nextY = p.y + ay[i];
				int nextZ = p.z + az[i];

				if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N || nextZ < 0 || nextZ >= H)
					continue;
				if (arr[nextZ][nextY][nextX] != 0)
					continue;

				arr[nextZ][nextY][nextX] = 1;

				// 틀렸던 이유!! 여기서 객체 생성해서 큐에 넣을 때 p.day++ 이렇게 하면 안됨!
				q.add(new Point(nextX, nextY, nextZ, p.day + 1));

			}
		}

		if (check())
			System.out.println(day);
		else
			System.out.println(-1);
	}

	static boolean check() {
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				for (int m = 0; m < M; m++) {
					if (arr[h][n][m] == 0)
						return false;
				}
			}
		}
		return true;
	}
}
