package study;

import java.util.ArrayList;
import java.util.Scanner;

public class N15685 {
	static int N;
	static int[][] map = new int[101][101];
	static int[] ax = { 1, 0, -1, 0 };
	static int[] ay = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			ArrayList<Integer> list = new ArrayList<Integer>();
			int x = sc.nextInt();
			int y = sc.nextInt();
			int d = sc.nextInt();
			int g = sc.nextInt();

			list.add(d);
			for (int j = 0; j < g; j++) {
				int size = list.size();
				for (int k = size - 1; k >= 0; k--) {
					int nd = (list.get(k) + 1) % 4;
					list.add(nd);
				}
			}

			map[y][x] = 1;
			for (int dir : list) {
				x += ax[dir];
				y += ay[dir];
				map[y][x] = 1;
			}
			list.clear();
		}

		System.out.println(calc());
	}

	public static int calc() {
		int res = 0;
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (map[i][j] == 1 && map[i + 1][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j + 1] == 1)
					res++;

			}
		}
		return res;
	}

}
