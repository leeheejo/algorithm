package study.boj;

import java.util.Scanner;

public class N14501_3 {

	// TODO Auto-generated method stub
	static int N;
	static int[][] map;
	static int Max = Integer.MIN_VALUE;
	static int[] dp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		map = new int[2][N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			int t = sc.nextInt();
			int p = sc.nextInt();
			map[0][i] = t;
			map[1][i] = p;
		}
	}

	public static void dp() {
		for (int i = 1; i <= N; i++) {
			for (int j = i; j > 0; j--) {
				if (map[0][i] + i - 1 <= i) {

				}

			}
		}
	}

}
