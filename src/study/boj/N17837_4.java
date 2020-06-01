package study.boj;

import java.util.ArrayList;
import java.util.Scanner;

public class N17837_4 {
	static class Marker {
		int idx;
		int c;
		int r;
		int d;

		public Marker(int idx, int c, int r, int d) {
			super();
			this.idx = idx;
			this.c = c;
			this.r = r;
			this.d = d;
		}

	}

	static int N, K;
	static int[][] colors;
	static Marker[] markers;
	static ArrayList<Integer>[][] map;

	static int[] ac = { 0, 0, 0, -1, 1 };
	static int[] ar = { 0, 1, -1, 0, 0 };

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
			int c = sc.nextInt();
			int r = sc.nextInt();
			int d = sc.nextInt();
			markers[i] = new Marker(i, c, r, d);
			map[c][r].add(i);
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

			// �̵��ϰ��� �ϴ� ����
			int nc = now.c + ac[now.d];
			int nr = now.r + ar[now.d];

			// ������Ż �̰ų� ���
			if (nc < 1 || nr < 1 || nc > N || nr > N || colors[nc][nr] == 2) {
				// ���� ����
				if (now.d % 2 == 0) {
					now.d -= 1;
				} else if (now.d % 2 == 1) {
					now.d += 1;
				}
				// ����ĭ ����
				nc = now.c + ac[now.d];
				nr = now.r + ar[now.d];
			}

			// ȭ��Ʈ�� ����
			// ���� �ȿ� �ִ� ���
			if (nc < 1 || nr < 1 || nc > N || nr > N || colors[nc][nr] == 2)
				continue;

			if (colors[nc][nr] == 0) {
				update(now, nc, nr, 0);

			} else if (colors[nc][nr] == 1) {
				update(now, nc, nr, 1);

			}
			
			if (map[nc][nr].size() >= 4)
				return true;
		}

		return false;
	}

	public static void update(Marker marker, int nc, int nr, int flag) {
		ArrayList<Integer> now = map[marker.c][marker.r];
		int bottom = now.indexOf(marker.idx);
		int top = now.size() - 1;

		if (flag == 0) {
			for (int i = bottom; i <= top; i++) {
				int m = now.get(i);
				map[nc][nr].add(m);
				markers[m].c = nc;
				markers[m].r = nr;
			}

		} else if (flag == 1) {
			for (int i = top; i >= bottom; i--) {
				int m = now.get(i);
				map[nc][nr].add(m);
				markers[m].c = nc;
				markers[m].r = nr;
			}

		}
		remove(now, bottom, top);
	}

	public static void remove(ArrayList<Integer> now, int bottom, int top) {
		for (int i = top; i >= bottom; i--) {
			now.remove(i);
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
