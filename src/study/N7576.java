package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N7576 {
	static int M, N; // M 가로 칸, N 세로 칸
	static int[][] arr = new int[1000][10000];
	static int[] ax = { 1, -1, 0, 0 };
	static int[] ay = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		M = sc.nextInt();
		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				arr[j][i] = sc.nextInt();
			}
		}

		BFS();
	}

	static class Point {
		int x;
		int y;
		int day;

		public Point(int x, int y, int day) {
			super();
			this.x = x;
			this.y = y;
			this.day = day;
		}
	}

	public static void BFS() {
		Queue<Point> q = new LinkedList<Point>();
		int day = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[j][i] == 1)
					q.add(new Point(j, i, 0));
			}
		}

		while (!q.isEmpty()) {
			Point temp = q.poll();

			for (int i = 0; i < 4; i++) {
				int nextX = temp.x + ax[i];
				int nextY = temp.y + ay[i];
				if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
					continue;
				if (arr[nextX][nextY] != 0)
					continue;

				q.add(new Point(nextX, nextY, temp.day + 1));
				arr[nextX][nextY] = 1;
				day = temp.day + 1;
			}
		}

		if (checkTomato())
			System.out.print(day);
		else
			System.out.print(-1);

	}

	public static boolean checkTomato() {

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (arr[j][i] == 0)
					return false;
			}
		}
		return true;
	}

}
