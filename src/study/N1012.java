package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N1012 {

	static int ans = 0;
	static int T, M, N, K;
	static int[][] arr = new int[50][50];
	static int[] ax = { 1, -1, 0, 0 };
	static int[] ay = { 0, 0, 1, -1 };

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		T = sc.nextInt();

		for (int t = 0; t < T; t++) {
			M = sc.nextInt();
			N = sc.nextInt();
			K = sc.nextInt();
			for (int i = 0; i < K; i++) {
				int num = sc.nextInt();
				int line = sc.nextInt();
				arr[num][line] = 1;
			}

			ans = 0;
			for (int line = 0; line < N; line++) {
				for (int num = 0; num < M; num++) {
					if (arr[num][line] == 1) {
						ans++;
						BFS(num, line);
					}
				}
			}

			System.out.println(ans);
		}

	}

	public static void BFS(int x, int y) {
		arr[x][y] = 0;
		for (int i = 0; i < 4; i++) {
			int nextX = x + ax[i];
			int nextY = y + ay[i];

			if (nextX < 0 || nextX >= M || nextY < 0 || nextY >= N)
				continue;
			if (arr[nextX][nextY] == 0)
				continue;

			arr[nextX][nextY] = 0;
			BFS(nextX, nextY);

		}

	}

}
