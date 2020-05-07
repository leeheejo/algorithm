package study;

import java.util.Scanner;

public class N14499_200507 {
	static int N, M;
	static int[][] map;
	static int[] dice = new int[7];
	static int[] ax = { 0, 0, 0, -1, 1 };
	static int[] ay = { 0, 1, -1, 0, 0 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][M];

		int x = sc.nextInt();
		int y = sc.nextInt();
		int k = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		int nx = x;
		int ny = y;
		for (int i = 0; i < k; i++) {
			int t = sc.nextInt();
			nx += ax[t];
			ny += ay[t];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				nx -= ax[t];
				ny -= ay[t];
				continue;
			}
			if (t == 1) {
				moveRight();
			} else if (t == 2) {
				moveRight();
				moveRight();
				moveRight();
			} else if (t == 3) {
				moveFront();
				moveFront();
				moveFront();
			} else if (t == 4) {
				moveFront();
			}

			if (map[nx][ny] == 0) {
				map[nx][ny] = dice[6];
			} else {
				dice[6] = map[nx][ny];
				map[nx][ny] = 0;
			}

			System.out.println(dice[1]);
		}

	}

	public static void moveRight() {
		int[] temp = new int[7];
		temp[1] = dice[3];
		temp[2] = dice[2];
		temp[3] = dice[6];
		temp[4] = dice[1];
		temp[5] = dice[5];
		temp[6] = dice[4];

		for (int i = 1; i < 7; i++) {
			dice[i] = temp[i];
		}
	}

	public static void moveFront() {
		int[] temp = new int[7];
		temp[1] = dice[5];
		temp[2] = dice[1];
		temp[3] = dice[3];
		temp[4] = dice[4];
		temp[5] = dice[6];
		temp[6] = dice[2];

		for (int i = 1; i < 7; i++) {
			dice[i] = temp[i];
		}
	}

}
