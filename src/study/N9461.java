package study;

import java.util.Scanner;

public class N9461 {

	static int N, T;
	static long[] arr = new long[N + 1];// 이런 경우에 int로 하면 런타임에러 나는 경우 많으니 꼭 최대값 넣어서 확

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			N = sc.nextInt();

			arr[1] = 1;
			arr[2] = 1;
			arr[3] = 1;

			for (int i = 4; i <= N; i++) {
				arr[i] = arr[i - 2] + arr[i - 3];
			}

			System.out.println(arr[N]);
		}

	}

}
