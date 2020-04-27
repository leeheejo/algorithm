package study;

import java.util.Scanner;

public class N14501 {
	static int N;
	static int[][] arr;
	static int[] res;
	static int MAX = Integer.MIN_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[2][N + 1];
		res = new int[N + 1];

		for (int i = 1; i <= N; i++) {

			arr[0][i] = sc.nextInt();
			arr[1][i] = sc.nextInt();
		}

		dp();

		for (int i = 1; i <= N; i++) {
			MAX = Math.max(MAX, res[i]);
		}

		System.out.println(MAX);
	}

	public static void dp() {
		for (int i = 1; i <= N; i++) {
			if (i + arr[0][i] > N + 1)
				continue;
			res[i] = arr[1][i];
			for (int j = 1; j <= i; j++) {
				if (j + arr[0][j] > i)
					continue;
				res[i] = Math.max(res[i], arr[1][i] + res[j]);
			}
		}

	}

}
