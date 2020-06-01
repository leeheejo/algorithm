package study.boj;

import java.util.Scanner;

public class N14501_200507 {
	static int[][] map;
	static int[] dp;
	static int N;
	static int ans = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[2][N + 1];
		dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			map[0][i] = sc.nextInt();
			map[1][i] = sc.nextInt();
		}
		dp();
		for (int i = 1; i <= N; i++)
			ans = Math.max(ans, dp[i]);

		System.out.println(ans);
	}

	public static void dp() {
		for (int i = 1; i <= N; i++) {
			int a = 0;
			for (int j = i - 1; j > 0; j--) {
				if (j + map[0][j] <= i) {
					a = Math.max(a, dp[j]);
				}
			}
			if (i + map[0][i] - 1 <= N) {
				a += map[1][i];
			}
			dp[i] = a;
		}
	}

}
