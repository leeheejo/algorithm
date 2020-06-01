package study.boj;

import java.util.Scanner;

public class N2748_re {

	static int N;
	static long[] arr = new long[91]; // 당당하게 제출했다가 틀림 -> 90번째가 int 범위를 벗어난다..long 으로 해야됨
	// int 범위 -2,147,483,648 ~ 2,147,483,647

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr[0] = 0;
		arr[1] = 1;
		for (int i = 2; i <= N; i++) {
			arr[i] = arr[i - 1] + arr[i - 2];
		}

		System.out.println(arr[N]);
	}

}
