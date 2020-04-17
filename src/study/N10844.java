package study;

import java.util.Scanner;

public class N10844 {

	static int N;
	static long[][] arr = new long[10][101];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 1; i < 10; i++) {
			arr[i][1] = 1;
			if (i == 9)
				arr[i][2] = 1;
			else
				arr[i][2] = 2;
		}

		for (int j = 3; j <= N; j++) {
			for (int i = 1; i < 10; i++) {
				if (i == 1)
					arr[i][j] = (arr[i][j - 2] + arr[i + 1][j - 1]) % 1000000000;
				else if (i == 9)
					arr[i][j] = (arr[i - 1][j - 1]) % 1000000000;
				else
					arr[i][j] = (arr[i - 1][j - 1] + arr[i + 1][j - 1]) % 1000000000;

			}
		}

		long cnt = 0;
		for (int i = 1; i < 10; i++) {
			cnt += arr[i][N];
		}

		System.out.println(cnt % 1000000000);

	}

}
