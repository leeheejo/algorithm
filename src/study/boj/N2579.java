package study.boj;

import java.util.Scanner;

public class N2579 {

	static int N;
	static int[] arr = new int[301];
	static long[] ans = new long[301];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 1; i <= N; i++)
			arr[i] = sc.nextInt();

		ans[1] = arr[1];
		ans[2] = arr[1] + arr[2];
		ans[3] = Math.max(arr[1], arr[2]) + arr[3];

		for (int i = 4; i <= N; i++) {
			ans[i] = arr[i] + Math.max(ans[i - 2], arr[i - 1] + ans[i - 3]);
		}
	
		System.out.println(ans[N]);

	}

}
