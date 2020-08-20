package study.boj;

import java.util.Scanner;

public class N14890_200807 {

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

		for (int c = 0; c < 2; c++) {
			for (int i = 0; i < N; i++) {
				if (check(map[i])) {
//					System.out.println(i);
					ans++;
				}

			}
			rotate();

		}

//		N = 6;
//		L = 2;
//
//		System.out.println(check(new int[] { 2, 2, 3, 3, 2, 2 }));
		System.out.println(ans);
	}

	public static boolean check(int[] a) {
		boolean[] visited = new boolean[N];

		for (int i = 0; i < a.length - 1; i++) {
			int flag = i;
			if (Math.abs(a[i] - a[i + 1]) > 1) {
				return false;
			} else if (Math.abs(a[i] - a[i + 1]) == 1) {
				if (a[i] > a[i + 1]) {
//222111
					if (i + L >= N)
						return false;

					int floor = a[i + 1];
					for (int j = i + 1; j <= i + L; j++) {
						if (a[j] != floor || visited[j]) {
							return false;
						}
						visited[j] = true;
//						visited[j + 1] = true;
						flag = j - 1;
					}

				} else if (a[i] < a[i + 1]) {
					if (i - L + 1 < 0)
						return false;
//111222
					int floor = a[i];
					for (int j = i; j > i - L; j--) {
						if (a[j] != floor || visited[j]) {
							return false;
						}
						visited[j] = true;
//						visited[j - 1] = true;
//						flag = j;
					}
				}

			}
			i = flag;
		}
		return true;
	}

	public static void rotate() {
		int[][] cp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				cp[i][j] = map[N - j - 1][i];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = cp[i][j];
//				System.out.print(map[i][j] + " ");
			}
//			System.out.println();
		}

//		System.out.println();
	}
}
