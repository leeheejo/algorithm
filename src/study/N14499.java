package study;

import java.util.Scanner;

public class N14499 {

	static int[] dice = new int[7]; // {1: 위, 2: 뒤, 3:우, 4: 좌, 5: 앞, 6: 아래}
	static int N, M;
	static int[][] map;
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
		int K = sc.nextInt();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		int nx = x;
		int ny = y;
		for (int i = 0; i < K; i++) {
			int d = sc.nextInt();
			nx += ax[d];
			ny += ay[d];
			if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
				nx -= ax[d];
				ny -= ay[d];
				continue;
			}

			if (d == 1) { // 동
				moveLeft();
				moveLeft();
				moveLeft();

			} else if (d == 2) { // 서
				moveLeft();

			} else if (d == 3) { // 북
				moveFront();
				moveFront();
				moveFront();
			} else if (d == 4) {// 남
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

	public static void moveFront() {
		int[] temp = new int[7];
		temp[1] = dice[2];
		temp[2] = dice[6];
		temp[3] = dice[3];
		temp[4] = dice[4];
		temp[5] = dice[1];
		temp[6] = dice[5];

		dice = temp.clone();
	}

	public static void moveLeft() {
		int[] temp = new int[7];
		temp[1] = dice[3];
		temp[2] = dice[2];
		temp[3] = dice[6];
		temp[4] = dice[1];
		temp[5] = dice[5];
		temp[6] = dice[4];

		dice = temp.clone();
	}
}
