package study;

import java.util.Scanner;

public class N12865 {
	static int N, K;
	static int[][] arr = new int[101][2];
	static int ans = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		int[][] dp = new int[K + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (arr[i][0] <= K)
					dp[j][i] = dp[j][i - 1];
				if (j - arr[i][0] >= 0) {
					dp[j][i] = Math.max(dp[j][i - 1], dp[j - arr[i][0]][i - 1] + arr[i][1]);
				}
				
				ans = Math.max(ans, dp[j][i]);
			}
		}

		System.out.println(ans);
	}

}
