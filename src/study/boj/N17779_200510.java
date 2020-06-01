package study.boj;

import java.util.Scanner;

public class N17779_200510 {
	static int[][] map;
	static int[][] tmp;
	static int N;
	static int ans = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) { // (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
						if (x < x + d1 + d2 && x + d1 + d2 <= N && y - d1 >= 1 && y - d1 < y && y < y + d2
								&& y + d2 <= N) {
							// run
							play(x, y, d1, d2);
						}
					}
				}
			}
		}


		System.out.println(ans);

	}

	public static void play(int x, int y, int d1, int d2) {
		tmp = new int[N + 1][N + 1];
		setBoundary(x, y, d1, d2);
		setOthers(x, y, d1, d2);
		ans = Math.min(ans, calc());

	}

//	(x, y), (x+1, y-1), ..., (x+d1, y-d1)
//	(x, y), (x+1, y+1), ..., (x+d2, y+d2)
//	(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
//	(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
	public static void setBoundary(int x, int y, int d1, int d2) {
		for (int i = 0; i <= d1; i++) {
			tmp[x + i][y - i] = 5;
			tmp[x + d2 + i][y + d2 - i] = 5;

		}

		for (int i = 0; i <= d2; i++) {
			tmp[x + i][y + i] = 5;
			tmp[x + d1 + i][y - d1 + i] = 5;
		}

		fillBoundary();

	}

//	1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
//	2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
//	3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
//	4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
	public static void setOthers(int x, int y, int d1, int d2) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (tmp[i][j] == 5)
					continue;
				if (i < x + d1 && j <= y) {
					tmp[i][j] = 1;
				} else if (i <= x + d2 && y < j) {
					tmp[i][j] = 2;
				} else if (x + d1 <= i && j < y - d1 + d2) {
					tmp[i][j] = 3;
				} else if (x + d2 < i && y - d1 + d2 <= j) {
					tmp[i][j] = 4;
				}

			}
		}
	}

	public static int calc() {
		int[] cnt = new int[6];
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt[tmp[i][j]] += map[i][j];
			}
		}
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= 5; i++) {
			max = Math.max(max, cnt[i]);
			min = Math.min(min, cnt[i]);
		}

		return max - min;
	}

	public static void fillBoundary() {
		for (int i = 1; i <= N; i++) {
			int left = 0;
			int right = 0;
			for (int j = 1; j <= N; j++) {
				if (tmp[i][j] == 5) {
					left = j;
					break;
				}
			}

			for (int j = N; j >= 1; j--) {
				if (tmp[i][j] == 5) {
					right = j;
					break;
				}
			}

			for (int j = left; j <= right; j++) {
				tmp[i][j] = 5;
			}
		}
	}

	public static void print() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				System.out.print(tmp[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

}
