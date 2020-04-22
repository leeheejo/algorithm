package study;

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
				pop[r][c] = sc.nextInt();
			}
		}
//		draw(2, 4, 2, 2);
//		System.out.println();
//		System.out.println();
//		for (int c = 1; c <= N; c++) {
//			for (int r = 1; r <= N; r++) {
//				System.out.print(map[r][c] + " ");
//			}
//			System.out.println();
//		}

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				for (int d1 = 1; d1 <= N; d1++) {
					for (int d2 = 1; d2 <= N; d2++) {
						if ((r + d1 + d2) <= N && (c - d1) >= 1 && (r - d1) < c && (c + d2) > c && (c + d2) <= N) {// 조건처리주의...

							draw(r, c, d1, d2);
							int tmp = calc();
							answer = Math.min(tmp, answer);
						}
					}
				}
			}
		}

		System.out.println(answer);
	}

	static public void draw(int r, int c, int d1, int d2) {

//		(x, y), (x+1, y-1), ..., (x+d1, y-d1)
//		(x, y), (x+1, y+1), ..., (x+d2, y+d2)
//		(x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
//		(x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
		// 1번
		for (int i = 0; i <= d1; i++) {
			// 1
			map[r + i][c - i] = 5;
			// 4
			map[r + d2 + i][c + d2 - i] = 5;
		}
		// 2
		for (int i = 0; i <= d2; i++) {
			map[r + i][c + i] = 5;
			// 3
			map[r + d1 + i][c - d1 + i] = 5;
		}
//		
//		1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
//		2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
//		3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
//		4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N

		for (int p = 1; p <= N; ++p) {
			for (int q = 1; q <= N; ++q) {
				if (map[q][p] != 0)
					continue;

				if (p < (r + d1) && q <= c)
					map[q][p] = 1;
				else if ((r + d2) >= p && q > c)
					map[q][p] = 2;
				else if ((r + d1) <= p && q < (c - d1 + d2))
					map[q][p] = 3;
				else if ((r + d2) < p && (c - d1 + d2) <= q)
					map[q][p] = 4;
				else
					map[q][p] = 5;
			}
		}

		// 5구역 채우기
		for (int p = 1; p <= N; ++p) {
			int left = -1;
			int right = -1;

			int idx = 1;
			while (idx <= N) {
				if (map[idx][p] == 5) {
					left = idx;
					break;
				}
				idx++;
			}

			idx = N;
			while (idx >= 0) {
				if (map[idx][p] == 5) {
					right = idx;
					break;
				}
				idx--;
			}

			if (left != right) {
				for (int i = left; i < right; ++i)
					map[i][p] = 5;
			}
		}
	}

	public static int calc() {
		int[] arr = new int[5];
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if (map[c][r] == 1)
					arr[0] += pop[c][r];
				else if (map[c][r] == 2)
					arr[1] += pop[c][r];
				else if (map[c][r] == 3)
					arr[2] += pop[c][r];
				else if (map[c][r] == 4)
					arr[3] += pop[c][r];
				else if (map[c][r] == 5)
					arr[4] += pop[c][r];
			}
		}
		for (int i = 0; i < 5; i++) {
			max = Math.max(max, arr[i]);
			min = Math.min(min, arr[i]);
		}

		System.out.println();
		System.out.println(max - min);
		for (int r = 1; r <= N; r++) {
			System.out.println();
			for (int c = 1; c <= N; c++) {
				System.out.print(map[c][r] + " ");
			}

		}

		return max - min;
	}
}
