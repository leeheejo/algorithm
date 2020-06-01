package study.boj;

import java.util.Scanner;

public class N14888_2 {

	static int N;
	static int[] op = new int[4];
	static int[] num, result;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		num = new int[N];
		result = new int[N - 1];

		for (int i = 0; i < N; i++) {
			num[i] = sc.nextInt();
		}

		for (int i = 0; i < 4; i++) {
			op[i] = sc.nextInt();
		}

		bfs(0);
		System.out.println(max);
		System.out.println(min);
	}

	public static void bfs(int index) {
		if (index == N - 1) {
			int ans = num[0];
			for (int i = 0; i < N-1; i++) {

				switch (result[i]) {
				case 0:
					ans += num[i + 1];
					break;
				case 1:
					ans -= num[i + 1];
					break;
				case 2:
					ans *= num[i + 1];
					break;
				case 3:
					ans /= num[i + 1];
					break;

				default:
					break;
				}
			}
			max = Math.max(max, ans);
			min = Math.min(min, ans);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (op[i] == 0)
				continue;

			op[i] = op[i] - 1;
			result[index] = i;
			bfs(index + 1);
			op[i] = op[i] + 1;
		}
	}

}
