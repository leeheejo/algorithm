package study;

import java.util.Scanner;

public class N14888 {
	static int N;
	static int[] arr = new int[11]; // 숫자 입력 받는 배열
	static int[] op = new int[4]; // 연산자 개수 입력 받는 배열

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}
		for (int i = 0; i < 4; i++) {
			op[i] = sc.nextInt();
		}

		run(0, arr[0]);
		System.out.println(max);
		System.out.println(min);

	}

	public static void run(int index, int value) {
		if (index == N - 1) {
			max = Math.max(max, value);
			min = Math.min(min, value);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] == 0)
				continue;

			int nextV = 0;
			switch (i) {
			case 0:
				nextV = value + arr[index + 1];
				break;
			case 1:
				nextV = value - arr[index + 1];
				break;
			case 2:
				nextV = value * arr[index + 1];
				break;
			case 3:
				nextV = value / arr[index + 1];
				break;
			}
			op[i] = op[i] - 1;
			run(index + 1, nextV);
			op[i] = op[i] + 1;
		}

	}
}
