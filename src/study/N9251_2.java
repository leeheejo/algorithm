package study;

import java.util.Scanner;

public class N9251_2 {
	static int[][] dp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();

		dp = new int[b.length() + 1][a.length() + 1];
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				dp[j][i] = Math.max(dp[j][i - 1], dp[j - 1][i]);
				if (a.charAt(i - 1) == b.charAt(j - 1)) {
					dp[j][i] = dp[j - 1][i - 1] + 1;
				}
			}
		}

		System.out.println(dp[b.length()][a.length()]);

	}

}
