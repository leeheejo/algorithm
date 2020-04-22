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
			this.r = c;
			this.direction = direction;
		}
	}

	static int N, K;
	static int[][] colors;
	static Marker[] markers;
	static ArrayList<Integer>[][] map;

	static int[] ac = { 0, 1, -1, 0, 0 };
	static int[] ar = { 0, 0, 0, -1, 1 };

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
				;
			}
		}
		// 1000번 안에 게임이 끝나지 않을 경우
		System.out.println(-1);
	}

	public static boolean move(int index) {
		int cc = markers[index].c;
		int cr = markers[index].r;
		int nc = markers[index].c + ac[markers[index].direction];
		int nr = markers[index].r + ar[markers[index].direction];

		if (nc < 1 || nr < 1 || nc > N || nr > N || colors[nc][nr] == 2) { // 방향 변경 이동하려는 칸이 파랑이거나 범위 이탈인 경우
			if (markers[index].direction % 2 != 0) {
				markers[index].direction += 1;
			} else if (markers[index].direction % 2 == 0) {
				markers[index].direction -= 1;
			}
			nc = markers[index].c + ac[markers[index].direction];
			nr = markers[index].r + ar[markers[index].direction];
		}

		if (nr <= N && nr >= 1 && nc <= N && nc >= 1 && colors[nr][nc] != 2) {
			int bottom = map[markers[index].c][markers[index].r].indexOf(index);
			if (colors[nc][nr] == 0) {
				updateMarkers(new int[] { cc, cr }, new int[] { nc, nr }, map[cc][cr].size() - 1, bottom, 0);
			} else if (colors[nc][nr] == 1) {
				updateMarkers(new int[] { cc, cr }, new int[] { nc, nr }, map[cc][cr].size() - 1, bottom, 1);
			}
			if (map[nc][nr].size() >= 4) {
				return true;
			}
		}

		return false;
	}

	public static void updateMarkers(int[] from, int[] to, int top, int bottom, int color) {
		// int[] from = { c, r};

		if (color == 0) {// 화이트
			for (int i = bottom; i <= top; i++) {
				int m = map[from[0]][from[1]].get(i);
				map[to[0]][to[1]].add(m);
				markers[m].c = to[0];
				markers[m].r = to[1];
			}
		} else if (color == 1) { // 빨강
			for (int i = top; i <= bottom; i--) {
				int m = map[from[0]][from[1]].get(i);
				map[to[0]][to[1]].add(m);
				markers[m].c = to[0];
				markers[m].r = to[1];
			}
		}
		removeMarkers(from, top, bottom);
	}

	public static void removeMarkers(int[] from, int top, int bottom) {
		for (int i = bottom; i <= top; i++) {
			map[from[0]][from[1]].remove(i);
		}
	}
}
