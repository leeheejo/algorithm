package study;

import java.util.Arrays;
import java.util.Scanner;

public class N17779 {
	static int N;
	static int[][] pop;
	static int[][] map;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		pop = new int[N + 1][N + 1];
		map = new int[N + 1][N + 1];

		for (int c = 1; c <= N; c++) {
			for (int r = 1; r <= N; r++) {
				pop[c][r] = sc.nextInt();
			}
		}

		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= N; y++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
//						 (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
						if (x < x + d1 + d2 && x + d1 + d2 <= N && 1 <= y - d1 && y < y + d2 && y + d2 <= N) {
							draw(x, y, d1, d2);
							int tmp = calc();
							answer = Math.min(answer, tmp);
						}
					}
				}
			}
		}

//		draw(2, 3, 1, 2);
//		print();
//		System.out.println(calc());

		System.out.println(answer);

	}

	static public void draw(int x, int y, int d1, int d2) {
//		(x, y), (x+1, y-1), ..., (x+d1, y-d1)
//		(x, y), (x+1, y+1), ..., (x+d2, y+d2)
//		(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
//		(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)

		for (int a[] : map) {
			Arrays.fill(a, 0);
		}
		// 경계선 그리기
		for (int i = 0; i <= d1; i++) {
			map[x + i][y - i] = 5;
			map[x + d2 + i][y + d2 - i] = 5;
		}
		for (int i = 0; i <= d2; i++) {
			map[x + i][y + i] = 5;
			map[x + d1 + i][y - d1 + i] = 5;
		}

		for (int j = 1; j <= N; j++) {

			int left = -1;
			int right = -1;

			for (int i = 1; i <= N; i++) {
				if (map[j][i] == 5) {
					left = i;
					break;
				}
			}
			for (int i = N; i > 0; i--) {
				if (map[j][i] == 5) {
					right = i;
					break;
				}
			}
			if (left != -1 && right != -1) {
				for (int i = left; i <= right; i++) {
					map[j][i] = 5;
				}
			}
		}

//		1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
//		2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
//		3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
//		4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
		for (int c = 1; c <= N; c++) {
			for (int r = 1; r <= N; r++) {
				if (map[c][r] == 5)
					continue;
				if (c < x + d1 && r <= y) {
					map[c][r] = 1;
				}

				else if (c <= x + d2 && r <= N && r > y) {
					map[c][r] = 2;
				}

				else if (x + d1 <= c && c <= N && r < y - d1 + d2) {
					map[c][r] = 3;
				}

				else if (x + d2 < c && c <= N && y - d1 + d2 <= r && r <= N) {
					map[c][r] = 4;
				}
			}
		}

	}

	public static int calc() {
		int ans = 0;
		int[] cal = new int[5];
		for (int col = 1; col <= N; col++) {
			for (int row = 1; row <= N; row++) {
				if (map[col][row] == 1) {
					cal[0] += pop[col][row];
				} else if (map[col][row] == 2) {
					cal[1] += pop[col][row];
				} else if (map[col][row] == 3) {
					cal[2] += pop[col][row];
				} else if (map[col][row] == 4) {
					cal[3] += pop[col][row];
				} else if (map[col][row] == 5) {
					cal[4] += pop[col][row];
				}
			}
		}

		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < 5; i++) {
			max = Math.max(max, cal[i]);
			min = Math.min(min, cal[i]);
		}

		return max - min;
	}

	public static void print() {
		for (int c = 1; c <= N; c++) {
			for (int r = 1; r <= N; r++) {
				System.out.print(map[c][r] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
