package study;

import java.util.Scanner;

public class N1912_2 {

	static int N;
	static int[] arr;
	static int[] dp;
	static int answer = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N + 1];
		dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}

		for (int i = 1; i <= N; i++) {
			dp[i] = Math.max(arr[i], dp[i - 1] + arr[i]);
			answer = Math.max(answer, dp[i]);
		}

		System.out.println(answer);
	}

}
