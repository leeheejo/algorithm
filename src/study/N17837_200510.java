package study;

import java.util.ArrayList;
import java.util.Scanner;

import study.N17837_4.Marker;

public class N17837_200510 {
	static int N, K;
	static int[][] map;
	static ArrayList<Integer>[][] arr;
	static Point[] markers;

	static int[] ax = { 0, 0, 0, -1, 1 };
	static int[] ay = { 0, 1, -1, 0, 0 };

	static class Point {
		int x, y, d;

		public Point(int x, int y, int d) {
			super();
			this.x = x;
			this.y = y;
			this.d = d;
		}

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		map = new int[N + 1][N + 1];
		arr = new ArrayList[N + 1][N + 1];
		markers = new Point[K + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
				arr[i][j] = new ArrayList<Integer>();
			}
		}

		for (int i = 1; i <= K; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			markers[i] = new Point(x, y, d);
			arr[x][y].add(i);
		}

		play();
	}

	public static void play() {

		for (int i = 1; i <= 1000; i++) {
			if (move()) {
				System.out.println(i);
				System.exit(0);
			}
		}
		System.out.println(-1);
	}

	public static boolean move() {

		for (int i = 1; i <= K; i++) { // 말을 차례로 이동시킨다.
			Point cur = markers[i];

			int nx = cur.x + ax[cur.d];
			int ny = cur.y + ay[cur.d];

			if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == 2) { // 이동방향이 벽이거나 파란색인 경우
				// 방향 바꾸기
				if (cur.d % 2 == 0) {
					cur.d -= 1;
				} else if (cur.d % 2 == 1) {
					cur.d += 1;
				}
				nx = cur.x + ax[cur.d];
				ny = cur.y + ay[cur.d];
			}

			if (nx < 1 || ny < 1 || nx > N || ny > N || map[nx][ny] == 2) { // 다시 이동하려는 곳도 벽이거나 파란색이면 가만히 있기...
				continue;
			}

			if (map[nx][ny] == 1) {
				update(i, nx, ny, 1);

			} else if (map[nx][ny] == 0) {
				update(i, nx, ny, 0);
			}

			if (arr[nx][ny].size() >= 4)
				return true;
		}

		return false;
	}

	public static void update(int idx, int nx, int ny, int flag) {
		Point marker = markers[idx];
		ArrayList<Integer> now = arr[marker.x][marker.y];
		int bottom = now.indexOf(idx);
		int top = now.size() - 1;

		if (flag == 1) {
			for (int t = top; t >= bottom; t--) {
				int c = now.get(t);
				arr[nx][ny].add(c);
				markers[c].x = nx;
				markers[c].y = ny;
			}
		} else if (flag == 0) {
			for (int t = bottom; t <= top; t++) {
				int c = now.get(t);
				arr[nx][ny].add(c);
				markers[c].x = nx;
				markers[c].y = ny;
			}
		}
		remove(now, bottom, top);

	}

	public static void remove(ArrayList<Integer> now, int bottom, int top) {
		for (int i = top; i >= bottom; i--) {
			now.remove(i);
		}
	}
}
