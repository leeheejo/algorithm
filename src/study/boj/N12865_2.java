package study.boj;

import java.util.Scanner;

public class N12865_2 {
	static int N, K;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		int[][] arr = new int[N + 1][2];
		int[][] dp = new int[K + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (arr[i][0] > i)
					dp[j][i] = dp[j][i - 1];
				if (j - arr[i][0] >= 0) {
					dp[j][i] = Math.max(dp[j][i - 1], dp[j - 1][i]);
					dp[j][i] = Math.max(dp[j][i], dp[j - arr[i][0]][i - 1] + arr[i][1]);

				}

			}
		}
		System.out.println(dp[K][N]);

	}

}
