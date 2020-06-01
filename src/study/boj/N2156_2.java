package study.boj;

import java.util.Scanner;

public class N2156_2 {
	static int N;
	static int[] arr = new int[10001];
	static int[] dp = new int[10001];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 1; i <= N; i++)
			arr[i] = sc.nextInt();

		dp[1] = arr[1];
		dp[2] = arr[2] + arr[1];
		dp[3] = Math.max(dp[2], Math.max(arr[1], arr[2]) + arr[3]);

		for (int i = 4; i <= N; i++) {
			dp[i] = Math.max(arr[i - 1] + dp[i - 3], dp[i - 2]) + arr[i];
			dp[i] = Math.max(dp[i], dp[i - 1]);
		}

		System.out.println(dp[N]);
	}

}
