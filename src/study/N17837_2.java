package study;

import java.util.ArrayList;
import java.util.Scanner;

//https://velog.io/@hyeon930/BOJ-17837-%EC%83%88%EB%A1%9C%EC%9A%B4-%EA%B2%8C%EC%9E%842-Java
public class N17837_2 {

	static class Marker {
		int index, c, r, direction;

		public Marker(int index, int c, int r, int direction) {
			super();
			this.index = index;
			this.c = c;
			this.r = r;
			this.direction = direction;
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

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				colors[i][j] = sc.nextInt();
				map[i][j] = new ArrayList<>();
			}
		}

		for (int i = 1; i <= K; i++) {
			int index = i;
			int c = sc.nextInt();
			int r = sc.nextInt();
			int direction = sc.nextInt();
			markers[i] = new Marker(index, c, r, direction);
			map[c][r].add(i);
		}

		play(0);
	}

	public static void play(int index) {
		int times = 0;

		while (times <= 1000) {
			times++;

			for (int i = 1; i <= K; ++i) {

				if (move(i)) {
					System.out.println(times);
					return;
				}
			}
//			for (int r = 1; r <= N; r++) {
//				for (int c = 1; c <= N; c++) {
//					System.out.print(map[c][r].size() + " ");
//				}
//				System.out.println();
//			}
//
//			System.out.println();
		}
		// 1000�� �ȿ� ������ ������ ���� ���
		System.out.println(-1);

	}

	public static boolean move(int index) {
		Marker now = markers[index];

		int nc = markers[index].c + ac[now.direction];
		int nr = markers[index].r + ar[now.direction];

		if (nc < 1 || nr < 1 || nc > N || nr > N || colors[nc][nr] == 2) { // ���� ���� �̵��Ϸ��� ĭ�� �Ķ��̰ų� ���� ��Ż��
																			// ���
			if (markers[index].direction % 2 != 0) {
				markers[index].direction += 1;
			} else if (markers[index].direction % 2 == 0) {
				markers[index].direction -= 1;
			}
			nc = now.c + ac[markers[index].direction];
			nr = now.r + ar[markers[index].direction];
		}

		if (nr <= N && nr >= 1 && nc <= N && nc >= 1 && colors[nc][nr] != 2) {
			if (colors[nc][nr] == 0) {
				updateMarkers(markers[index], nr, nc, 0);
			} else if (colors[nc][nr] == 1) {
				updateMarkers(markers[index], nr, nc, 1);
			}
			if (map[nc][nr].size() >= 4) {
				return true;
			}
		}

		return false;
	}

	public static void updateMarkers(Marker now, int nr, int nc, int color) {
		// int[] from = { c, r};
		ArrayList<Integer> from = map[now.c][now.r];
		ArrayList<Integer> to = map[nc][nr];
		int bottom = from.indexOf(now.index);
		int top = from.size() - 1;

		if (color == 0) {// ȭ��Ʈ
			for (int i = bottom; i <= top; i++) {
				int m = from.get(i);
				to.add(m);
				markers[m].c = nc;
				markers[m].r = nr;
			}
		} else if (color == 1) { // ����
			for (int i = top; i >= bottom; i--) {
				int m = from.get(i);
				to.add(m);
				markers[m].c = nc;
				markers[m].r = nr;
			}
		}
		removeMarkers(from, top, bottom);
	}

	public static void removeMarkers(ArrayList<Integer> list, int top, int bottom) {
		for (int i = top; i >= bottom; i--) {
			list.remove(i);
		}
	}
}
