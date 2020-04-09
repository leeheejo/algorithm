package study;

import java.util.Scanner;

public class N1003_constructor {

	static int T;

	// 구조체 생성해서 작성했는데 2차원 배열 사용하는 방법도 있음. 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			int n = sc.nextInt();
			fib(n);
		}

	}

	public static class P {
		int zero;
		int one;

		public P(int zero, int one) {
			super();
			this.zero = zero;
			this.one = one;
		}

		public P() {
			super();
		}

	}

	public static void fib(int n) {
		P[] arr = new P[41];

		arr[0] = new P(1, 0);
		arr[1] = new P(0, 1);
		for (int i = 2; i <= n; i++) {
			arr[i] = new P(arr[i - 1].zero + arr[i - 2].zero, arr[i - 1].one + arr[i - 2].one);
		}
		System.out.println(arr[n].zero + " " + arr[n].one);
	}

}
