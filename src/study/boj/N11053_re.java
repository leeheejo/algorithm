package study.boj;

import java.util.Scanner;

public class N11053_re {

	static int N;
	static int[] arr = new int[1001];
	static int[] dp = new int[1001];
	static int ans = 1; //최소값이랑 최대값은 확인하고 제출하기 Intger.MIN_VALUE로 했더니 1이면 minvalue 출력됨 !!!

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 1; i <= N; i++) {
			arr[i] = sc.nextInt();
		}

		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[j] < arr[i] && dp[i] <= dp[j])
					dp[i] = dp[j] + 1;
			}
			ans = Math.max(dp[i], ans);
		}

		System.out.println(ans);
	}

}
