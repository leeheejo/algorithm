package study;

import java.util.Scanner;

public class N1904 {
	static int N;
	static int[] arr = new int[1000001];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		arr[1] = 1;
		arr[2] = 2;

		for (int i = 3; i <= N; i++) {
			arr[i] = (arr[i - 1] + arr[i - 2]) % 15746;
		}

		System.out.println(arr[N]);
	}

}
