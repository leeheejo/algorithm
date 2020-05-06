package study;

import java.util.Scanner;

public class N14890 {

	public static int N, L;
	public static int[][] map;
	public static boolean[][] visited;

	public static int ans = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();

		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int i = 0; i < 2; i++) {
			visited = new boolean[N][N];
			check();
			rotate();
		}

		System.out.println(ans);

	}

	// visited와map을 오른쪽 90도 회전
	public static void rotate() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[N - j - 1][i];
			}
		}
		map = temp.clone();

		boolean[][] tv = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tv[i][j] = visited[N - j - 1][i];
			}
		}

		visited = tv.clone();
	}

	// 왼쪽에서 오른쪽으로 탐색
	public static void check() {
		int count = 0;
		for (int i = 0; i < N; i++) {
			count++;
			for (int j = 1; j < N;) {
				if (map[i][j] == map[i][j - 1]) {
					j++;
					continue;
				} else if (map[i][j] - map[i][j - 1] == 1) {
					boolean flag = true;
					if (j - L < 0) {
						count--;
						flag = false;
						break;
					}
					for (int k = 0; k < L; k++) {
						if (map[i][j - 1] != map[i][j - 1 - k] || visited[i][j - 1 - k]) {
							flag = false;
							break;
						}
						visited[i][j - 1 - k] = true;

					}
					if (!flag) {
						count--;
						break;
					}
					j++;
					continue;
				} else if (map[i][j] - map[i][j - 1] == -1) {
					boolean flag = true;
					if (j + L > N) {
						count--;
						flag = false;
						break;
					}
					for (int k = 0; k < L; k++) {

						if (map[i][j] != map[i][j + k] || visited[i][j + k]) {
							flag = false;
							break;
						}

						visited[i][j + k] = true;
					}
					if (!flag) {
						count--;
						break;
					} else {
						j += L;
						continue;
					}
				} else {
					count--;
					break;
				}

			}
		}

		ans += count;

	}

	public static void print() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
