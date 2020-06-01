package study.boj;

import java.util.Scanner;

public class N14501_2 {

	static int N;
	static int[][] map;
	static int Max = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		map = new int[2][N + 1];

		for (int i = 1; i <= N; i++) {
			int t = sc.nextInt();
			int p = sc.nextInt();
			map[0][i] = t;
			map[1][i] = p;
		}

		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			int v = 0;
			for (int j = 1; j <= i - 1; j++) {
				if (j + map[0][j] <= i) {
					v = Math.max(v, dp[j]);
				} 
			}
			if (i + map[0][i] - 1 <= N) {
				v += map[1][i];
			}
			dp[i] = v;
			Max = Math.max(v, Max);
		}

		System.out.println(Max);

	}

}
