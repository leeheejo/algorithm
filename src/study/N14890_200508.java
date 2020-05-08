package study;

import java.util.Scanner;

public class N14890_200508 {

	static int N, L;
	static int[][] map;
	static int ans = 0;

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

		check();
		rotate();
		check();
		System.out.println(ans);

	}

	public static int check() {
		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			ans++;
			for (int j = 1; j < N;) {
				if (map[i][j - 1] == map[i][j]) {
					j++;
					continue;
				} else if (map[i][j - 1] - map[i][j] == 1) {
					boolean flag = true;
					for (int k = 0; k < L; k++) {
						if (j + L - 1 >= N) {
							flag = false;
							break;
						}
						if (visited[i][j + k]) {
							flag = false;
							break;
						}
						if (map[i][j] != map[i][j + k]) {
							flag = false;
						} else {
							visited[i][j + k] = true;
						}
					}
					if (!flag) {
						ans--;
						break;
					}
					j += L;

				} else if (map[i][j - 1] - map[i][j] == -1) {
					boolean flag = true;
					for (int k = 1; k <= L; k++) {
						if (j - L < 0) {
							flag = false;
							break;
						}
						if (visited[i][j - k]) {
							flag = false;
							break;
						}
						if (map[i][j - 1] != map[i][j - k]) {
							flag = false;
						} else {
							visited[i][j - k] = true;
						}
					}
					if (!flag) {
						ans--;
						break;
					}
					j++;

				} else {
					ans--;
					break;
				}

			}
		}
		return ans;
	}

	public static void rotate() {
		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				temp[i][j] = map[N - j - 1][i];
			}
		}
		map = temp.clone();

	}

}