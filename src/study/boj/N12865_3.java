package study.boj;

import java.util.Scanner;

public class N12865_3 {
	static int N, K;
	static int[][] dp;
	static int[][] arr;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		K = sc.nextInt();
		dp = new int[K + 1][N + 1];
		arr = new int[N + 1][2];

		for (int i = 1; i <= N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				dp[j][i] = Math.max(dp[j][i - 1], dp[j - 1][i]);
				if (j >= arr[i][0]) {
					dp[j][i] = Math.max(dp[j][i], dp[j - arr[i][0]][i - 1] + arr[i][1]);
				}
			}
		}
		
		System.out.println(dp[K][N]);
	}

}
