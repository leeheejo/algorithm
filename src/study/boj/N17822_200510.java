package study.boj;

import java.util.Scanner;

public class N17822_200510 {
	static int N, M, K; // N개의 원판 M개의 숫자 K번 회전
	static int[][] map;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N + 1][M];
		K = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < K; i++) {
			int x = sc.nextInt();
			int d = sc.nextInt();
			int k = sc.nextInt();
			play(x, d, k);
		}
		System.out.println(calc());
	}

	public static void play(int x, int d, int k) {
		for (int i = 0; i < k; i++) {
			for (int j = 1; j <= N; j++) {
				if (j % x == 0) {
					if (d == 0) {
						turnRight(j);
					} else if (d == 1) {
						turnLeft(j);
					}
				}
			}
		}
		check();
	}

	public static void turnRight(int index) { // 시계방향 회전
		int temp = map[index][M - 1];
		for (int i = M - 2; i >= 0; i--) {
			map[index][i + 1] = map[index][i];
		}
		map[index][0] = temp;
	}

	public static void turnLeft(int index) { // 시계방향 회전
		for (int i = 1; i < M; i++)
			turnRight(index);
	}

	public static void check() { // 인접한 것 체크 및 값 변경
		boolean[][] visited = new boolean[N + 1][M];
		int total = 0;
		int count = 0;
		boolean flag = false;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					continue;
				}
				total += map[i][j];
				count++;
				if (i != N) {
					if (map[i][j] == map[i + 1][j]) {
						flag = true;
						visited[i][j] = true;
						visited[i + 1][j] = true;
					}
				}
				if (j != M - 1) {
					if (map[i][j] == map[i][j + 1]) {
						flag = true;
						visited[i][j] = true;
						visited[i][j + 1] = true;
					}
				} else {
					if (map[i][j] == map[i][0]) {
						flag = true;
						visited[i][0] = true;
						visited[i][j] = true;

					}
				}
			}
		}

		if (!flag) {
			if (count != 0) {
				int avg = total / count;
				int sub = total % count;
				for (int i = 1; i <= N; i++) {
					for (int j = 0; j < M; j++) {
						if (map[i][j] == 0)
							continue;
						if (map[i][j] > avg)
							map[i][j]--;
						else if (map[i][j] < avg)
							map[i][j]++;
						else if (map[i][j] == avg) {
							if (sub > 0)
								map[i][j]++;
						}
					}
				}
			}
		} else {
			for (int i = 1; i <= N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j]) {
						map[i][j] = 0;
					}

				}
			}

		}

	}

	public static int calc() {
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}

	private static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
