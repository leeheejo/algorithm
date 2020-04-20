package study;

import java.util.Scanner;

public class N1912 {
	static int N;
	static int[] arr = new int[100001];
	static long[] dp = new long[100001];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 1; i <= N; i++)
			arr[i] = sc.nextInt();

		dp[1] = arr[1];
		long ans = dp[1];

		for (int i = 2; i <= N; i++) {
			dp[i] = arr[i];
			if (dp[i - 1] + dp[i] > dp[i]) {
				dp[i] = dp[i - 1] + dp[i];
			}
			ans = Math.max(ans, dp[i]);
		}

		System.out.println(ans);

	}

}
