package study;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class N2565_2 {

	static int N;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] arr = new int[N + 1][2];
		int[] dp = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i][0] = sc.nextInt();
			arr[i][1] = sc.nextInt();
		}

		Arrays.sort(arr, new Comparator<int[]>() { // A를 기준으로 오름차순 정렬
			@Override
			public int compare(int[] a, int[] b) {
				System.out.println(a + " " + b);
				if (a[0] < b[0]) // a가 다음 b가먼저 들어온 애
									// a[0] = arr[i][0] , a[1] = arr[i][1] 형태..
					return -1; // 순서 바꿔야되는 경우..
				else if (a[0] > b[0])
					return 1;
				return 0;
			}
		});

		int ans = 1;
		dp[1] = 1;
		for (int i = 2; i <= N; i++) {
			dp[i] = 1;
			for (int j = 1; j < i; j++) {
				if (arr[j][1] < arr[i][1] && dp[j] >= dp[i])
					dp[i] = dp[j] + 1;
			}

			ans = Math.max(ans, dp[i]);
		}

		System.out.println(N - ans);
	}

}
