package study;

import java.util.Scanner;

public class N1003_arr {

	static int T;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int i = 0; i < T; i++) {
			int n = sc.nextInt();
			fib(n);
		}

	}

	// int 배열 숫자 주의하기 
	public static void fib(int n) {
		int[][] arr = new int[41][2];
		arr[0][0] = 1;
		arr[1][1] = 1;

		for (int i = 2; i <= n; i++) {
			arr[i][0] = arr[i - 1][0] + arr[i - 2][0];
			arr[i][1] = arr[i - 1][1] + arr[i - 2][1];
		}

		System.out.println(arr[n][0] + " " + arr[n][1]);
	}

}
