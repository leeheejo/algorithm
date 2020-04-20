package study;

import java.util.Scanner;

public class N11054 {

	static int N;
	static int[] arr = new int[1001];
	static int[][] dp = new int[2][1001];

	static int ans = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}

		dp[0][1] = 1;
		dp[1][N] = 1;

		// 먼저 부분 LIS
		for (int i = 2; i <= N; i++) {
			dp[0][i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[j] < arr[i] && dp[0][i] <= dp[0][j])
					dp[0][i] = dp[0][j] + 1;
			}
		}

		for (int i = N - 1; i > 0; i--) {
			dp[1][i] = 1;
			for (int j = i + 1; j <= N; j++) {
				if (arr[j] < arr[i] && dp[1][i] <= dp[1][j])
					dp[1][i] = dp[1][j] + 1;
			}
		}

		for (int i = 1; i <= N; i++)
			ans = Math.max(ans, dp[0][i] + dp[1][i] - 1);
		
		System.out.println(ans);
	}

}
