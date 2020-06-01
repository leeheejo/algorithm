package study.boj;

import java.util.Scanner;

public class N1932 {
	static int N;
	static long[][] arr = new long[501][501];
	static long ans = Long.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < i + 1; j++) {
				arr[j][i] = sc.nextLong();
			}
		}

		for (int i = 1; i < N; i++) { // �ι�° �ٺ��� ����
			for (int j = 0; j < i + 1; j++) {
				if (j == 0)
					arr[j][i] = arr[j][i] + arr[j][i - 1];
				else if (j == i)
					arr[j][i] = arr[j][i] + arr[j - 1][i - 1];
				else
					arr[j][i] = arr[j][i] + Math.max(arr[j][i - 1], arr[j - 1][i - 1]);
				ans = Math.max(ans, arr[j][i]);
			}

		}

		System.out.println(ans);
	}

}
