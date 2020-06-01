package study.boj;

import java.util.Scanner;

public class N1149 {

	static int N;
	static int[][] arr = new int[3][1001];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();

		for (int i = 0; i < N; i++) {
			arr[0][i] = sc.nextInt();
			arr[1][i] = sc.nextInt();
			arr[2][i] = sc.nextInt();
		}

		for (int i = 1; i < N; i++) {
			arr[0][i] += Math.min(arr[1][i - 1], arr[2][i - 1]);
			arr[1][i] += Math.min(arr[2][i - 1], arr[0][i - 1]);
			arr[2][i] += Math.min(arr[1][i - 1], arr[0][i - 1]);

		}

		System.out.println(Math.min(arr[0][N - 1], Math.min(arr[1][N - 1], arr[2][N - 1])));
	}

}
