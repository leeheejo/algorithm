package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N17837_3 {
	public static class Marker {
		int idx, x, y, d;

		public Marker(int idx, int x, int y, int d) {
			super();
			this.idx = idx;
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}

	static int N, K;
	static int[][] colors;
	static Marker[] markers;
	static ArrayList<Integer>[][] map;
	static final int BLUE = 2;
	static final int RED = 1;
	static final int WHITE = 0;
	static int[] ax = { 0, 1, -1, 0, 0 };
	static int[] ay = { 0, 0, 0, -1, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		colors = new int[N + 1][N + 1];
		markers = new Marker[K + 1];
		map = new ArrayList[N + 1][N + 1];

		for (int c = 1; c <= N; c++) {
			for (int r = 1; r <= N; r++) {
				colors[c][r] = sc.nextInt();
				map[c][r] = new ArrayList<Integer>();
			}
		}

		for (int i = 1; i <= K; i++) {
			int y = sc.nextInt();
			int x = sc.nextInt();
			int d = sc.nextInt();
			markers[i] = new Marker(i, x, y, d);
			map[y][x].add(i);
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
		for (int i = 1; i <= K; i++) {
			Marker now = markers[i];
			int nx = now.x + ax[now.d];
			int ny = now.y + ay[now.d];

			if (nx < 1 || ny < 1 || nx > N || ny > N || colors[ny][nx] == BLUE) {
				if (now.d % 2 == 1) {
					now.d = now.d + 1;
				} else if (now.d % 2 == 0) {
					now.d = now.d - 1;
				}
				nx = now.x + ax[now.d];
				ny = now.y + ay[now.d];
			}

			if (nx > 0 && ny > 0 && nx <= N && ny <= N && colors[ny][nx] != BLUE) {
				if (colors[ny][nx] == WHITE) {
					setMarkers(now, new int[] { nx, ny }, WHITE);
				} else if (colors[ny][nx] == RED) {
					setMarkers(now, new int[] { nx, ny }, RED);
				}

				if (map[ny][nx].size() >= 4)
					return true;
			}
		}

		return false;
	}

	public static void setMarkers(Marker now, int[] next, int flag) {
		ArrayList<Integer> from = map[now.y][now.x]; // now ��ü�� �ٷ� �ϸ� now�� �����ϸ鼭 ���� �����...
		int nx = next[0];
		int ny = next[1];

		int bottom = from.indexOf(now.idx);
		int top = from.size();

		switch (flag) {
		case WHITE:
			for (int i = bottom; i < top; i++) {
				int n = from.get(i);
				map[ny][nx].add(n);
				markers[n].x = nx;
				markers[n].y = ny;
			}
			remove(from, top, bottom);

			break;
		case RED:
			for (int i = top - 1; i >= bottom; i--) {
				int n = from.get(i);
				map[ny][nx].add(n);
				markers[n].x = nx;
				markers[n].y = ny;
			}
			remove(from, top, bottom);

			break;

		default:
			break;
		}

	}

	public static void remove(ArrayList<Integer> from, int top, int bottom) {
		for (int i = top - 1; i >= bottom; i--) {
			from.remove(i);
		}
	}

	public static void print() {

		for (int c = 1; c <= N; c++) {
			for (int r = 1; r <= N; r++) {
				System.out.print(map[c][r].size() + " ");
			}
			System.out.println();
		}
		System.out.println();

	}

}
