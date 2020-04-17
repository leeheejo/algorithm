package study;

import java.util.Scanner;

public class N11053 {
	static int N;
	static int[] arr = new int[1001];
	static int[] dp = new int[1001];
	static int answer = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 1; i <= N; i++)
			arr[i] = sc.nextInt();

		dp[1] = 1;
		for (int i = 2; i <= N; i++) {

			dp[i] = 1;
			for (int j = 1; j < i; j++) { // 앞에 애들 탐색
				// 자기보다 작은애들 중에 가장 큰 값 +1
				if (arr[j] < arr[i] && dp[i] <= dp[j])
					dp[i] = dp[j] + 1;
			}

			answer = Math.max(answer, dp[i]);
		}

		System.out.println(answer);
	}

}
