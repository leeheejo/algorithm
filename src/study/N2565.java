package study;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class N2565 {
	static int N;
	static int[][] arr;
	static int[] dp;
	static int answer = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N + 1][2];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}

		// https://gmlwjd9405.github.io/2018/09/06/java-comparable-and-comparator.html

		Arrays.sort(arr, new Comparator<int[]>() { // A를 기준으로 오름차순 정렬
			@Override
			public int compare(int[] a, int[] b) {
				if (a[0] < b[0])
					return -1;
				else if (a[0] > b[0])
					return 1;
				return 0;
			}
		});

		dp[1] = 1;
		for (int i = 1; i <= N; i++) {
			dp[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[j][1] < arr[i][1] && dp[j] >= dp[i])
					dp[i] = dp[j] + 1;
			}
			answer = Math.max(answer, dp[i]);
		}

		System.out.println(N-answer);
	}

}
