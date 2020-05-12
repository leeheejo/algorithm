package study;

import java.util.Scanner;

public class N17143_200511 {

	static int R, C, T;
	static int[][] map, temp;
	static int[] ax = { 0, 0, 1, -1 };
	static int[] ay = { 1, -1, 0, 0 };
	static int a1_x, b1_x;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		T = sc.nextInt();

		map = new int[R][C];
		int aircount = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = sc.nextInt();
				if (map[i][j] == -1) {
					if (aircount == 0) {
						a1_x = i;
						aircount++;
					} else {
						b1_x = i;
					}

				}
			}
		}

		for (int i = 0; i < T; i++) {
			spread();
			windUp();
			windDown();
		}

		System.out.println(calc());
	}

	public static void spread() {
		temp = new int[R][C];
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {

				if (map[i][j] > 0) {
					int dust = map[i][j];
					int cnt = 0;
					for (int k = 0; k < 4; k++) {
						int nx = i + ax[k];
						int ny = j + ay[k];
						if (nx < 0 || ny < 0 || nx >= R || ny >= C)
							continue;
						if (map[nx][ny] == -1)
							continue;
						cnt++;
						temp[nx][ny] += dust / 5;

					}

					temp[i][j] += dust - ((dust / 5) * cnt);
				}
			}
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				map[i][j] = temp[i][j];
			}
		}
		
		map[a1_x][0] = -1;
		map[b1_x][0] = -1;
	}

	public static void windUp() {
		for (int i = a1_x - 1; i > 0; i--) {
			map[i][0] = map[i - 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[0][i] = map[0][i + 1];
		}

		for (int i = 0; i < a1_x; i++) {
			map[i][C - 1] = map[i + 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[a1_x][i] = map[a1_x][i - 1];
		}
		map[a1_x][1] = 0;
	}

	public static void windDown() {

		for (int i = b1_x + 1; i < R - 1; i++) {
			map[i][0] = map[i + 1][0];
		}

		for (int i = 0; i < C - 1; i++) {
			map[R - 1][i] = map[R - 1][i + 1];
		}

		for (int i = R - 1; i > b1_x; i--) {
			map[i][C - 1] = map[i - 1][C - 1];
		}

		for (int i = C - 1; i > 1; i--) {
			map[b1_x][i] = map[b1_x][i - 1];
		}

		map[b1_x][1] = 0;

	}

	public static int calc() {
		int sum = 0;

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] <= 0)
					continue;
				sum += map[i][j];
			}
		}
		return sum;
	}

	public static void print() {
		System.out.println();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
