package study.boj;

import java.util.Scanner;

public class N14503_200814 {

	static int N, M;
	static int[][] map;
	static int[] ax = { -1, 0, 1, 0 };
	static int[] ay = { 0, -1, 0, 1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		int x = sc.nextInt();
		int y = sc.nextInt();
		int d = sc.nextInt();
		if (d == 1)
			d = 3;
		else if (d == 3)
			d = 1;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int count = 0;
		map[x][y] = 2;
		count++;

		while (true) {

			boolean flag = true;
			for (int i = 1; i < 5; i++) {
				int nd = (d + i) % 4;
				int nx = x + ax[nd];
				int ny = y + ay[nd];

				// 청소할 수 있는 경우
				if (map[nx][ny] != 1 && map[nx][ny] != 2) {
					flag = false;
					x = nx;
					y = ny;
					d = nd;
					map[x][y] = 2;
					count++;
					break;
				}
			}

			if (flag) {
				int nd = (d + 2) % 4;
				int nx = x + ax[nd];
				int ny = y + ay[nd];
				if (map[nx][ny] == 1) {
					break;
				}
				x = nx;
				y = ny;
			}

		}

		print();
		System.out.println(count);
	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
