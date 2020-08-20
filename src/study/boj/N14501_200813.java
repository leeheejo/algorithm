package study.boj;

import java.util.Scanner;

public class N14501_200813 {

	static int N;
	static int[][] map;
	static int[] dp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[2][N];
		dp = new int[N];
		for (int i = 0; i < N; i++) {
			map[0][i] = sc.nextInt();
			map[1][i] = sc.nextInt();
		}

		int ans = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			dp[i] = 0;

			for (int j = i - 1; j >= 0; j--) {
				if (j + map[0][j] - 1 < i) {
					dp[i] = Math.max(dp[i], dp[j]);
				}
			}

			if (i + map[0][i] <= N) {
				dp[i] += map[1][i];
			}

			ans = Math.max(ans, dp[i]);
		}

		System.out.println(ans);
	}

}
