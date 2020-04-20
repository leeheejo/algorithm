package study;

import java.util.Scanner;

public class N2293 {
	static int N, K;
	static int[] arr = new int[101];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		for (int i = 1; i <= N; i++)
			arr[i] = sc.nextInt();

		int[][] dp = new int[K + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j < arr[i]) {
					dp[j][i] = dp[j][i - 1];
				} else if (j == arr[i]) {
					dp[j][i] = dp[j][i - 1] + 1;
				} else if (j > arr[i]) {
					dp[j][i] = dp[j][i - 1] + dp[j - arr[i]][i];
				}
			}
		}

		System.out.println(dp[K][N]);

	}

}
