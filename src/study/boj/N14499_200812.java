package study.boj;

import java.util.Scanner;

public class N14499_200812 {

	static int[] dice = { 0, 0, 0, 0, 0, 0, 0 };
	static int N, M, x, y, K;
	static int[][] map;
	static int[] ax = { 0, 0, 0, -1, 1 };
	static int[] ay = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		x = sc.nextInt();
		y = sc.nextInt();

		K = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < K; i++) {
			int d = sc.nextInt();
			int nx = x + ax[d];
			int ny = y + ay[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M)
				continue;

			if (d == 1) {
				moveRight();
			} else if (d == 2) {
				moveRight();
				moveRight();
				moveRight();

			} else if (d == 3) {
				moveFront();
				moveFront();
				moveFront();
			} else if (d == 4) {
				moveFront();
			}

			if (map[nx][ny] == 0) {
				map[nx][ny] = dice[6];
			} else {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}

			System.out.println(dice[1]);
			x = nx;
			y = ny;
		}

	}

	public static void moveRight() {
		int temp = dice[6];
		dice[6] = dice[3];
		dice[3] = dice[1];
		dice[1] = dice[4];
		dice[4] = temp;
	}

	public static void moveFront() {
		int temp = dice[6];
		dice[6] = dice[5];
		dice[5] = dice[1];
		dice[1] = dice[2];
		dice[2] = temp;
	}

}
