package study;

import java.util.Scanner;

public class N10844_3 {
	static int N;
	static long[][] arr = new long[10][101];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		long answer = 0;
		for (int i = 1; i <= N; i++) {
			answer = 0;
			for (int j = 1; j <= 9; j++) {
				if (i == 1 || i == 2) {
					arr[j][i] = i;
					if (i == 2 && j == 9)
						arr[j][i] = 1;
					answer += arr[j][i];
					continue;
				}

				if (j == 1)
					arr[j][i] = (arr[j][i - 2] + arr[j + 1][i - 1]) % 1000000000;
				else if (j == 9)
					arr[j][i] = arr[j - 1][i - 1] % 1000000000;
				else
					arr[j][i] = (arr[j - 1][i - 1] + arr[j + 1][i - 1]) % 1000000000;

				answer += arr[j][i];
			}
		}

		System.out.println(answer % 1000000000);

	}

}
